<%--
  Created by IntelliJ IDEA.
  User: ebenezergraham
  Date: 4/24/19
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  String state = "logout";
  String visibility = "";
  if (request.getSession(false).getAttribute("username") == null) {
    state = "login";
    visibility = "display:none;";

  }
%>

<nav style="background-color: #216558;padding: 1rem 6rem;" class="shadow-sm navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a href="/dashboard" class="font-weight-bolder navbar-brand text-white">Milestone</a>
    </div>
    <ul class="nav navbar-right">
      <li style="<%=visibility%>"><a class="px-2 text-white" href="/dashboard">Dashboard</a></li>
      <li><a class="text-white px-2 btn-login" href="/<%=state%>"><span
          style="text-transform: capitalize"><%=state%></span></a></li>
    </ul>
  </div>
</nav>
