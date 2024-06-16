package org.example.DataAccessLayer;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();

    public HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return (new Configuration()).configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.print("Error while attempt to build sessionFactory.");
            throw ex;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
