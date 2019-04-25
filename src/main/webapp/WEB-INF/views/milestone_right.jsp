<%--
  Created by IntelliJ IDEA.
  User: lina
  Date: 4/25/19
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%--%>
<%--  String state = "logout";--%>
<%--  if (request.getRequestedSessionId() != null--%>
<%--      && !request.isRequestedSessionIdValid()) {--%>
<%--    state = "login";--%>
<%--  }--%>
<%--%>--%>
<!-- timeline item 1 -->
  <div class="row no-gutters">
    <div class="col-sm"> <!--spacer--> </div>
    <!-- timeline item 1 center dot -->
    <div class="col-sm-1 text-center flex-column d-none d-sm-flex">
      <div class="row h-50">
        <div class="col">&nbsp;</div>
        <div class="col">&nbsp;</div>
      </div>
      <h5 class="m-2">
        <span class="badge badge-pill bg-light border">&nbsp;</span>
      </h5>
      <div class="row h-50">
        <div class="col border-right">&nbsp;</div>
        <div class="col">&nbsp;</div>
      </div>
    </div>

<%--    <%@include file="milestone_card.jsp" %>--%>
    <jsp:include page="milestone_card.jsp">
    <jsp:param name="milestone" value="${param.milestone}"/>
    <jsp:param name="title" value="FOO"/>
    <jsp:param name="count" value="${param.count}" />
    </jsp:include>



  </div>
