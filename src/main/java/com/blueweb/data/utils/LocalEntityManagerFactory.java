
package com.blueweb.data.utils;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Blueweb
 */
@WebListener
public class LocalEntityManagerFactory implements ServletContextListener {
    
        private static EntityManagerFactory emf;

@Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Creando EntityManagerFactory");
        emf = Persistence.createEntityManagerFactory("AppMaven");

    }
@Override
    public void contextDestroyed(ServletContextEvent sce) {
           System.out.println("Cerrando EntityManagerFactory");
        emf.close();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf;

    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
    
}
