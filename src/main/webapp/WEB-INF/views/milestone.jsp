<%@ page contentType="text/html;charset=UTF-8" %>

<div class="row no-gutters">
<%--  <c:if test="${count%2==0}">--%>
<c:choose>
  <c:when test="${count%2==0}">
  <!-- timeline item 1 -->
  <div class="col-sm"> <!--spacer--> </div>
  <!-- timeline item 1 center dot -->
  <div class="col-sm-1 text-center flex-column d-none d-sm-flex">
    <div class="row h-50">
      <c:choose>
      <c:when test="${count==0}">
        <div class="col">&nbsp;</div>
      </c:when>
      <c:otherwise>
        <div class="col border-right">&nbsp;</div>
      </c:otherwise>
      </c:choose>
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
    <%@include file="milestone_card.jsp" %>

    <%--  </c:if>--%>
  </c:when>


  <c:otherwise>
    <%@include file="milestone_card.jsp" %>

    <div class="col-sm-1 text-center flex-column d-none d-sm-flex">
      <div class="row h-50">
        <div class="col border-right">&nbsp;</div>
        <div class="col">&nbsp;</div>
      </div>
      <h5 class="m-2">
        <span class="badge badge-pill bg-success">&nbsp;</span>
      </h5>
      <div class="row h-50">
        <div class="col border-right">&nbsp;</div>
        <div class="col">&nbsp;</div>
      </div>
    </div>
    <div class="col-sm"> <!--spacer--> </div>
  </c:otherwise>

</c:choose>
</div>
