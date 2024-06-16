package org.example.DataAccessLayer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Cat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CatDAOHibernate {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void create(Cat cat) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            session.save(cat);
            trans.commit();
            session.close();
        } catch (HibernateException var4) {
            System.out.print("Error while attempt to open session.");
            throw var4;
        }
    }

    public Cat read(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            Cat cat = session.get(Cat.class, id);
            trans.commit();
            session.close();
            return cat;
        }
        catch (HibernateException var6) {
            System.out.print("Error while attempt to open session.");
            throw var6;
        }
    }

    public void update(Cat cat) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            session.update(cat);
            trans.commit();
            session.close();
        } catch (HibernateException var4) {
            System.out.print("Error while attempt to open session.");
            throw var4;
        }
    }

    public void delete(Cat cat) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            session.delete(cat);
            trans.commit();
            session.close();
        } catch (HibernateException var4) {
            System.out.print("Error while attempt to open session.");
            throw var4;
        }
    }

    public List<Cat> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cat", Cat.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Cat> getCatFriends(Cat cat) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Cat> criteria = builder.createQuery(Cat.class);
            Root<Cat> root = criteria.from(Cat.class);
            criteria.select(root).where(builder.isMember(cat, root.get("friends")));
            return session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
