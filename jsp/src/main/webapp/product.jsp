<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="productController" scope="request" class="uy.com.proitc.jsp.ProductController"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Manager</title>
</head>
<body>
<header><h1>Products</h1></header>
<section>
    <c:forEach var="product" items="${productController.findAll()}">
        ${product.name}<br>
    </c:forEach>
</section>
</body>
</html>
