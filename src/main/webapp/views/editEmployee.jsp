<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Employee <c:out value="${employee.name}"/></title>
</head>
<body>
    <h1>Edit Employee: <c:out value="${employee.name}"/></h1>
    <form action="/employees?id=${employee.id}" method="post">
        <input type="hidden" name="_method" value="PUT"/>
        <input type="hidden" name="id" value="${employee.id}" required/>
        <input type="text" name="name" placeholder="Name" value="${employee.name}" required/>
        <input type="email" name="email" placeholder="Email" value="${employee.email}" required/>
        <input type="text" name="phone" placeholder="Phone" maxlength="10" size="10" value="${employee.phone}" required/>

       <select id="job" name="job">
           <option value="Job" selected disabled>Job</option>
           <c:forEach var="job" items="${jobs}">
               <option value="${job.id}" <c:if test="${job.id == employee.job.id}">selected</c:if>>${job.name}</option>
           </c:forEach>
       </select>

       <select id="department" name="department">
           <option value="Department" selected disabled>Department</option>
           <c:forEach var="department" items="${departments}">
               <option value="${department.id}" <c:if test="${department.id == employee.department.id}">selected</c:if>>${department.name}</option>
           </c:forEach>
       </select>

        <button type="submit">Edit Employee</button>
    </form>

</body>
</html>