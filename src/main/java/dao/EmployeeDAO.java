package dao;

import entities.Department;
import entities.Employee;
import entities.Job;

import jakarta.transaction.SystemException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements DAO<Employee>{

    @Override
    public void save(Employee employee) throws SystemException {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Employee> get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return Optional.ofNullable(session.get(Employee.class, id));
        }
    }

    public List<Employee> getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.enableFilter("nameFilter").setParameter("nameFilterParam", name);

            return session.createQuery("from Employee", Employee.class).list();
        }
    }
    public List<Employee> getByJob(Job job) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.enableFilter("jobFilter").setParameter("jobFilterParam", job.getId());

            return session.createQuery("from Employee",Employee.class).list();
        }
    }
    public List<Employee> getByDepartment(Department department) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.enableFilter("departmentFilter").setParameter("departmentFilterParam", department.getId());

            return session.createQuery("from Employee",Employee.class).list();
        }
    }

    @Override
    public List<Employee> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.list();
        }
    }

    @Override
    public void update(Employee employee) throws SystemException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Employee employee) throws SystemException{
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
