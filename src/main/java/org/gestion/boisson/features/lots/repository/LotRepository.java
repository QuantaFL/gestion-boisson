package org.gestion.boisson.features.lots.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.lots.dao.LotDao;
import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.utils.JPAUtil;

import java.util.List;

/**
 * Repository for Lot entities.
 */
@Slf4j
public class LotRepository  implements LotDao {
    private EntityManager em = JPAUtil.createEntityManager();

    /**
     * {@inheritDoc}
     */
    @Override
    public Lot saveLot(Lot lot) {
        try {
            em.getTransaction().begin();
            em.persist(lot);
            em.getTransaction().commit();
            log.info("Lot enregistré avec succès : {}", lot);
            return lot;
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("Erreur lors de la sauvegarde du lot", e);
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lot getById(Long id) {
        try {
            Lot lot = em.find(Lot.class, id);
            if (lot == null) {
                log.warn("Aucun lot trouvé avec l'ID {}", id);
                return null;
            } else {
                log.info("Lot récupéré par ID : {}", lot);
                return lot;
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du lot par ID", e);
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lot updateLot(Lot lot) {
        if (lot.getId() == null) {
            log.warn("Impossible de mettre à jour le lot car l'ID est null");
            return null;
        }
        try {
            em.getTransaction().begin();
            Lot updated = em.merge(lot);
            em.getTransaction().commit();
            log.info("Lot mis à jour avec succès : {}", updated);
            return updated;
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("Erreur lors de la mise à jour du lot", e);
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Lot> getAllLots() {
        try {
            List<Lot> lots = em.createNamedQuery("Lot.findAll", Lot.class).getResultList();
            log.info("Récupération de tous les lots : {} éléments", lots.size());
            return lots;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de tous les lots", e);
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Lot> getLotsByBoissonId(Long boissonId) {
        try {
            List<Lot> lots = em.createNamedQuery("Lot.findDisponiblesByBoisson", Lot.class)
                    .setParameter("boissonId", boissonId)
                    .getResultList();
            log.info("Lots disponibles pour la boisson ID {} : {} éléments", boissonId, lots.size());
            return lots;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des lots disponibles pour la boisson", e);
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lot getByNumero(String numero) {
        try {
            Lot lot = em.createNamedQuery("Lot.findByNumeroLot", Lot.class)
                    .setParameter("numeroLot", numero)
                    .getSingleResultOrNull();
            log.info("Lot trouvé avec le numéro '{}': {}", numero, lot);
            return lot;
        } catch (NoResultException e) {
            log.warn("Aucun lot trouvé avec le numéro '{}'", numero);
            return null;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du lot par numéro", e);
            throw e;
        }
    }
}
