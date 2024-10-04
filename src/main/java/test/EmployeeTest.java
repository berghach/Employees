package test;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.JobDAO;
import entities.Department;
import entities.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeTest {
   /* public static void main(String[] args){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        JobDAO jobDAO = new JobDAO();

        Optional<Employee> employee = employeeDAO.get(1);
        employee.ifPresent(value -> System.out.println(value.toString()));

        List<Employee> rs = employeeDAO.getByName("David Wilson");
        System.out.println(rs.get(0).toString());

        Optional<Department> department = departmentDAO.get(1);
        assert department.orElse(null) != null;
        List<Employee> hr = employeeDAO.getByDepartment(department.get());
        for(Employee item : hr){
            System.out.println(item.toString());
        }

    }*/
}
