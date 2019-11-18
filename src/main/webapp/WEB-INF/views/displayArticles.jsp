<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Articles</title>
    <style> <%@ include file="/assets/style.css" %> </style>
</head>
<body>
<table>
    <tr>
        <td style="vertical-align: top">
            <table>
                <form:form method="post" modelAttribute="article">
                    <tr><td>title:</td><td><form:input path="title" />
                        <br><form:errors path="title" cssClass="error" /></td></tr>
                    <tr><td>author:</td><td><form:select path="author" items="${authors}"
                                    itemValue="id" itemLabel="lastName" />
                        <br><form:errors path="author" cssClass="error" /></td></tr>
                    <tr><td>categories:</td><td><form:select path="categories" items="${categories}"
                                                         itemValue="id" itemLabel="name" />
                        <br><form:errors path="categories" cssClass="error" /></td></tr>
                    <tr><td>content:</td><td><form:textarea cols="40" rows="10" path="content" />
                        <br><form:errors path="content" cssClass="error" /></td></tr>
                    <tr><td></td><td><button type="submit" style="float: right;">Submit</button> </td></tr>
                </form:form>
            </table>
        </td>
        <td>
            <table>
                <c:forEach items="${articles}" var="article">
                    <tr>
                        <td>
                            Article id: ${article.id}<br>
                            Title: ${article.title}<br>
                            <c:choose>
                                <c:when test = "${article.updated == null}">
                                    <fmt:parseDate value="${article.created}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                    Created: <fmt:formatDate pattern="dd MMMM yyyy HH:mm" value="${parsedDateTime}" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:parseDate value="${article.updated}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                    Updated: <fmt:formatDate pattern="dd MMMM yyyy HH:mm" value="${parsedDateTime}" />
                                </c:otherwise>
                            </c:choose> <br>
                            Author: ${article.author.firstName} ${article.author.lastName}<br>
                            Categories:<br>
                            <c:forEach items="${article.categories}" var="category">${category.name}; </c:forEach><br>
                            Content:<br>
                                ${article.content}<br><br>
                        </td>
                        <td style="vertical-align: middle">
                            <a href="${pageContext.request.contextPath}/articles/update/${article.id}">Edytuj</a><br>
                            <a href="${pageContext.request.contextPath}/articles/delete/${article.id}" onclick="confirm('Czy na pewno chcesz usunąć artykuł?')">Usuń</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>

    </tr>
</table>
</body>
</html>
