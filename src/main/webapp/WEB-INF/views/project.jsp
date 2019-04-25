<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--Timeline code inspired from https://www.codeply.com/go/dI6CknFxts/bootstrap-4-timeline--%>
<html>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<%--  <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>--%>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

  <title>Milestone Planner</title>

</head>
<script>
  document.getElementById('add_form').setAttribute('action', window.location.href);
</script>
<body>
<%@include file="menu.jsp" %>


<%--<div class="container">--%>
  <h1>Project ${title}</h1>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add">Add Milestone</button>

<%@include file="add_milestone_form.jsp" %>

<%--  <button>Add Milestone</button>--%>
  <div class="container py-2">
<%--  <c:set var = "right" value = "${true}"/>--%>
    <c:set var = "count" value = "0" scope="request"/>
    <c:forEach items="${allMilestones}" var="milestone">
      <%@ include file="milestone.jsp" %>
      <c:set var = "count" value = "${count+1}" scope="page"/>

    </c:forEach>

  </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>

<script type="text/javascript"  src="/resources/js/project.js"></script>
<script src="/resources/js/bootbox.min.js"></script>


</body>


</html>


<%--      <c:choose>--%>
<%--      <c:when test="${count%2==0}">--%>
<%--        <%@ include file="milestone_right.jsp" %>--%>
<%--      </c:when>--%>

<%--        <c:otherwise>--%>
<%--&lt;%&ndash;          <c:set var = "right" value = "${true}"/>&ndash;%&gt;--%>
<%--          <%@include file="milestone_left.jsp" %>--%>

<%--        </c:otherwise>--%>
<%--        </c:choose>--%>
