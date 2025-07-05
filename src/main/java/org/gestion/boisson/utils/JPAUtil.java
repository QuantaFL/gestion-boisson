package org.gestion.boisson.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Utility class for managing JPA EntityManagerFactory and EntityManager.
 * This class provides methods to create and manage the EntityManagerFactory,
 * as well as to create EntityManager instances.
 */

public class JPAUtil {


    private static EntityManagerFactory em;

    static {
        try {
            em = Persistence.createEntityManagerFactory("oulyPU");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex.getMessage());
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets the EntityManagerFactory.
     *
     * @return EntityManagerFactory instance.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return em;
    }

    /**
     * Creates and returns an EntityManager.
     * Remember to close it after use.
     *
     * @return EntityManager instance.
     */
    public static EntityManager createEntityManager() {
        return em.createEntityManager();
    }

    /**
     * Closes the EntityManagerFactory when the application is shutting down.
     * This method should be called, for example, in a ServletContextListener's contextDestroyed method.
     */
    public static void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
