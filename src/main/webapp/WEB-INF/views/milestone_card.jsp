<%@ page contentType="text/html;charset=UTF-8" %>
<%--<link rel="stylesheet" href="../../resources/css/project.css">--%>

<div class="col-sm py-2">
  <div class="card border-success shadow">
    <div class="card-body">
      <i class="fas fa-trash" onmouseover="console.log('${milestone.id}')"></i>

      <div class="float-right text-muted small">Start Date: ${milestone.startDate}</div>
      <br><br>
      <button class="btn btn-lg btn-outline-info btn-block card-title text-truncate" type="button"
              data-target="#${milestone.id}"
              data-toggle="collapse">
        &emsp;${milestone.title}&emsp;&emsp;▼
      </button>
      <div class="collapse " id="${milestone.id}">
        <div class="p-2 text-monospace">
          ${milestone.description}
        </div>
      </div>
    </div>
    <div class="card-footer">Due: ${milestone.dueDate}
      <span class="float-right">

<i class="fas fa-circle ${milestone.status}"></i>
  &emsp;&emsp;
  <button type="button" class="btn btn-primary" data-toggle="modal"
          data-target="#editForm${milestone.id}"><i class="fas fa-pen"></i></button>
  </span>
      <%@include file="milestone_form.jsp" %>

    </div>
  </div>
</div>