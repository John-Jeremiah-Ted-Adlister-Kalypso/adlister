<%--
  Created by IntelliJ IDEA.
  User: edwardmccormick
  Date: 3/17/21
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<jsp:include page="/WEB-INF/partials/head.jsp" />
    <title>Your ad has been deleted.</title>
    <meta http-equiv="Refresh" content="3;url=/profile" />
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Your ad has been deleted. Click <a href="/profile">here</a> if you aren't redirected within five seconds....</h1>

</div>
<jsp:include page="/WEB-INF/partials/script.jsp" />
</body>
</html>
