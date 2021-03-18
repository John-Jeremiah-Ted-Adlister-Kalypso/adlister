<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container-fluid">
<jsp:include page="partials/profileEdit.jsp"/>
    <div>
        <a href="#" ><img class="profilePic" src="http://placehold.jp/150x150.png"> <span>edit pic</span></a>
    </div>
    <div>
        <h1>Welcome, ${sessionScope.user.username}!</h1>
    </div>
</div>
<c:choose>
    <c:when test="${ads.isEmpty()}">
        <h2>You haven't created any ads (yet).</h2>
        <h1>Maybe you should <a href="ads/create">try it out?</a></h1>
    </c:when>

    <c:otherwise>
        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6">
                <h2><a href="details?id=${ad.id}">${ad.title}</a></h2>
                <p>${ad.description}</p>
            </div>
        </c:forEach>
        <h3>Do you need to <a href="ads/create">post some more?</a></h3>
    </c:otherwise>
</c:choose>
</body>
</html>
