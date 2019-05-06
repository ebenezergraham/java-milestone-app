<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
  <link rel="shortcut icon" type="image/png" href="../../resources/favicon.ico"/>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
  <link rel="stylesheet" href="../../resources/css/normalize.css">
  <link rel="stylesheet" href="../../resources/css/project.css">
  <title>Milestone Planner</title>
</head>

<body>
<%@include file="menu.jsp" %>
<div class="container m-5">
  <div class="row">
    <aside class="col-3">
      <div class="d-flex justify-content-center">
      </div>
      <div class="mx-4 control-pane">
        <h1><br><span style="text-transform: capitalize">${title}<br> Milestones<p></p></h1>
        <div class="${visibility}">
          <a id="add-new-milestone">
            <button class="btn btn-block text-white bg-green" data-toggle="modal" data-target="#add">
              Add Milestone
            </button>
            <br><br>
          </a>
          <a href="/project?id=${id}&completed=1">
            <button type="button" class="btn btn-block text-white bg-green" id="status">Completed</button>
          </a>
          <br><br>
          <a href="/project?id=${id}&completed=0">
            <button type="button" class="btn btn-block text-white bg-green">Ongoing</button>
          </a>
          <br><br>
          <a href="/project?id=${id}">
            <button type="button" class="btn btn-block text-white bg-green">All</button>
            <br><br>
          </a>
        </div>

      </div>
      <%@include file="add_milestone_form.jsp" %>
    </aside>

    <main class="col-9">
      <div class="container py-2">
        <c:set var="count" value="0" scope="request"/>
        <c:forEach items="${allMilestones}" var="milestone">
          <%@ include file="milestone.jsp" %>
          <c:set var="count" value="${count+1}" scope="page"/>
        </c:forEach>
      </div>

    </main>
  </div>
</div>

<script src="../../resources/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../resources/js/project.js"></script>
<script src="../../resources/js/bootbox.min.js"></script>
</main>
</body>
</html>
