package org.gestion.boisson.features.lots.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.lots.dao.LotDao;
import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.utils.JPAUtil;

import java.util.List;
@Slf4j
public class LotRepository  implements LotDao {
    private EntityManager em = JPAUtil.createEntityManager();

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

    @Override
    public Lot getById(Long id) {
        return null;
    }

    @Override
    public Lot updateLot(Lot lot) {
        return null;
    }

    @Override
    public List<Lot> getAllLots() {
        return List.of();
    }

    @Override
    public List<Lot> getLotsByBoissonId(Long boissonId) {
        return List.of();
    }

    @Override
    public Lot getByNumero(String numero) {
        return null;
    }
}
