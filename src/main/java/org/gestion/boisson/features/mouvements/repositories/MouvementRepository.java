package org.gestion.boisson.features.mouvements.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.gestion.boisson.features.mouvements.dao.MouvementDao;
import org.gestion.boisson.features.mouvements.entities.Mouvement;
import org.gestion.boisson.utils.JPAUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MouvementRepository implements MouvementDao {
    private EntityManager em = JPAUtil.createEntityManager();

    @Override
    @Transactional
    public Mouvement save(Mouvement mouvement) {
        if (mouvement.getId() == null) {
            em.persist(mouvement);
            return mouvement;
        } else {
            return em.merge(mouvement);
        }
    }

    @Override
    public List<Mouvement> saveAll(List<Mouvement> mouvement) {
        List<Mouvement> mouvementList = new ArrayList<>();
        for (Mouvement m : mouvement) {
            mouvementList.add(save(m));

        }
        return mouvementList;
    }

    @Override
    public Mouvement getById(Long id) {
        return em.find(Mouvement.class, id);
    }

    @Override
    public List<Mouvement> findAll() {
        return em.createQuery("SELECT m FROM Mouvement m", Mouvement.class).getResultList();
    }

    @Override
    public List<Mouvement> findByUtilisateurId(Long utilisateurId) {
        return em.createQuery("SELECT m FROM Mouvement m WHERE m.utilisateur.id = :utilisateurId", Mouvement.class)
                .setParameter("utilisateurId", utilisateurId)
                .getResultList();
    }
}

