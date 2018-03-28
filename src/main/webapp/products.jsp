<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: Anna-PC
  Date: 06.03.2018
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<link rel="stylesheet" href="/styles/style.css">--%>
    <title>Products</title>
</head>
<table align="center">
    <tr>
        <td>
        <div class="categories" align="center">
            <table>
                <tr>
                    <c:forEach items="${sessionScope.categories}" var="category">
                            <td><a href="${pageContext.request.contextPath}/products?category=${category.getId()}">
                            <c:out value="${fn:escapeXml(category.getName())}" /></a></td>
                    </c:forEach>
                </tr>
            </table>
         </div>
        </td>
    </tr>
        <tr>
            <td>
            <div class="products" align="center">
                <form action="products" method="post">
                    <table>
                        <c:forEach items="${requestScope.products}" var="product">
                            <tr>
                                <td>Артикул: <c:out value="${fn:escapeXml(product.getCode())}" /></td>
                                <td>Название: <c:out value="${fn:escapeXml(product.getName())}" /></td>
                                <td>Цена: <c:out value="${product.getPrice()}" />грн</td>
                                <td>Вес: <c:out value="${fn:escapeXml(product.getWeight())}" /></td>
                                <td>Количество: <c:out value="${fn:escapeXml(product.getQuantity())}" /></td>
                                <td>Категория: <c:out value="${fn:escapeXml(product.getCategory().getName())}" /></td>
                                <td><img src="${fn:escapeXml(product.getImg())}" ></td>
                                <td><a href="${pageContext.request.contextPath}/product?edit=${product.getIdgoods()}">Изменить</a></td>
                                <td><button type="submit" name="delete" value="${product.getIdgoods()}">Удалить</button></td>
                             </tr>
                        </c:forEach>
                    </table>
                    <a href="${pageContext.request.contextPath}product">Добавить новый продукт</a>
                </form>
            </div>
            </td>
    </tr>
</table>
</body>
</html>
