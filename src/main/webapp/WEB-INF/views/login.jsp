<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 3/7/2019
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <title>To-dos</title>
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-default">

        <a href="/" class="navbar-brand">Brand</a>

        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="/list-todo.do">Todos</a></li>
            <li><a href="http://www.in28minutes.com">In28Minutes</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="/login.do">Login</a></li>
        </ul>

    </nav>

    <div class="container">
        <h1>Login</h1>
        <p><font color="red"> ${errorMessage} </font> </p>
        <form action="/login.do" method="post">
            Enter Your Name <input type="text" name="name"/>
            Enter Your Password <input type="password" name="password"/>
            <input type="submit" value="Login"/>
        </form>
    </div>

    <footer class="footer">
        <p>footer content</p>
    </footer>
<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>


</html>
