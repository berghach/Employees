package dao;

import entities.Job;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class JobDAO implements DAO<Job>{
    @Override
    public void save(Job job) throws SystemException {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.save(job);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Job> get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Job.class, id));
        }
    }

    @Override
    public List<Job> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Job> query = session.createQuery("from Job", Job.class);
            return query.list();
        }
    }

    @Override
    public void update(Job job) throws SystemException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.update(job);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean delete(Job job) throws SystemException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(job);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
