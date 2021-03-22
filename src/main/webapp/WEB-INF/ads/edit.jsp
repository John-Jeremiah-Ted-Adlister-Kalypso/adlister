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

    <style>
        .file {
            visibility: hidden;
            position: absolute;
        }

    </style>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">

<c:choose>
<c:when test="${isOwner}">
        <h1>Editing Ad #${ad.id} - ${ad.title}</h1>


<%--    <form method="post" id="/upload">--%>
<%--&lt;%&ndash;    <form action="https://request-inspector.glitch.me/" method="post">&ndash;%&gt;--%>
<%--        <div class="input-group mb-3">--%>
<%--            <div class="custom-file">--%>
<%--                <input type="file" class="custom-file-input" id="inputGroupFile02" name="file" accept="image/*">--%>
<%--                <label class="custom-file-label" for="inputGroupFile02">Choose a picture (not required)</label>--%>
<%--            </div>--%>
<%--            <div class="input-group-append">--%>
<%--                <button type="submit" class="btn btn-primary input-group-text" id="">Upload</button>--%>
<%--            </div>--%>
<%--        </div>--%>


<%--    </form>--%>
<%--<div id="alert">--%>
<%--    <c:if test="${not empty message}">--%>
<%--        <div class='alert alert-success alert-dismissible fade show' role='alert'>--%>
<%--            ${message}--%>
<%--            <button type='button' class='close' data-dismiss='alert' aria-label='Close'>--%>
<%--                <span aria-hidden='true'>&times;</span>--%>
<%--            </button>--%>
<%--        </div>--%>

<%--    </c:if>--%>
<%--</div>--%>


<form action="/edit" method="post">
<%--    <div class="d-flex flex-wrap">--%>
<%--        <%! int counter1 = 1; %>--%>
<%--        <c:forEach var="image" items="${images}">--%>
<%--            <div class="form-check">--%>
<%--                <label class="form-check-label"><img src="{image.filepath}" class="img-thumbnail" height="200px"></label>--%>
<%--                <input class="form-check-input" type="checkbox" id="image${image.id}">--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>

            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" required value="${ad.title}">
            </div>
            <input type="hidden" class="d-none" name="id" value="${ad.id}">
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text" required>${ad.description}</textarea>
            </div>
            <div class="form-group">
                <label for="category">Select All Categories that Apply</label>
                <select multiple class="form-control" id="category" name="category" required>
                    <%! int counter = 1; %>
                    <c:forEach var="category" items="${categories}">
                        <option value="<%= counter %>"<c:forEach var="selectedcategory" items="${ad.categories}"><c:if test="${category} eq ${selectedcategory}"> selected</c:if></c:forEach> name="category">${category}</option>
                        <% counter += 1; %>
                    </c:forEach>
                </select>

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
