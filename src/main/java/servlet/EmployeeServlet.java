package servlet;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.JobDAO;
import entities.Department;
import entities.Employee;
import entities.Job;
import jakarta.transaction.SystemException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = employeeDAO.list();
        request.setAttribute("employees", employeeList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        JobDAO jobDAO = new JobDAO();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Department department = departmentDAO.get(Integer.parseInt(request.getParameter("department_id")));
        Job job = jobDAO.get(Integer.parseInt(request.getParameter("job_id")));


        Employee employee = new Employee(name, email, phone, department, job);

        try {
            employeeDAO.save(employee);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("employees");
    }
}
