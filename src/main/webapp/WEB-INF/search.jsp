<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jeremiahbaham
  Date: 3/17/21
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Ads Search Results</h1>
<h2>Showing Results for ${searchTerm}</h2> <%-- <-- note this line --%>
<c:forEach var="product" items="${searchResults}"><jsp:useBean id="ad" scope="request" type="sun.awt.shell.ShellFolderColumnInfo"/>

    <h3>
        <a href="detail?id=${ad.id}">${ad.title}</a>
    </h3>
</c:forEach>
</body>
</html>
