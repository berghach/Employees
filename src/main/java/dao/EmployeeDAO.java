package dao;

import entities.Department;
import entities.Employee;
import entities.Job;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import org.hibernate.Filter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class EmployeeDAO {

    public void save(Employee employee) throws SystemException {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    public Employee get(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, id);
            Hibernate.initialize(employee.getDepartment());
            Hibernate.initialize(employee.getJob());

            return employee;
        }
    }

    public List<Employee> getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.enableFilter("nameFilter").setParameter("nameFilterParam", name);

            List<Employee> employees = session.createQuery("from Employee",Employee.class).list();

            for(Employee employee : employees){
                Hibernate.initialize(employee.getDepartment());
                Hibernate.initialize(employee.getJob());
            }
            return employees;
        }
    }
    public List<Employee> getByJob(Job job) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.enableFilter("jobFilter").setParameter("jobFilterParam", job.getId());
            return session.createQuery("from Employee", Employee.class).list();
        }
    }
    public List<Employee> getByJob(Department department) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.enableFilter("departmentFilter").setParameter("departmentFilterParam", department.getId());
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    public List<Employee> list() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.list();
        }
    }

    public void update(Employee employee) throws SystemException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(int id) throws SystemException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
