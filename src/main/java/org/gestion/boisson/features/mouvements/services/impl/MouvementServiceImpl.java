package org.gestion.boisson.features.mouvements.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.features.lots.services.LotService;
import org.gestion.boisson.features.mouvements.entities.Mouvement;
import org.gestion.boisson.features.mouvements.enums.TypeAjustement;
import org.gestion.boisson.features.mouvements.enums.TypeMouvement;
import org.gestion.boisson.features.mouvements.repositories.MouvementRepository;
import org.gestion.boisson.features.mouvements.services.MouvementService;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Service de gestion des mouvements de stock (entrées, sorties, retours, ajustements).
 */
@Named
@ApplicationScoped
@Slf4j
public class MouvementServiceImpl implements MouvementService {

    @Inject
    private MouvementRepository repository;

    @Inject
    private LotService lotService;

    /**
     * Enregistre un mouvement unitaire.
     * - SORTIE : applique FEFO puis FIFO.
     * - AUTRES : persistance directe.
     */
    @Override
    @Transactional
    public Mouvement enregistrerMouvement(Mouvement mouvement) {
        validateMouvement(mouvement);
        if (mouvement.getType() == TypeMouvement.SORTIE) {
            log.info("[SORTIE] Demande de sortie de {} unités pour la boisson {} par utilisateur {}", mouvement.getQuantite(), mouvement.getBoisson().getNom(), mouvement.getUtilisateur() != null ? mouvement.getUtilisateur().getId() : null);
            return processSortieMouvementOptimized(mouvement);
        }
        log.info("[MOUVEMENT] Enregistrement d'un mouvement {} pour la boisson {} (lot: {})", mouvement.getType(), mouvement.getBoisson().getNom(), mouvement.getLot() != null ? mouvement.getLot().getId() : null);
        return repository.save(mouvement);
    }

    /**
     * Enregistre une liste de mouvements (batch).
     */
    @Override
    @Transactional
    public List<Mouvement> enregistrerMouvementsBatch(List<Mouvement> mouvements) {
        List<Mouvement> saved = new ArrayList<>();
        for (Mouvement m : mouvements) {
            saved.add(enregistrerMouvement(m));
        }
        return saved;
    }

    /**
     * Enregistre un retour client :
     * - Reintegre la quantite au lot (vendable=false).
     * - Cree un nouveau mouvement ENTREE/RETOUR_CLIENT.
     */
    @Override
    @Transactional
    public Mouvement enregistrerRetour(Mouvement retour, String raisonRetour) {
        validateMouvement(retour);
        Lot lot = retour.getLot();
        if (lot == null) {
            log.error("[RETOUR] Tentative de retour sans lot pour la boisson {}", retour.getBoisson().getNom());
            throw new IllegalArgumentException("Un retour doit etre lie a un lot");
        }
        lot.setQuantiteActuelle(lot.getQuantiteActuelle() + retour.getQuantite());
        lot.setVendable(false);
        lotService.updateLot(lot);
        log.info("[RETOUR] Retour de {} unités sur le lot {} (nouvelle quantité: {})", retour.getQuantite(), lot.getId(), lot.getQuantiteActuelle());
        Mouvement mvt = Mouvement.builder()
                .type(TypeMouvement.ENTREE)
                .boisson(retour.getBoisson())
                .lot(lot)
                .utilisateur(retour.getUtilisateur())
                .quantite(retour.getQuantite())
                .typeAjustement(TypeAjustement.RETOUR_CLIENT)
                .raison(raisonRetour)
                .build();
        return repository.save(mvt);
    }

    /**
     * Enregistre un ajustement de stock :
     * - positif si RETOUR_CLIENT
     * - négatif sinon (CASSE, VOL, PERTE)
     */
    @Override
    @Transactional
    public Mouvement enregistrerAjustement(Mouvement ajust, String raisonAjustement) {
        validateMouvement(ajust);
        Lot lot = ajust.getLot();
        if (lot == null) {
            log.error("[AJUSTEMENT] Tentative d'ajustement sans lot pour la boisson {}", ajust.getBoisson().getNom());
            throw new IllegalArgumentException("Un ajustement doit être lie à un lot");
        }
        if (ajust.getTypeAjustement() == null) {
            log.error("[AJUSTEMENT] Type d'ajustement manquant pour la boisson {} (lot: {})", ajust.getBoisson().getNom(), lot.getId());
            throw new IllegalArgumentException("Le type d'ajustement est requis");
        }
        int delta = ajust.getQuantite();
        boolean positif = ajust.getTypeAjustement() == TypeAjustement.RETOUR_CLIENT;
        int nouvelleQte = lot.getQuantiteActuelle() + (positif ? delta : -delta);
        if (nouvelleQte < 0) {
            log.error("[AJUSTEMENT] Ajustement négatif impossible : stock négatif pour le lot {} (après: {})", lot.getId(), nouvelleQte);
            throw new IllegalArgumentException("L'ajustement conduit a un stock negatif");
        }
        lot.setQuantiteActuelle(nouvelleQte);
        lotService.updateLot(lot);
        log.info("[AJUSTEMENT] Ajustement de {} unités sur le lot {} (nouvelle quantité: {})", delta, lot.getId(), nouvelleQte);
        Mouvement mvt = Mouvement.builder()
                .type(TypeMouvement.AJUSTEMENT)
                .boisson(ajust.getBoisson())
                .lot(lot)
                .utilisateur(ajust.getUtilisateur())
                .quantite(delta)
                .typeAjustement(ajust.getTypeAjustement())
                .raison(raisonAjustement)
                .build();
        return repository.save(mvt);
    }

