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
    <title>List product</title>
</head>
<body>
<h1>Product List</h1>
<p><a href="products?action=create">Create</a></p>
<form action="/products?action=search" method="post">
    <input type="text" name="name" id="name" placeholder="Enter name product...">
    <button type="submit" value="Submit">Search</button>
</form>
<table>
    <tr>
        <td>Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${requestScope['productList']}" var="product">
        <tr>
            <td><a title="view product" href="/products?action=view&Product&id=${product.getId()}">${product.getName()}</a></td>
            <td>
                <button>
                    <a href="/products?action=edit&Product&id=${product.getId()}" title="edit product">Edit</a>
                </button>
            </td>
            <td>
                <button>
                    <a href="/products?action=delete&Product&id=${product.getId()}" title="delete product">Delete</a>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
