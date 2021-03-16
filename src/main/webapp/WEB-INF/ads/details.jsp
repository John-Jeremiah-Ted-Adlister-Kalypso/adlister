<%--
  Created by IntelliJ IDEA.
  User: edwardmccormick
  Date: 3/16/21
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Detailed View of Ad #${id}" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here is more information about that ad</h1>


    <div class="col-md-6">
        <h2>${displayAd.id} - ${displayAd.title} </h2>
        <p>${displayAd.description}</p>
    </div>
    </div>
</body>
</html>
