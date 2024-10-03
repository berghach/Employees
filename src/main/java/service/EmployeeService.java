package service;

import dao.EmployeeDAO;
import entities.Department;
import entities.Employee;
import entities.Job;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class EmployeeService implements Services<Employee> {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public Optional<Employee> get(long id) {
        return employeeDAO.get(id);
    }

    public List<Employee> getByName(String name) {
        return employeeDAO.getByName(name);
    }

    public List<Employee> getByJob(Job job) {
        return employeeDAO.getByJob(job);
    }

    public List<Employee> getByDepartment(Department department) {
        return employeeDAO.getByDepartment(department);
    }


    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public boolean save(Employee employee) {
        try {
            employeeDAO.save(employee);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Employee employee) {
        try {
            employeeDAO.update(employee);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Employee employee) {
        try {
            return employeeDAO.delete(employee);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }

}
