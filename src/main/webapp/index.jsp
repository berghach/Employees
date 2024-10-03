<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.Employee" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h1>Employees</h1>

    <form action="employees" method="post">
        <input type="text" name="name" placeholder="Name" required/>
        <input type="email" name="email" placeholder="Email" required/>
        <input type="text" name="phone" placeholder="Phone" required/>
        <button type="submit">Add Employee</button>
    </form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
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
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
