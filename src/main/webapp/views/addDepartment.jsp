<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee List</title>
     <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
    <h1>Add Department</h1>
        <form action="/department" method="post">

            <input type="text" name="name" placeholder="Name" required/>

            <button type="submit">Add Department</button>
        </form>
</body>
</html>