<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--Timeline code inspired from https://www.codeply.com/go/dI6CknFxts/bootstrap-4-timeline--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

  <h2 class="font-weight-light text-center text-muted py-3">Bootstrap 4 Timeline</h2>

  <hr>

  <div class="container py-2">
    <c:set var = "right" value = "${true}"/>
      <c:set var = "count" value = "0" scope="page"/>
        <c:forEach items="${allMilestones}" var="milestone">
          <c:choose>
            <c:when test="${right==true}">
              <c:set var = "right" value = "${false}"/>

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
      <!-- timeline item 1 event content -->
      <div class="col-sm py-2">
        <div class="card">
          <div class="card-body">
            <div class="float-right text-muted small">Jan 9th 2019 7:00 AM</div>
            <h4 class="card-title text-muted">Day 1 Orientation</h4>
            <p class="card-text">Welcome to the campus, introduction and get started with the tour.</p>
          </div>
        </div>
      </div>
    </div>
    <!--/row-->
            </c:when>

            <c:otherwise>
              <c:set var = "right" value = "${true}"/>

              <!-- timeline item 2 -->
    <div class="row no-gutters">
      <div class="col-sm py-2">
        <div class="card border-success shadow">
          <div class="card-body">
            <div class="float-right text-success small">Start Date: ${milestone.startDate}</div>
            <h4 class="card-title text-success"></h4>
            <p class="card-text"></p>
<%--            <button class="btn btn-sm btn-outline-secondary" type="button" data-target="#t22_details" data-toggle="collapse">Show Details ▼</button>--%>
            <button class="btn btn-lg btn-outline-secondary" type="button"
<%--                    data-target="#t22_details"--%>
                    data-target="#_${milestone.id}"
                    data-toggle="collapse">
              &emsp;&emsp;${milestone.title}&emsp;&emsp;▼
            </button>
            <div class="collapse border" id="_${milestone.id}">
              <div class="p-2 text-monospace">
                  ${milestone.description}
              </div>
            </div>
          </div>
        </div>
      </div>
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
    </div>
    <!--/row-->
    <!-- timeline item 3 -->
    <div class="row no-gutters">
      <div class="col-sm"> <!--spacer--> </div>
      <div class="col-sm-1 text-center flex-column d-none d-sm-flex">
        <div class="row h-50">
          <div class="col border-right">&nbsp;</div>
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
      <div class="col-sm py-2">
        <div class="card">
          <div class="card-body">
            <div class="float-right text-muted small">Jan 11th 2019 8:30 AM</div>
            <h4 class="card-title">Day 3 Sessions</h4>
            <p>Shoreditch vegan artisan Helvetica. Tattooed Codeply Echo Park Godard kogi, next level irony ennui twee squid fap selvage. Meggings flannel Brooklyn literally small batch, mumblecore PBR try-hard kale chips. Brooklyn vinyl lumbersexual
              bicycle rights, viral fap cronut leggings squid chillwave pickled gentrify mustache.</p>
          </div>
        </div>
      </div>
    </div>
    <!--/row-->
    <!-- timeline item 4 -->
    <div class="row no-gutters">
      <div class="col-sm py-2">
        <div class="card">
          <div class="card-body">
            <div class="float-right text-muted small">Jan 12th 2019 11:30 AM</div>
            <h4 class="card-title">Day 4 Wrap-up</h4>
            <p>Join us for lunch in Bootsy's cafe across from the Campus Center.</p>
          </div>
        </div>
      </div>
      <div class="col-sm-1 text-center flex-column d-none d-sm-flex">
        <div class="row h-50">
          <div class="col border-right">&nbsp;</div>
          <div class="col">&nbsp;</div>
        </div>
        <h5 class="m-2">
          <span class="badge badge-pill bg-light border">&nbsp;</span>
        </h5>
        <div class="row h-50">
          <div class="col">&nbsp;</div>
          <div class="col">&nbsp;</div>
        </div>
      </div>
      <div class="col-sm"> <!--spacer--> </div>
    </div>
    <!--/row-->
            </c:otherwise>
          </c:choose>
        </c:forEach>
  </div>


</body>
</html>
