<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--Timeline code inspired from https://www.codeply.com/go/dI6CknFxts/bootstrap-4-timeline--%>
<html>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
  <title>Milestone Planner</title>
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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a href="/" class="navbar-brand">Milestone</a>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="nav navbar-nav">
      <li class="nav-item m-2"><a href="/dashboard">Dashboard</a></li>
    </ul>

    <ul class="nav navbar-nav navbar-right">
      <li class="nav-item"><a href="/login">Login</a></li>
    </ul>
  </div>
</nav>

<div class="container">
  <h1>Project ${title}</h1>

  <div class="container py-2">
  <c:set var = "right" value = "${true}"/>
    <c:set var = "count" value = "0" scope="page"/>
    <c:forEach items="${allMilestones}" var="milestone">
<%--      <c:set var="count" value="${count + 1}"/>--%>
      <c:choose>
      <c:when test="${right==true}">
        <c:set var = "right" value = "${false}"/>

<%--        <c:set var = "id" value = "${milestone.id}"/>--%>
<%--        <c:out value="${id}"></c:out>--%>
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
              <div class="card border-success shadow">
                <div class="card-body">
                  <i class="fas fa-trash"></i>

                  <div class="float-right text-muted small">Start Date: ${milestone.startDate}</div>
                  <br>
                  <button class="btn btn-lg btn-outline-info btn-block card-title" type="button"
                          data-target="#_${milestone.id}"
                          data-toggle="collapse">
                    &emsp;&emsp;${milestone.title}&emsp;&emsp;▼
                  </button>
                  <div class="collapse" id="_${milestone.id}" >
                    <div class="p-2 text-monospace">
                        ${milestone.description}
                    </div>
                  </div>
                  <div class="small">
                  Due: ${milestone.endDate}
                  <span>
                    &emsp;&emsp;
                    <i class="fas fa-circle"></i>
                    &emsp;&emsp;
                    <i class="fas fa-pen"></i>
                  </span>
                  </div>
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
                  <i class="fas fa-trash"></i>
                  <div class="float-right text-muted small">Start Date: ${milestone.startDate}</div>
                  <br><br>
                  <button class="btn btn-lg btn-outline-info btn-block card-title" type="button"
                          data-target="#_${milestone.id}"
                          data-toggle="collapse">
                    &emsp;${milestone.title}&emsp;&emsp;▼
                  </button>
                  <div class="collapse " id="_${milestone.id}">
                    <div class="p-2 text-monospace">
                        ${milestone.description}
                    </div>
                  </div>
                </div>
                <div class="card-footer">Due: ${milestone.endDate}
                  <span>
                    &emsp;&emsp;
                    <i class="fas fa-circle"></i>
                    &emsp;&emsp;
                    <i class="fas fa-pen"></i>
                  </span>
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
        </c:otherwise>
        </c:choose>
      </c:forEach>

      </div>
</div>






<%--<footer class="footer">--%>
<%--  <p>footer content</p>--%>
<%--</footer>--%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>


</html>
