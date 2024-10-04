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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        String action = req.getParameter("action");
        if (action == null) {
            showAllEmployees(req, resp);
            return;
        }
        switch(action){
            case "add":
                showAddEmployee(req, resp);
                break;
            case "edit":
                ShowEditEmployee(req, resp);
                break;
            default:
                showAllEmployees(req, resp);

        }
    }
    private void showAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeService.getAll();

        String searchQuery = req.getParameter("searchQuery");
        String departmentQuery = req.getParameter("department");
        String jobQuery = req.getParameter("job");

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            employeeList = employeeService.searchEmployees(searchQuery);
            if (employeeList.isEmpty()) {
                req.setAttribute("message", "No employees match your search query.");
            }
        }

        if (departmentQuery != null && !departmentQuery.trim().isEmpty()) {
            employeeList = employeeService.filterByDepartment(departmentQuery);
            if (employeeList.isEmpty()) {
                req.setAttribute("message", "No employees found in the selected department.");
            }
        }

        if (jobQuery != null && !jobQuery.trim().isEmpty()) {
            employeeList = employeeService.filterByJob(jobQuery);
            if (employeeList.isEmpty()) {
                req.setAttribute("message", "No employees found with the selected job.");
            }
        }

        req.setAttribute("employees", employeeList);

        List<Department> departmentList = departmentService.getAll();
        req.setAttribute("departments", departmentList);

        List<Job> jobList = jobService.getAll();
        req.setAttribute("jobs", jobList);

        req.getRequestDispatcher("views/index.jsp").forward(req, resp);
    }


    private void showAddEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Employee> employeeList = employeeService.getAll();
        List<Department> departmentList = departmentService.getAll();
        List<Job> jobList = jobService.getAll();
        req.setAttribute("employees", employeeList);
        req.setAttribute("departments", departmentList);
        req.setAttribute("jobs", jobList);
        req.getRequestDispatcher("views/addEmployee.jsp").forward(req, resp);
    }
    private void ShowEditEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Department> departmentList = departmentService.getAll();
        List<Job> jobList = jobService.getAll();
        req.setAttribute("departments", departmentList);
        req.setAttribute("jobs", jobList);
        String idParm = req.getParameter("id");
        if (idParm != null) {
            long id = Integer.parseInt(idParm);
            Optional<Employee> employee = employeeService.get(id);
            if (employee.isPresent()) {
                req.setAttribute("employee", employee.get());
                req.getRequestDispatcher("views/editEmployee.jsp").forward(req, resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not found");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");

        switch (method != null ? method.toUpperCase() : "") {
            case "PUT":
                doPut(req, resp);
                break;
            case "DELETE":
                doDelete(req, resp);
                break;
            case "POST":
            default:
                createEmployee(req, resp);
                break;
        }
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Optional<Department> department = departmentService.get(Integer.parseInt(req.getParameter("department")));
            Optional<Job> job = jobService.get(Integer.parseInt(req.getParameter("job")));

            Employee employee = new Employee(name, email, phone, department.orElse(null), job.orElse(null));
            employeeService.save(employee);
            resp.sendRedirect("/");
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
                Optional<Department> department = departmentService.get(Integer.parseInt(req.getParameter("department")));
                Optional<Job> job = jobService.get(Integer.parseInt(req.getParameter("job")));

                employee.setName(name);
                employee.setEmail(email);
                employee.setPhone(phone);
                employee.setDepartment(department.orElse(null));
                employee.setJob(job.orElse(null));

                employeeService.update(employee);
                resp.sendRedirect("/");
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
                resp.sendRedirect("/");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete employee");
        }
    }

}
