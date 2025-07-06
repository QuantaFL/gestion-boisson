package org.gestion.boisson.features.boissons.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.gestion.boisson.features.boissons.dao.BoissonDao;
import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.utils.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BoissonRepository implements BoissonDao {
    private static final Logger logger = LoggerFactory.getLogger(BoissonRepository.class);
    private EntityManager em = JPAUtil.createEntityManager();
    @Override
    public Boisson getByNom(String nom) {
        try {
            Boisson boisson= em.createNamedQuery("Boisson.findByNom", Boisson.class)
                    .setParameter("nom", nom)
                    .getSingleResultOrNull()
                    ;


            logger.info("Boisson trouvée avec le nom '{}': {}", nom, boisson);
            return boisson;
        } catch (NoResultException e) {
            logger.warn("Aucune boisson trouvée avec le nom '{}'", nom);
            throw  e ;
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de la boisson par nom", e);
            throw e;
        }
    }

    @Override
    public Boisson save(Boisson boisson) {
        return null;
    }

    @Override
    public Boisson update(Boisson boisson) {
        return null;
    }

    @Override
    public List<Boisson> getAll() {
        return List.of();
    }
}
