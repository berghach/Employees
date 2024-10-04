<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
    <h1>Add Employee</h1>
    <form action="/employees" method="post">
        <input type="text" name="name" placeholder="Name" required/>
        <input type="email" name="email" placeholder="Email" required/>
        <input type="text" name="phone" placeholder="Phone" maxlength="10" size="10" required/>

       <select id="job" name="job">
           <option value="Job" selected disabled>Job</option>
           <c:forEach var="job" items="${jobs}">
               <option value="${job.id}">${job.name}</option>
           </c:forEach>
       </select>

       <select id="department" name="department">
           <option value="Department" selected disabled>Department</option>
           <c:forEach var="department" items="${departments}">
               <option value="${department.id}">${department.name}</option>
           </c:forEach>
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

       <c:forEach var="employee" items="${employees}">
           <tr>
               <td>${employee.id}</td>
               <td>${employee.name}</td>
               <td>${employee.email}</td>
               <td>${employee.phone}</td>
               <td>${employee.job.name}</td>
               <td>${employee.department.name}</td>
           </tr>
       </c:forEach>
   </table>

</body>
</html>