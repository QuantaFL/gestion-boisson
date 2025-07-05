package org.gestion.boisson.features.lots.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.features.lots.services.LotService;
import org.gestion.boisson.features.mouvements.entities.Mouvement;
import org.gestion.boisson.utils.JPAUtil;

import java.util.List;
@Named
@ApplicationScoped
public class LotServiceImpl implements LotService {

    private EntityManager em = JPAUtil.createEntityManager();

    @Override
    public boolean addQuantityToLot(Long lotId, int quantity) {
        return false;
    }

    @Override
    public boolean removeQuantityFromLot(Long lotId, int quantity) {
        return false;
    }

    @Override
    public boolean isExpired(Long lotId) {
        return false;
    }

    @Override
    public boolean isLotAvailable(Long lotId) {
        return false;
    }

    @Override
    public List<Lot> getAllLotsByBoisson(Long boissonId) {
        return em.createQuery("SELECT l FROM Lot l WHERE l.boisson.id = :boissonId", Lot.class)
                .setParameter("boissonId", boissonId)
                .getResultList();
    }

    @Override
    public List<Lot> getAllLotOrderByExpirationDate(Long boissonId) {
        return em.createQuery("SELECT l FROM Lot l WHERE l.boisson.id = :boissonId ORDER BY l.dateExpiration", Lot.class)
                .setParameter("boissonId", boissonId)
                .getResultList();
    }

    @Override
    public boolean updateLot(Lot lot) {
        return false;
    }

    @Override
    public List<Lot> getAllLotsDisponibles() {
        return em.createQuery("SELECT l FROM Lot l WHERE l.quantiteDisponible > 0 AND l.vendable = true", Lot.class)
                .getResultList();
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
        if (lot.getId() == null) {
            em.persist(lot);
        } else {
            em.merge(lot);
        }
    }
}
