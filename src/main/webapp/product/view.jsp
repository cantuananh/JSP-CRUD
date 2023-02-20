<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 20/02/2023
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View product</title>
</head>
<body>
<h1>View product</h1>
<p>
    <c:if test="${requestScope['message'] != null}">
        <span class="message"><${requestScope['message']}</span>
    </c:if>
</p>
<p>
    <a href="/products">Back</a>
</p>
<p>
    <c:if test="${requestScope['message'] != null}">
        <span class="message">${requestScope['message']}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        Name: <input type="text" name="name" id="name" value="${requestScope['product'].getName()}">
    </fieldset>
</form>
</body>
</html>
