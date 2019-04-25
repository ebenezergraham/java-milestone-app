<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) userName = cookie.getValue();
        }
    }
%>
<html>
<body>
<style>
    div#b {
        display: none;
        width: 20px;
        height: 20px;
        border-radius: 10px;
        background-color: #216558;
    }

    div#a:hover div#b {
        display: block;
        width: 20px;
        height: 20px;
        background-color: #216558;
    }
</style>
<%--<%@ page import="DAO.DAO" %>--%>
<%@include file="WEB-INF/views/menu.jsp" %>
<div class="container m-5">
    <div class="row">
        <aside class="col-3">
            <img class="avatar" src="https://ui-avatars.com/api/?name=<%=userName%>&size=512">
            <div class="m-3">
                <h1>Welcome <br><span style="text-transform: capitalize"><%=userName%><p></p></h1>
                <a class="btn btn-login text-white btn-add-project">Add Project</a>
            </div>

        </aside>
        <main class="col-9">
            <div class="row">
                <section class="col-6">
                    <div id="a">
                        <div id="b"></div>
                    </div>
                    <a id="add-new-project" class="card m-3">
                        <form action="/project"  method="post">
                            <input id="nameInput" name="project" type="text" class="form-control"
                                   placeholder="enter project name ">
                        </form>
                        <p id="project"></p>
                    </a>
                    <c:if test="${projectList.size()>0}">
                        <c:forEach begin="0" end="${(projectList.size()/2)-1}" items="${projectList}" var="project">
                            <a href="projects/?title=${project.getTitle()}" class="project card m-3">
                                <p><c:out value="${project.getTitle()}"/></p>

                            </a>

                        </c:forEach>
                    </c:if>
                </section>
                <section class="col-6">
                    <c:if test="${projectList.size()>0}">

                        <c:forEach begin="${projectList.size()/2}" items="${projectList}" var="project">
                            <a href="projects/?title=${project.getTitle()}" class="project card m-3">
                                <p><c:out value="${project.getTitle()}"/></p>
                            </a>
                        </c:forEach>
                    </c:if>
                </section>
            </div>
        </main>
    </div>
</div>
<script defer src="resources/js/jquery-3.3.1.js"></script>
<script defer src="resources/js/script.js"></script>

</body>
</html>
