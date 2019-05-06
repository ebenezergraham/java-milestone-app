<%--
  Created by IntelliJ IDEA.
  User: omeiza
  Date: 24/04/19
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
  <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
  <link rel="shortcut icon" type="image/png" href="../../resources/favicon.ico"/>
  <link rel="stylesheet" href="../../resources/css/main.css">
  <title>Milestone Planner</title>
</head>
<body id="loginbg">
<div class="container">
  <div class="row">
    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
      <div class="card signin-card my-5">
        <div class="card-body">
          <h5 class="card-title text-center">Register</h5>
            <p class="${displayError} text-center alert-danger p-2">${errorMessage}</p>
          <form action="/register" method="post" class="form-signin">
            <div class="form-label-group">
              <input type="text" id="inputEmail" class="form-control" name="name" placeholder="User Name"
                     required autofocus>
              <label for="inputEmail">Username</label>
            </div>

            <div class="form-label-group">
              <input type="password" id="inputPassword" class="form-control" name="password"
                     placeholder="Password" required>
              <label for="inputPassword">Password</label>
            </div>
            <button class="btn btn-lg btn-login btn-block text-uppercase" type="submit" value="register">
              Register
            </button>
            <a class="btn btn-lg btn-login btn-block text-uppercase" href="/login"
            >Back to login</a>
            <hr class="my-4">
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<script defer src="../../resources/js/jquery-3.3.1.min.js"></script>
<script defer src="../../resources/js/jquery.ripples-min.js"></script>
<script defer src="../../resources/js/script.js"></script>
</body>


</html>

