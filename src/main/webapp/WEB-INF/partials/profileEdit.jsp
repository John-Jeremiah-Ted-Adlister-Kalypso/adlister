<%--
  Created by IntelliJ IDEA.
  User: jeremiahbaham
  Date: 3/18/21
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-link" href="/profile">Profile</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="/ads">Edit Ads <span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ads/create">Create</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Refresh</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Local</a>
            </li>
        </ul>
        <form action="/update" class="form-inline my-2 my-lg-0">
            <button id="update" class="btn btn-outline-success my-2 my-sm-0" type="submit">Update</button>
        </form>
    </div>
</nav>
