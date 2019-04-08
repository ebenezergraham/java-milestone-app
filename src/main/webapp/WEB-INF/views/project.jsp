<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <title>Milestone Planner</title>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a href="/" class="navbar-brand">Milestone</a>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="nav navbar-nav">
      <li class="nav-item m-2"><a href="/dashboard">Dashboard</a></li>
    </ul>

    <ul class="nav navbar-nav navbar-right">
      <li class="nav-item"><a href="/login">Login</a></li>
    </ul>
  </div>
</nav>

<div class="container">
  <h1>Project ${title}</h1>
  <div class="row">
  <c:forEach items="${allMilestones}" var="milestone">

    <div class=".col-6 .col-md-3">
    <div class="card" style="width: 18rem;">
      <img src="" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">${milestone.title}</h5>
        <p class="card-text">${milestone.description}</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
      </div>
    </div>
    </div>

  </c:forEach>
  </div>


<%--  <p><font color="red"> ${errorMessage} </font></p>--%>
<%--  <form action="/login" method="post">--%>
<%--    Enter Your Name <input type="text" name="name"/>--%>
<%--    Enter Your Password <input type="password" name="password"/>--%>
<%--    <input type="submit" value="Login"/>--%>
<%--  </form>--%>
</div>

<footer class="footer">
  <p>footer content</p>
</footer>
<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>


</html>
