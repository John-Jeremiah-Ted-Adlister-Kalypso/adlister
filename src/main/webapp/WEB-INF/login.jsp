<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
</head>
<body>

    <jsp:include page="/WEB-INF/partials/navbar2.jsp" />
<div id="alert"><c:if test="${not empty alert}">
    <div class='alert alert-success alert-dismissible fade show' role='alert'>
    ${alert}
    <button type='button' class='close' data-dismiss='alert' aria-label='Close'>
    <span aria-hidden='true'>&times;</span>
    </button></div>
</c:if></div>
    <div class="container">
        <h1>Please Log In</h1>
        <form action="/login" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div class="input-group mb-3"> <input id="password" name="password" class="form-control" type="password" required value="${password}">
                    <div class="input-group-append">
                    <span class="input-group-text"><i class="fa fa-eye-slash" id="togglePassword"></i></span></div></div>
            </div>
            <input type="hidden" id="redirect" name="redirect">
            <input type="submit" class="btn btn-primary btn-block" value="Log In">
        </form>
    </div>

    <script>
        var redirect = document.referrer;
        document.getElementById("redirect").value = redirect;

        const togglePassword = document.getElementById("togglePassword");

        togglePassword.addEventListener('click', function (e) {
            // toggle the type attribute
            const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            // toggle the eye slash icon
            this.classList.toggle('fa-eye-slash');
            this.classList.toggle('fa-eye')
        })

    </script>
</body>
</html>
