<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")) userName = cookie.getValue();
        }
    }
%>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

<%@include file="WEB-INF/views/menu.jsp"%>
<div class="container m-5">
    <div class="row">
        <aside class="col-3">
            <img class="avatar" src="https://ui-avatars.com/api/?name=<%=userName%>&size=512">
            <div class="m-3">
                <h1 >Welcome <br><span style="text-transform: capitalize"><%=userName%><p></p></h1>
                <a class="btn btn-login text-white">Add Project</a>
            </div>

        </aside>
        <main class="col-9">
            <div class="row">
                <section class="col-6">
                    <div id="add-new-project m-3" class="card">
                        <input id="nameInput" type="text" class="form-control"
                               placeholder="enter project name ">
                        <p id="project">Title</p>
                    </div>
                <c:forEach begin="0" end="${(projectList.size()/2)-1}" items="${projectList}" var="project">
                        <a href="projects/?title=${project.getTitle()}" class="project card m-3">
                            <p><c:out value="${project.getTitle()}"/></p>
                        </a>
                </c:forEach>
                </section>
                <section class="col-6">
                    <c:forEach begin="${projectList.size()/2}" items="${projectList}" var="project">
                        <a href="projects/?title=${project.getTitle()}" class="project card m-3">
                            <p><c:out value="${project.getTitle()}"/></p>
                        </a>
                    </c:forEach>
                </section>
            </div>
        </main>
    </div>
</div>
<script defer src="resources/js/jquery-3.3.1.js"></script>
<script defer src="resources/js/script.js"></script>

</body>
</html>
