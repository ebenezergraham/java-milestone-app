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
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="../../resources/css/main.css">
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
          <div id="a">
            <div id="b"></div>
          </div>
          <a id="add-new-project" class="card m-3">
            <form action="/projects" method="post">
              <input id="nameInput" autofocus name="project" type="text" class="form-control"
                     placeholder="enter project name ">
            </form>
            <p id="project"></p>
          </a>
          <c:if test="${projectList.size()>0}">
            <c:forEach begin="0" end="${(projectList.size()/2)-1}" items="${projectList}" var="project">
              <div id="${project.getId()}" class=" m-3">
                <a href="/project?id=${project.getId()}" class="project card">
                  <p><c:out value="${project.getTitle()}"/></p>
                </a>
                <i id="${project.getTitle()}"
                   class="options rounded-circle text-white m-2 fa fa-trash btn-delete-project"
                   title="delete"></i>
                <i class="options rounded-circle m-2 text-white fa fa-share share-project"
                   title="share"></i>
              </div>
            </c:forEach>
          </c:if>
        </section>
        <section class="col-6">
          <c:if test="${projectList.size()>0}">
            <c:forEach begin="${projectList.size()/2}" items="${projectList}" var="project">
              <div id="${project.getId()}" class=" m-3">
                <a href="/project?id=${project.getId()}" class="project card">
                  <p><c:out value="${project.getTitle()}"/></p>
                </a>
                <i id="${project.getId()}"
                   class="options rounded-circle m-2 text-white fa fa-trash btn-delete-project"
                   title="delete"></i>
                <i class="options rounded-circle m-2 text-white fa fa-share share-project"
                   title="share"></i>
              </div>
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
