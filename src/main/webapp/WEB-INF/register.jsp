<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
</head>
<body>

    <jsp:include page="partials/navbar.jsp" />
    <div class="container">
        <h1>Please fill in your information.</h1>
        <form action="/register" method="post" class="needs-validation">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text" required value="${username}">
                <div class="valid-feedback">Looks good!</div>
                <div class="invalid-feedback">You must choose a unique username.</div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" type="text" required value="${email}">
                <div class="valid-feedback">Looks good!</div>
                <div class="invalid-feedback">Your email address is required to register.</div>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
            <div class="input-group mb-3"> <input id="password" name="password" class="form-control" type="password" required value="${password}"> <div class="input-group-append">
                <span class="input-group-text"><i class="fa fa-eye-slash" id="togglePassword"></i></span></div></div>
            </div>

            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
               <div class="input-group mb-3"><input id="confirm_password" name="confirm_password" class="form-control" type="password" required value="${confirm_password}"><div class="input-group-append">
                   <span class="input-group-text"><i class="fa fa-eye-slash" id="toggleConfirm"></i></span></div></div>

            </div>
            <div id="alert"><c:if test="${not empty alert}">
                <div class='alert alert-danger alert-dismissible fade show' role='alert'>
                <strong>Warning!</strong>The username "${alert}" is already in use! Select another username and resubmit to register.
                <button type='button' class='close' data-dismiss='alert' aria-label='Close'>
                <span aria-hidden='true'>&times;</span>
                </button></div>

            </c:if></div>
            <input id="submit" type="submit" class="btn btn-primary btn-block">
        </form>
    </div>

    <script>
        const password = document.getElementById("password")
        const confirm = document.getElementById("confirm_password")
        const alert = document.getElementById("alert")
        const submit = document.getElementById("submit")
        confirm.addEventListener('change', comparePasswords)

        function comparePasswords() {
            if (password.value != confirm.value) {
                alert.innerHTML = "<div class='alert alert-danger alert-dismissible fade show' role='alert'>" +
                    "<strong>Warning!</strong> Your password and the 'confirm password' field do not match." +
                    "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                    "<span aria-hidden='true'>&times;</span>" +
                    "</button></div>"
                submit.disabled = true;
            }
            else if (password.value == confirm.value) {
                alert.innerHTML = "<div class='alert alert-success alert-dismissible fade show' role='alert'>" +
                    "<strong>Success!</strong> Your password and the 'confirm password' field match." +
                    "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                    "<span aria-hidden='true'>&times;</span>" +
                    "</button></div>"
                submit.disabled = false;
            }
        }


        const togglePassword = document.getElementById("togglePassword");
        const toggleConfirm = document.getElementById("toggleConfirm");

        togglePassword.addEventListener('click', function (e) {
            // toggle the type attribute
            const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            // toggle the eye slash icon
            this.classList.toggle('fa-eye-slash');
            this.classList.toggle('fa-eye')
        })
        toggleConfirm.addEventListener('click', function (e) {
            // toggle the type attribute
            const type = confirm.getAttribute('type') === 'password' ? 'text' : 'password';
            confirm.setAttribute('type', type);
            // toggle the eye slash icon
            this.classList.toggle('fa-eye-slash');
            this.classList.toggle('fa-eye')
        })

        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    </script>
</body>
</html>
