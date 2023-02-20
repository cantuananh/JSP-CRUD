<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 20/02/2023
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search product</title>
</head>
<body>
<table>
    <tr>
        <td>Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <tr>
        <td>
            <a href="/products?action=view&Product&id=${requestScope["product"].getId()}">${requestScope["product"].getName()}</a>
        </td>
        <td><a href="/products?action=edit&id=${requestScope["product"].getId()}">Edit</a></td>
        <td><a href="/products?action=delete&id=${requestScope["product"].getId()}">Delete</a></td>
    </tr>
</table>
</body>
</html>
