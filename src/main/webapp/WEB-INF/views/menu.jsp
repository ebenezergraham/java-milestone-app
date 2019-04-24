<%--
  Created by IntelliJ IDEA.
  User: ebenezergraham
  Date: 4/24/19
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%
    String state = "Logout";
    if (request.getRequestedSessionId() != null
            && !request.isRequestedSessionIdValid()) {
        state = "Login";
    }
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="/dashboard" class="navbar-brand">Milestone</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="nav navbar-nav">
            <li class="nav-item m-2"><a href="/dashboard">Dashboard</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item"><a href="/<%=state%>"><%=state%></a></li>
        </ul>
    </div>
</nav>
