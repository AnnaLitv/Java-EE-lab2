<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: Anna-PC
  Date: 06.03.2018
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product editting</title>
</head>
<body>
<div align="center" id="products">
<form action="product" method="post">
    <label for="name">Name</label>
    <input id="name" name="name" value="${fn:escapeXml(product.getName())}" />
    <br/>
    <label for="code">Code</label>
    <input id="code" name="code" value="${fn:escapeXml(product.getCode())}" />
    <br/>
    <label for="price">Price</label>
    <input id="price" name="price" value="${fn:escapeXml(product.getPrice())}" />
    <br/>
    <label for="weight">Weight</label>
    <input id="weight" name="weight" value="${fn:escapeXml(product.getWeight())}" />
    <br/>
    <label for="quantity">Quantity</label>
    <input id="quantity" name="quantity" value="${fn:escapeXml(product.getQuantity())}" />
    <br/>
    <label for="idCategory">id Category</label>
    <input id="idCategory" name="idCategory" value="${fn:escapeXml(product.getCategory().getName())}" />
    <br/>
    <label for="img">img src</label>
    <input id="img" name="img" value="${fn:escapeXml(product.getImg())}" />
    <br/>
    <button type="submit" name="save" value="${product.getIdgoods()}">save</button>
</form>
</div>
</body>
</html>
