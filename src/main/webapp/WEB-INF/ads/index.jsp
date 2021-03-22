<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach var="ad" items="${ads}">
            <div class="card" style="width: 20rem; padding: 10px; margin: 20px; box-shadow: 3px 3px 6px 7px #ccc">
                <div class="card-body">
                    <h5 class="card-title"><a href="/details?id=${ad.id}"><c:out value="${ad.title}"/></a></h5>
                    <h6 class="card-subtitle mb-2 text-muted"> <h6 class="card-subtitle mb-2 text-muted">Category: <c:forEach var="category" varStatus="loop" items="${ad.categories}"> ${category}<c:if test="${!loop.last}">,</c:if> </c:forEach> </h6></h6>
                    <p class="card-text"><c:out value="${ad.description}"/><br>
                    <hr>
                    <br>
                    <small>Created <c:out value="${ad.created_time}"/> <br>Last modified <c:out
                            value="${ad.updated_time}"/></small>
                </div>
            </div>

        </c:forEach>

    </div>
</div>
<jsp:include page="/WEB-INF/partials/script.jsp" />
</body>
</html>
