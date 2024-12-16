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
    <title>Customer List</title>
    <style>
        table {
            border: 1px solid #000;
            width: 35%;
        }

        th, td {
            border: 1px dotted #555;
        }

        a {
            text-decoration: none;
            color: black;
        }
      </style>
</head>
<body>
<p>There are ${customers.size()} customer(s) in list.</p>
<table>
    <caption>Customers List</caption>
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="c" items="${customers}">
            <tr>
                <td>
                    <c:out value="${c.id}"/>
                </td>
                <td>
                    <a href="/customers/${c.id}">${c.name}</a>
                </td>
                <td>
                    <c:out value="${c.email}"/>
                </td>
                <td>
                    <c:out value="${c.address}"/>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
