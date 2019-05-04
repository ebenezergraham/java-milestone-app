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
<head>
  <title>Dashboard</title>
  <link rel="stylesheet" type="text/css" href="resources/css/main.css">
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
<%@include file="WEB-INF/views/menu.jsp" %>

<style>
</style>
<div style="vh: 100%" class="container m-5 px-5">
  <div class="row">
    <aside class="col-3">
      <img class="avatar" src="https://ui-avatars.com/api/?name=<%=userName%>&size=512">
      <div class="m-3">
        <h1>Welcome <br><span style="text-transform: capitalize"><%=userName%><p></p></h1>
        <a class="btn btn-login text-white btn-add-project">Add Project</a>
      </div>

    </aside>
    <main class="col-9">
      <%@include file="WEB-INF/views/copy-link.jsp" %>
      <div class="row">
        <section class="col-6">
          <a id="add-new-project" class="card m-3">
            <form action="/projects" method="post">
              <input id="nameInput" autofocus name="project" type="text" class="form-control"
                     placeholder="enter project name ">
            </form>
            <p id="project"></p>
          </a>
          <c:forEach begin="0" end="${projectList.size()}" items="${projectList}" varStatus="status" var="project">
            <c:if test="${status.index % 2 == 0}">
              <div id="${project.getId()}" class="project-box m-3">
                <a href="/project?id=${project.getId()}" class="project card">
                  <c:out value="${project.getTitle()}"/>
                </a>
                <div class="option-box">
                  <i
                     class="options rounded-circle m-2 text-white fa fa-trash btn-delete-project"
                     title="delete"></i>
                  <i class="options rounded-circle m-2 text-white fa fa-share share-project"
                     title="share"></i>
                </div>
              </div>
            </c:if>
          </c:forEach>
        </section>
        <section class="col-6">
          <c:forEach begin="0" items="${projectList}" varStatus="status" var="project">
            <c:if test="${status.index % 2 == 1}">
              <div id="${project.getId()}" class="project-box m-3">
                <a href="/project?id=${project.getId()}" class="project card">
                  <c:out value="${project.getTitle()}"/>
                </a>
                <div class="option-box">
                  <i
                     class="options rounded-circle m-2 text-white fa fa-trash btn-delete-project"
                     title="delete"></i>
                  <i class="options rounded-circle m-2 text-white fa fa-share share-project"
                     title="share"></i>
                </div>
              </div>
            </c:if>
          </c:forEach>
        </section>
      </div>

    </main>
  </div>
</div>
<script defer src="resources/js/jquery-3.3.1.min.js"></script>
<script defer src="resources/js/script.js"></script>

</body>
</html>
