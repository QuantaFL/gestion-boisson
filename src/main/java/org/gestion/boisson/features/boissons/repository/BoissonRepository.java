package org.gestion.boisson.features.boissons.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.boissons.dao.BoissonDao;
import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.utils.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Slf4j
public class BoissonRepository implements BoissonDao {

   // private static final Logger logger = LoggerFactory.getLogger(BoissonRepository.class);
    private EntityManager em = JPAUtil.createEntityManager();
    /**
     * Retrieves a beverage by its name.
     *
     * @param nom the name of the beverage
     * @return the beverage entity, or null if not found
     */
    @Override
    public Boisson getByNom(String nom) {
        try {
            Boisson boisson= em.createNamedQuery("Boisson.findByNom", Boisson.class)
                    .setParameter("nom", nom)
                    .getSingleResultOrNull()
                    ;


            log.info("Boisson trouvée avec le nom '{}': {}", nom, boisson);
            return boisson;
        } catch (NoResultException e) {
            log.warn("Aucune boisson trouvée avec le nom '{}'", nom);
            return  null;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la boisson par nom", e);
            throw e;
        }
    }


    @Override
    public Boisson save(Boisson boisson) {
        try {
            em.getTransaction().begin();
            if (getByNom(boisson.getNom().trim()) == null) {
                em.persist(boisson);
                log.info("Boisson créée: {}", boisson);
            } else {
                boisson = em.merge(boisson);
                log.info("Boisson mise à jour: {}", boisson);
            }
            em.getTransaction().commit();
            return boisson;
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("Erreur lors de la sauvegarde de la boisson", e);
            throw e;
        }
    }
    /*
    * @Override
    @Transactional
    public Boisson save(Boisson boisson) {
        try {
            if (getByNom(boisson.getNom()) == null) {
                em.persist(boisson);
                log.info("Boisson créée: {}", boisson);
            } else {
                boisson = em.merge(boisson);
                log.info("Boisson mise à jour: {}", boisson);
            }
            return boisson;
        } catch (Exception e) {
            log.error("Erreur lors de la sauvegarde de la boisson", e);
            throw e;
        }
    }

    *
    * */


    @Override
    public Boisson update(Boisson boisson) {
        return null;
    }

    @Override
    public List<Boisson> getAll() {
        try {
            List<Boisson> list = em.createNamedQuery("Boisson.findAll", Boisson.class).getResultList();
            log.info("Récupération de toutes les boissons : {} éléments", list.size());
            return list;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de toutes les boissons", e);
            throw e;
        }
    }
}