    /**
     * Valide qu'un mouvement est non-null, référence une boisson et une quantité positive.
     */
    private void validateMouvement(Mouvement m) {
        if (m == null) {
            throw new IllegalArgumentException("Le mouvement ne peut pas etre null");
        }
        if (m.getBoisson() == null) {
            throw new IllegalArgumentException("Le mouvement doit referencer une boisson");
        }
        if (m.getQuantite() <= 0) {
            throw new IllegalArgumentException("La quantite doit être strictement positive");
        }
    }

    /**
     * Version optimisée et robuste de la sortie :
     * - Utilise uniquement les lots vendables et non expirés (filtrage service/DB)
     * - Trie FEFO/FIFO de façon déterministe
     * - Logique extraite pour testabilité
     * - Gère les lots avec la même date de péremption ou d'entrée de façon stable
     * - Exclut explicitement les lots non vendables, expirés, ou à quantité nulle
     */
    private Mouvement processSortieMouvementOptimized(Mouvement sortie) {
        int restant = sortie.getQuantite();
        List<Lot> lots = selectLotsForSortie(sortie.getBoisson().getId());
        if (lots.isEmpty()) {
            log.warn("[SORTIE] Aucun lot disponible/vendable/non expiré pour la boisson {}", sortie.getBoisson().getNom());
            throw new IllegalArgumentException("Aucun lot disponible, vendable et non expiré pour cette boisson");
        }
        for (Lot lot : lots) {
            if (restant <= 0) break;
            int prelev = Math.min(restant, lot.getQuantiteActuelle());
            if (prelev > 0) {
                log.info("[SORTIE] Prélèvement de {} unités sur le lot {} (avant: {}, après: {})", prelev, lot.getId(), lot.getQuantiteActuelle(), lot.getQuantiteActuelle() - prelev);
                Mouvement mvtLot = Mouvement.builder()
                        .type(TypeMouvement.SORTIE)
                        .boisson(sortie.getBoisson())
                        .lot(lot)
                        .utilisateur(sortie.getUtilisateur())
                        .quantite(prelev)
                        .build();
                repository.save(mvtLot);
                lot.setQuantiteActuelle(lot.getQuantiteActuelle() - prelev);
                lotService.updateLot(lot);
                restant -= prelev;
            }
        }
        if (restant > 0) {
            log.error("[SORTIE] StockService insuffisant pour la sortie demandée (manque {}) pour la boisson {}", restant, sortie.getBoisson().getNom());
            throw new IllegalArgumentException("StockService insuffisant pour la sortie demandée (manque " + restant + ")");
        }
        log.info("[SORTIE] Sortie réussie de {} unités pour la boisson {}", sortie.getQuantite(), sortie.getBoisson().getNom());
        return sortie;
    }

    /**
     * Sélectionne les lots valides pour une sortie (vendable, non expiré), triés FEFO/FIFO/id.
     * - Exclut explicitement les lots non vendables, expirés, ou à quantité nulle
     * - Tri stable : datePeremption (nulls last), dateEntree, id
     */
    private List<Lot> selectLotsForSortie(Long boissonId) {
        List<Lot> lots = lotService.getAllLotsByBoisson(boissonId);
        log.info("[DEBUG] Lots récupérés pour la boisson {}: {}", boissonId, lots);
        List<Lot> valides = new ArrayList<>();
        for (Lot lot : lots) {
            log.info("[DEBUG] Lot {} - vendable: {}, expiré: {}, quantité: {}", lot.getId(), lot.isVendable(), lotService.isExpired(lot.getId()), lot.getQuantiteActuelle());
            if (lot.isVendable() && !lotService.isExpired(lot.getId()) && lot.getQuantiteActuelle() > 0) {
                valides.add(lot);
            }
        }
        log.info("[DEBUG] Lots valides après filtrage: {}", valides);
        valides.sort(Comparator
                .comparing(Lot::getDatePeremption, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Lot::getDateEntree)
                .thenComparing(Lot::getId)
        );
        return valides;
    }


    @Override
    public List<Mouvement> getAllMouvementsByBoisson(Long boissonId) {
        return lotService.findByBoissonId(boissonId);
    }

    @Override
    public List<Mouvement> getAllMouvementsByLot(Long lotId) {
        return lotService.findByLotId(lotId);
    }

    @Override
    public List<Mouvement> getAllMouvementsByUtilisateur(Long utilisateurId) {
        return repository.findByUtilisateurId(utilisateurId);
    }
}
