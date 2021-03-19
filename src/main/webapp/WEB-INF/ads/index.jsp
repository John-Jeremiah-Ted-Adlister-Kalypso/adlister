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
    <div class="col-md-6">
        <h2><a href="/details?id=${ad.id}"><c:out value="${ad.title}"/></a></h2>
        <p><c:out value="${ad.description}"/></p>
       <small><p>Created <c:out value="${ad.created_time}" /> <br>
        Last modified <c:out value="${ad.updated_time}" /> </p></small>
    </div>
</c:forEach>

</div>
</div>
</body>
</html>
