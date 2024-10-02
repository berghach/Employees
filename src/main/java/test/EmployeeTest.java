package test;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.JobDAO;
import entities.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeTest {
    public static void main(String[] args){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        JobDAO jobDAO = new JobDAO();

        Employee employee = employeeDAO.get(1);
        System.out.println(employee.toString());

        List<Employee> rs = employeeDAO.getByName("David Wilson");
        System.out.println(rs.get(0).toString());


    }
}
