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
  if (request.getRequestedSessionId() == null
      && !request.isRequestedSessionIdValid()) {
    state = "login";
  }
%>
<nav style="background-color: #216558;padding: 1rem 6rem ;" class="navbar navbar-expand-lg shadow-sm">
  <a href="/dashboard" class="navbar-brand text-white">Milestone</a>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="nav navbar-nav">
      <li class="nav-item m-2"><a class="text-white" href="/dashboard">Dashboard</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li class="nav-item"><a class="text-white" href="/<%=state%>"><span
          style="text-transform: capitalize"><%=state%></span></a></li>
    </ul>
  </div>
</nav>
