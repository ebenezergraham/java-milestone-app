<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
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
  <h1>Login</h1>
  <p><font color="red"> ${errorMessage} </font></p>
  <form action="/login" method="post">
    Enter Your Name <input type="text" name="name"/>
    Enter Your Password <input type="password" name="password"/>
    <input type="submit" value="Login"/>
  </form>
</div>

</body>


</html>
