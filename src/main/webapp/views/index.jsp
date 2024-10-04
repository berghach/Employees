    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
    <head>
        <title>Employee List</title>
    </head>
    <body>
        <h1>Employees</h1>

        <!-- Search and Filter Form
            <form action="/employees?action=search" method="get">
                <input type="text" name="searchQuery" placeholder="Search" value="${param.searchName}"/>
                <button type="submit">Search</button>
            </form>-->

<form action="/employees" method="get">
    <input type="text" name="searchQuery" placeholder="Search by name, email" value="${param.searchQuery}"/>

    <!-- Department Filter -->
    <select name="department">
        <option value="">All Departments</option>
        <c:forEach var="department" items="${departments}">
            <option value="${department.name}" ${param.department == department.name ? 'selected' : ''}>${department.name}</option>
        </c:forEach>
    </select>

    <!-- Job Filter -->
    <select name="job">
        <option value="">All Jobs</option>
        <c:forEach var="job" items="${jobs}">
            <option value="${job.name}" ${param.job == job.name ? 'selected' : ''}>${job.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Search</button>
</form>
        <a href="/employees?action=add">Add Employee</a>

            <table border="1">
               <tr>
                   <th>ID</th>
                   <th>Name</th>
                   <th>Email</th>
                   <th>Phone</th>
                   <th>Job</th>
                   <th>Department</th>
                       <th>Actions</th>
               </tr>

               <c:forEach var="employee" items="${employees}">
                   <tr>
                       <td>${employee.id}</td>
                       <td>${employee.name}</td>
                       <td>${employee.email}</td>
                       <td>${employee.phone}</td>
                       <td>${employee.job.name}</td>
                       <td>${employee.department.name}</td>
                       <td>
                            <a href="/employees?action=edit&id=${employee.id}">Edit</a>
                            <form action="/employees?id=${employee.id}" method="post" style="display:inline;">
                                <input type="hidden" name="_method" value="DELETE"/>
                                <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this employee?');" />
                            </form>
                       </td>
                   </tr>
               </c:forEach>
           </table>


    </body>
    </html>
