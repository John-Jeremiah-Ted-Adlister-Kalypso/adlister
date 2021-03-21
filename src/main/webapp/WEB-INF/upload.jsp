<%--
  Created by IntelliJ IDEA.
  User: edwardmccormick
  Date: 3/19/21
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Upload</title>
</head>
<body>

    <h1>File Upload</h1>
    <form method="post" action="/upload"
          enctype="multipart/form-data">
        Select file to upload: <input type="file" name="file" size="60" /><br />
        <br /> <input type="submit" value="Upload" />
    </form>

</body>
</html>