package service;

import dao.EmployeeDAO;
import entities.Department;
import entities.Employee;
import entities.Job;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService implements Services<Employee> {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final DepartmentService departmentService = new DepartmentService();
    private final JobService jobService = new JobService();

    @Override
    public Optional<Employee> get(long id) {
        return employeeDAO.get(id);
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

    //Search and Filter methods
    public List<Employee> searchEmployees(String searchQuery) {
        return employeeDAO.getAll().stream()
                .filter(e -> e.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        e.getEmail().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        e.getDepartment().getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        e.getJob().getName().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Employee> filterByDepartment(String departmentName) {
        return employeeDAO.getAll().stream()
                .filter(e -> e.getDepartment().getName().equalsIgnoreCase(departmentName))
                .collect(Collectors.toList());
    }

    public List<Employee> filterByJob(String jobName) {
        return employeeDAO.getAll().stream()
                .filter(e -> e.getJob().getName().equalsIgnoreCase(jobName))
                .collect(Collectors.toList());
    }


}
