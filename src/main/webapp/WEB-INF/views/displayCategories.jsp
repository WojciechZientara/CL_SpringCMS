<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Categories</title>
</head>
<body>
<table>
    <tr>
        <td style="vertical-align: top">
            <table>
                <form:form method="post" modelAttribute="category">
                    <tr><td>name:</td><td><form:input path="name" /></td></tr>
                    <tr><td>description:</td><td><form:textarea path="description" rows="3" cols="20"/></td></tr>
                    <tr><td></td><td><button type="submit" style="float: right;">Submit</button> </td></tr>
                </form:form>
            </table>
        </td>
        <td>
            <table>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>
                            Category id: ${category.id}<br>
                            Name: ${category.name}<br>
                            Description: ${category.description}<br>
                            Articles:<br>
                            <c:forEach items="${category.articles}" var="article">
                            ${article.author.firstName} ${article.author.lastName}: ${article.title} <br> </c:forEach>
                            <br>
                        </td>
                        <td style="vertical-align: middle">
                            <a href="${pageContext.request.contextPath}/categories/update/${category.id}">Edytuj</a><br>
                            <a href="${pageContext.request.contextPath}/categories/delete/${category.id}" onclick="confirm('Czy na pewno chcesz usunąć kategorię?')">Usuń</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>

    </tr>
</table>
</body>
</html>
