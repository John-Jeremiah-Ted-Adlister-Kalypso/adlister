<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad"/>
    </jsp:include>
</head>
<body>
<jsp:include page="../partials/navbar.jsp"></jsp:include>
<div class="container">
    <h1>Create a new Ad</h1>

    <br><br>
    <form action="/upload" method="post">
<%--        <form action="https://request-inspector.glitch.me/" method="post">--%>

    </form>


    <form action="/ads/create" method="post">
<%--    <form action="https://request-inspector.glitch.me/" method="post">--%>
    <p>Once you have picked a title and description of your ad, you can add pictures.</p><br>
<%--    <div class="d-flex flex-wrap"--%>
<%--    <%! int counter1 = 1; %>--%>
<%--    <c:forEach var="image" items="${images}">--%>
<%--        <div class="form-check">--%>
<%--            <label class="form-check-label"><img src="{image.filepath}" class="img-thumbnail" height="200px"></label>--%>
<%--            <input class="form-check-input" type="checkbox" id="image${image.id}">--%>

<%--    </c:forEach>--%>


    <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" class="form-control" type="text" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" type="text" required></textarea>
        </div>
        <div class="form-group">
            <label for="category">Select All Categories that Apply</label>
            <select multiple class="form-control" id="category" name="category" required>
                <%! int counter2 = 1; %>
                <c:forEach var="category" items="${categories}">
                    <option value="<%= counter2 %>" name="category">${category}</option>
                    <% counter2 += 1; %>
                </c:forEach>
            </select>

        </div>

        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>

<jsp:include page="/WEB-INF/partials/script.jsp"/>
    </body>
    </html>
