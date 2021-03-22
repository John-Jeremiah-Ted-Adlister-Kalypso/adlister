<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="/profile">Adlister</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="/ads">Ads</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
            <li id="theme" class="nav-item" onclick="myFunction()">
                <a class="nav-link" href="#">Theme</a>
            </li>
        </ul>
        <form action="/search" class="form-inline my-2 my-lg-0">
            <input name="term" type="text" class="form-control mr-sm-2" type="search" placeholder="Search">
            <button id="search" class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
        </form>

    </div>
</nav>