<%--
  Created by IntelliJ IDEA.
  User: edwardmccormick
  Date: 3/17/21
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Editing Ad #${ad.id} - ${ad.title}" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">

<c:choose>
<c:when test="${isOwner}">
        <h1>Editing Ad #${ad.id} - ${ad.title}</h1>
        <form action="/edit" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" value="${ad.title}">
            </div>
            <input type="hidden" class="d-none" name="id" value="${ad.id}">
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text">${ad.description}</textarea>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>


</c:when>
<c:otherwise>
    <h1>I'm sorry, something went wrong</h1>
    <p>It looks like you were trying to edit an ad when you weren't the owner, or somehow otherwise got misdirected to this page. Sorry about that.</p>
    <p>You can go to your profile to see the ads that you've created or see all ads, or create a new ad.</p>
</c:otherwise>
</c:choose>
</div>
<jsp:include page="/WEB-INF/partials/script.jsp" />
</body>
</html>
