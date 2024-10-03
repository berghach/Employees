package servlet;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.JobDAO;
import entities.Department;
import entities.Employee;
import entities.Job;
import jakarta.transaction.SystemException;
import service.DepartmentService;
import service.EmployeeService;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private JobService jobService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        departmentService = new DepartmentService();
        jobService = new JobService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeService.getAll();
        req.setAttribute("employees", employeeList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Optional<Department> department = departmentService.get(Integer.parseInt(req.getParameter("department_id")));
            Optional<Job> job = jobService.get(Integer.parseInt(req.getParameter("job_id")));

            Employee employee = new Employee(name, email, phone, department.orElse(null), job.orElse(null));
            employeeService.save(employee);
            resp.sendRedirect("employees");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create employee");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Employee> employeeOptional = employeeService.get(id);

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                Optional<Department> department = departmentService.get(Integer.parseInt(req.getParameter("department_id")));
                Optional<Job> job = jobService.get(Integer.parseInt(req.getParameter("job_id")));

                employee.setName(name);
                employee.setEmail(email);
                employee.setPhone(phone);
                employee.setDepartment(department.orElse(null));
                employee.setJob(job.orElse(null));

                employeeService.update(employee);
                resp.sendRedirect("employees");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update employee");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Employee> employeeOptional = employeeService.get(id);

            if (employeeOptional.isPresent()) {
                employeeService.delete(employeeOptional.get());
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete employee");
        }
    }

}
