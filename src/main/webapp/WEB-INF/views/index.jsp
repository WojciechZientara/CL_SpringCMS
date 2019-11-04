<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>
<head>
    <title>HomePage</title>
</head>
<body>

    <h1>Kategorie:</h1>
    <c:forEach items="${categories}" var="category">
        <a href="${pageContext.request.contextPath}/category/${category.id}">${category.name}</a>&nbsp;&nbsp;&nbsp;
    </c:forEach>

    <br>
    <br>
    <br>

    <h1>5 ostatnich artykułów:</h1><br>
    <br>
    <c:forEach items="${lastFive}" var="article">
        <h1>${article.title}</h1>

        <c:set var = "fullDate" value = "${article.created}"/>
        <c:set var = "shortDate" value = "${fn:substring(fullDate, 0, 10)}" />
        <h2>Data: ${shortDate}</h2>
        <br>

        <c:set var = "fullContent" value = "${article.content}"/>
        <c:set var = "shortContent" value = "${fn:substring(fullContent, 0, 200)}" />
        ${shortContent}...[Czytaj dalej]<br>
        <br>
    </c:forEach>

</body>
</html>
