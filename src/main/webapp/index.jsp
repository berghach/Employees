<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.Employee" %>
<%@ page import="entities.Department" %>
<%@ page import="entities.Job" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h1>Employees</h1>

    <form action="/" method="post">
        <input type="text" name="name" placeholder="Name" required/>
        <input type="email" name="email" placeholder="Email" required/>
        <input type="text" name="phone" placeholder="Phone" maxlength="10" size="10" required/>
        <select id="job" name="job">
                    <option value="Job" selected disabled>Job</option>
                    <%
                        List<Job> jobs = (List<Job>) request.getAttribute("jobs");
                        if (jobs != null) {
                                        for (Job job : jobs) {
                    %>
                    <option value=<%= job.getId() %>><%= job.getName() %></option>
                    <%
                            }
                        }
                    %>
                </select>
        <select id="department" name="department">
            <option value="Department" selected disabled>Department</option>
            <%
                List<Department> departments = (List<Department>) request.getAttribute("departments");
                if (departments != null) {
                                for (Department dep : departments) {
            %>
            <option value=<%= dep.getId() %>><%= dep.getName() %></option>
            <%
                    }
                }
            %>
        </select>
        <button type="submit">Add Employee</button>
    </form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Job</th>
            <th>Department</th>
        </tr>
        <%
            List<Employee> employees = (List<Employee>) request.getAttribute("employees");
            if (employees != null) {
                for (Employee emp : employees) {
        %>
        <tr>
            <td><%= emp.getId() %></td>
            <td><%= emp.getName() %></td>
            <td><%= emp.getEmail() %></td>
            <td><%= emp.getPhone() %></td>
            <td><%= emp.getJob().getName() %></td>
            <td><%= emp.getDepartment().getName() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
