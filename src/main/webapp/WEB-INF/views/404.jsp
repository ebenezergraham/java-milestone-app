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
      <div>
        <h1>Hmm! Looks like</h1>
        <h1 class="special">something went wrong.</h1>
      </div>
      <div class="row">
        <span id="icon" class="ic-warning exclamation-error"></span>
        <span id="error-text" class="display-error-text">Don't worry, let's take you home</span>
      </div>
      <div class="row space-above-try-again">
        <button id="submit" class="primary-btn-active right">TRY AGAIN</button>
      </div>

      <div class="nav-btn-container space-before-nav-container">
        <span class="tel left">REPORT INCIDENT TO <span class="tel-color"> </span> <a target="_blank"
                                                                                                 href="">HERE</a></span>
      </div>
    </div>
  </div>
</div>

<script defer src="../../resources/js/jquery-3.3.1.min.js"></script>
<script defer src="../../resources/js/jquery.ripples-min.js"></script>
<script defer src="../../resources/js/script.js"></script>
</body>


</html>

