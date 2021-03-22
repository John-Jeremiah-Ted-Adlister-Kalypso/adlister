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

<div class="container">
    <div>
        <img src="${sessionScope.user.avatar_filepath}" alt="profile pic" class="profilePic" height="150px" width="150px" style="border-radius: 50%; margin: 20px; border: 2px solid black; border-radius: 50%">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#profilePictureModal">
            Edit Pic
        </button>
    </div>
    <div>
        <h1 style="text-shadow: 2px 2px #ccc">Welcome, ${sessionScope.user.username}!</h1>
    </div>

    <p>Your username is: ${user.username}</p>
    <p>Your current email is: ${user.email}</p>

<c:choose>
    <c:when test="${ads.isEmpty()}">
        <h2>You haven't created any ads (yet).</h2>
        <h1>Maybe you should <a href="ads/create">try it out?</a></h1>
    </c:when>
    <c:otherwise>
        <div class="row">
        <c:forEach var="ad" items="${ads}">
            <div class="card" style="width: 20rem; padding: 10px; margin: 20px; box-shadow: 1px 1px 6px 7px #ccc">
                <div class="card-body">
                    <h5 class="card-title"><a href="/details?id=${ad.id}"><c:out value="${ad.title}"/></a></h5>
                    <h6 class="card-subtitle mb-2 text-muted">Category: <c:forEach var="category" varStatus="loop" items="${ad.categories}"> ${category}<c:if test="${!loop.last}">,</c:if> </c:forEach> </h6>
                    <p class="card-text"><c:out value="${ad.description}"/><br><hr><br>
                    <small>Created <c:out value="${ad.created_time}" /> <br>Last modified <c:out value="${ad.updated_time}" /></small>
                </div>
            </div>
        </c:forEach>
                <br>
        </div>
            <br><hr><br>
        <h3>Do you need to <a href="ads/create">post some more?</a></h3>
    </c:otherwise>
</c:choose>
</div>
<!-- Modal -->
<div class="modal fade" id="profilePictureModal" tabindex="-1" role="dialog" aria-labelledby="profilePictureModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="profilePicModalTitle">Change Your Profile Picture</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <img id="profilePicPreview" height="150px" width="150px" src="${sessionScope.user.avatar_filepath}">
                <img id="profilePicPreviewSmall" height="50px" width="50px" src="${sessionScope.user.avatar_filepath}">

                <form action="/profile" method="post" id="profilePicForm">
                    <div class="form-group">
                    <label for="avatar_filepath">Put a web address here to use it as a Profile Picture</label>
                        <div class="input-group mb-3">
                            <input id="avatar_filepath" class="form-control" name="avatar_filepath" placeholder="Web address here">
                            <div class="input-group-append">
<%--                                <span class="input-group-text">--%>
                                    <button class="btn btn-primary" id="preview">Preview</button>
<%--                                </span>--%>
                            </div>
                        </div>
                    </div>
                </form>
<%--                    <button id="preview" class="btn btn-primary btn-block col-md-6">Preview</button><button class="btn btn-primary btn-block col-md-6">Submit</button>--%>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" form="profilePicForm" class="btn btn-primary">Submit</button></div>
                </form>
            </div>
        </div>
    </div>

<script>
    const preview = document.getElementById("preview");
    const profilePic = document.getElementById("profilePicPreview");
    const profilePicSmall = document.getElementById("profilePicPreviewSmall");
    const avatar_filepath = document.getElementById("avatar_filepath");

    preview.addEventListener('click', function(e) {
        e.preventDefault();
        profilePic.outerHTML = "<img id=\"profilePicPreview\" src=\"" +
           avatar_filepath.value +"\" width=\"150px\" height=\"150px\">"
        profilePicSmall.outerHTML = "<img id=\"profilePicPreviewSmall\" src=\"" +
           avatar_filepath.value + "\" width=\"50px\" height=\"50px\">"

    })

</script>
<jsp:include page="/WEB-INF/partials/script.jsp" />
</body>
</html>
