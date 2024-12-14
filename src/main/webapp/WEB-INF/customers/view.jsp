<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 14/12/2024
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Details</title>
</head>
<body>
<form method="post">
    <fieldset style="width: 300px">
        <legend>Customer Information</legend>
        <table>
            <tr>
                <td>Id</td>
                <td>
                    <input type="hidden" id="id" name="id" value="${customer.id}">
                    ${customer.id}
                </td>
            </tr>
            <tr>
                <td><label for="name">Name</label></td>
                <td>
                    <input type="text" id="name" name="name" value="${customer.name}">
                </td>
            </tr>
            <tr>
                <td><label for="email">Email</label></td>
                <td>
                    <input type="text" id="email" name="email" value="${customer.email}">
                </td>
            </tr>
            <tr>
                <td><label for="address">Address</label></td>
                <td>
                    <input type="text" id="address" name="address" value="${customer.address}">
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Update</button>
                </td>
                <td>
                    <c:if test="${customer != null}">
                        <span style="color: green">${message}</span>
                    </c:if>
                </td>
            </tr>
        </table>
        <a href="/customers">Back to list</a>
    </fieldset>
</form>
</body>
</html>
