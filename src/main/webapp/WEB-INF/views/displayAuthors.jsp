<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Authors</title>
</head>
<body>
<table>
    <tr>
        <td style="vertical-align: top">
            <table>
                <form:form method="post" modelAttribute="author">
                    <tr><td>firstName:</td><td><form:input path="firstName" /></td></tr>
                    <tr><td>lastName:</td><td><form:input path="lastName" /></td></tr>
                    <tr><td></td><td><button type="submit" style="float: right;">Submit</button> </td></tr>
                </form:form>
            </table>
        </td>
        <td>
            <table>
                <c:forEach items="${authors}" var="author">
                    <tr>
                        <td>
                            Author id: ${author.id}<br>
                            Name: ${author.firstName} ${author.lastName}<br>
                            Articles:<br>
                            <c:forEach items="${author.articles}" var="article">
                            ${article.title} <br> </c:forEach>
                            <br>
                        </td>
                        <td style="vertical-align: middle">
                            <a href="${pageContext.request.contextPath}/authors/update/${author.id}">Edytuj</a><br>
                            <a href="${pageContext.request.contextPath}/authors/delete/${author.id}" onclick="confirm('Czy na pewno chcesz usunąć autora?')">Usuń</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>

    </tr>
</table>
</body>
</html>
