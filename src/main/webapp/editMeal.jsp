<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="POST" action='${pageContext.request.contextPath}/meals' name="frmAddMeal">

    <input type="text" hidden name="id" value="<c:out value="${meal.id}" />"/>

    DateTime : <input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}" />" required="required"/> <br/>

    Description : <input type="text" name="description" value="<c:out value="${meal.description}" />"/> <br/>

    Calories : <input type="number" name="calories" required="required" value="<c:out value="${meal.calories}" />"/> <br/>

    <br/> <input type="submit" value="Save"/> <input type="reset" value="Cancel"/>
</form>
</body>
</html>
