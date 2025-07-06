package org.gestion.boisson.features.lots.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.features.lots.repository.LotRepository;
import org.gestion.boisson.features.lots.services.LotService;
import org.gestion.boisson.features.mouvements.entities.Mouvement;
import org.gestion.boisson.utils.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;
@Named
@ApplicationScoped
@Slf4j
public class LotServiceImpl implements LotService {
    @Inject
    private LotRepository lotRepository;


    @Override
    public boolean addQuantityToLot(Long lotId, int quantity) {
        return false;
    }

    @Override
    public boolean removeQuantityFromLot(Long lotId, int quantity) {
        try {
            Lot lot = lotRepository.getById(lotId);
            if (lot == null || !lot.isVendable()) {
                log.warn("Lot introuvable ou non vendable pour la suppression de quantité. ID : {}", lotId);
                return false;
            }

            int nouvelleQuantite = lot.getQuantiteActuelle() - quantity;
           /*
           *  if (nouvelleQuantite < 0) {
                log.warn("si on retire cette quantité le lot sera vide. Lot : {}", lot);
                return false;
            }
           * */
            if(lot.getQuantiteActuelle()<quantity) {
                log.warn("La quantité à retirer est supérieure à la quantité actuelle du lot. Lot : {}, Quantité actuelle : {}, Quantité à retirer : {}", lot, lot.getQuantiteActuelle(), quantity);
                return false;
            }

            lot.setQuantiteActuelle(nouvelleQuantite);
            lotRepository.updateLot(lot);
            log.info("Quantité retirée du lot {} : -{}", lotId, quantity);
            return true;
        } catch (Exception e) {
            log.error("Erreur lors du retrait de quantité du lot {}", lotId, e);
            return false;
        }
    }

    @Override
    public boolean isExpired(Long lotId) {
        try {
            Lot lot = lotRepository.getById(lotId);
            boolean expired = lot != null && lot.getDatePeremption().isBefore(LocalDateTime.now()); // Vérifie si la date de péremption est passée par rapport à la date actuelle; Est-ce que la date de péremption est dans le passé par rapport à maintenant ?
            log.info("Expiration du lot {} : {}", lotId, expired);
            return expired;
        } catch (Exception e) {
            log.error("Erreur lors de la vérification de l'expiration du lot {}", lotId, e);
            return false;
        }
    }

    @Override
    public boolean isLotAvailable(Long lotId) {
        try {
            Lot lot = lotRepository.getById(lotId);
            boolean available = lot != null && lot.isVendable() && lot.getQuantiteActuelle() > 0;
            log.info("Disponibilité du lot {} : {}", lotId, available);
            return available;
        } catch (Exception e) {
            log.error("Erreur lors de la vérification de disponibilité du lot {}", lotId, e);
            return false;
        }
    }

    @Override
    public List<Lot> getAllLotsByBoisson(Long boissonId) {
        try {
            List<Lot> lots = lotRepository.getLotsByBoissonId(boissonId);
            log.info("Récupération de {} lots pour la boisson ID {}", lots.size(), boissonId);
            return lots;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des lots pour la boisson {}", boissonId, e);
            throw e;
        }
    }

    @Override
    public List<Lot> getAllLotOrderByExpirationDate(Long boissonId) {
        try {
            return getAllLotsByBoisson(boissonId).stream()
                    .sorted((l1, l2) -> l1.getDatePeremption().compareTo(l2.getDatePeremption()))
                    .toList();
        } catch (Exception e) {
            log.error("Erreur lors du tri des lots par date d'expiration pour la boisson {}", boissonId, e);
            throw e;
        }
    }

    @Override
    public boolean updateLot(Lot lot) {
        try {
            Lot updated = lotRepository.updateLot(lot);
            log.info("Lot mis à jour avec succès : {}", updated);
            return true;
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du lot {}", lot, e);
            return false;
        }
    }

    @Override
    public List<Lot> getAllLotsDisponibles() {
        try {
            List<Lot> lots = lotRepository.getAllLots();
            List<Lot> disponibles = lots.stream()
                    .filter(lot -> lot.isVendable() && lot.getQuantiteActuelle() > 0)
                    .toList();
            log.info("Récupération des lots disponibles : {} éléments", disponibles.size());
            return disponibles;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des lots disponibles", e);
            throw e;
        }
    }

    @Override
    public List<Mouvement> findByBoissonId(Long boissonId) {
        return List.of();
    }

    @Override
    public List<Mouvement> findByLotId(Long lotId) {
        return List.of();
    }



    @Override
    public void save(Lot lot) {
        try {
            lotRepository.saveLot(lot);
            log.info("Lot enregistré avec succès : {}", lot);
        } catch (Exception e) {
            log.error("Erreur lors de l'enregistrement du lot", e);
            throw e;
        }
    }
}
