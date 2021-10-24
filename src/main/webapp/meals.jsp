<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="editMeal.jsp">Add Meal</a></p>
<jsp:useBean id="mealsTos" scope="request" type="java.util.List"/>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="mealTo" items="${mealsTos}">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <c:if test="${mealTo.excess}">
        <tr style="color: red">
        </c:if>
        <c:if test="${!mealTo.excess}">
            <tr style="color: green">
        </c:if>
            <td>${mealTo.formattedDateTime}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="${pageContext.request.contextPath}/meals?action=edit&id=${mealTo.id}">Update</a></td>
            <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=${mealTo.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
