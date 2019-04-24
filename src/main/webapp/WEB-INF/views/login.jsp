<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/main.css">
    <title>Milestone Planner</title>
</head>
<body id="loginbg">

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card signin-card my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Sign In</h5>
                    <form action="/login" method="post" class="form-signin">
                        <div class="form-label-group">
                            <input type="text" id="inputEmail" class="form-control" name="name" placeholder="User Name" required autofocus>
                            <label for="inputEmail">Name</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>
                            <label for="inputPassword">Password</label>
                        </div>

                        <div class="custom-control custom-checkbox mb-3">
                            <input type="checkbox" class="custom-control-input" id="customCheck1">
                            <label class="custom-control-label" for="customCheck1">Remember password</label>
                        </div>
                        <button class="btn btn-lg btn-login btn-block text-uppercase" type="submit" value="login">Sign in</button>
                        <button class="btn btn-lg btn-login btn-block text-uppercase" type="submit" value="register">Register</button>
                        <hr class="my-4">
                        <button class="btn btn-lg btn-google btn-block text-uppercase" type="submit"><i class="fab fa-google mr-2"></i> Sign in with Google</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script defer src="/resources/js/jquery-3.3.1.js"></script>
<script defer src="/resources/js/jquery.ripples.js"></script>
<script defer src="/resources/js/login.js"></script>
</body>


</html>
