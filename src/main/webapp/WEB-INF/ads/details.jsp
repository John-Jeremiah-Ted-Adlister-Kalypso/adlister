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
        <p>Posted by: ${displayUser.username}. Email at: ${displayUser.email}</p>

        <c:if test="${isOwner}">
            <button type="button" class="btn btn-primary">Edit</button>
            <button type="submit" formmethod="post" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                Delete
            </button>

        </c:if>
    </div>
    </div>

<div class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Warning!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>There is no way to recover your ad if you delete it. Are you sure you want to proceed?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Delete</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../partials/script.jsp"/>
</body>
</html>
