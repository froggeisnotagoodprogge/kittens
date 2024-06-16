package org.example.DataAccessLayer;

import org.example.entities.Owner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDAOHibernate {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public void create(Owner owner) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            session.save(owner);
            trans.commit();
            session.close();
        } catch (HibernateException var4) {
            System.out.print("Error while attempt to open session.");
            throw var4;
        }
    }

    
    public Owner read(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            Owner owner = session.get(Owner.class, id);
            trans.commit();
            session.close();
            return owner;
        }
        catch (HibernateException var6) {
            System.out.print("Error while attempt to open session.");
            throw var6;
        }
    }

    
    public void update(Owner owner) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            session.update(owner);
            trans.commit();
            session.close();
        } catch (HibernateException var4) {
            System.out.print("Error while attempt to open session.");
            throw var4;
        }
    }

    
    public void delete(Owner owner) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            session.delete(owner);
            trans.commit();
            session.close();
        } catch (HibernateException var4) {
            System.out.print("Error while attempt to open session.");
            throw var4;
        }
    }

    
    public List<Owner> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Owner", Owner.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
