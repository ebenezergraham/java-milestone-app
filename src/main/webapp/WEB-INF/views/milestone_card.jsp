<%@ page contentType="text/html;charset=UTF-8" %>

<div class="col-sm py-2">
  <div class="card border-success shadow">
    <div class="card-body">
      <i class="fas fa-trash red"
         onclick="delMilestone('${id}','${milestone.id}','${milestone.title}')"
         onmouseover="console.log('${milestone.id}')"></i>

      <div class="float-right text-muted small">Start Date: ${milestone.startDate}</div>
      <br><br>
      <button class="btn btn-lg btn-outline-info btn-block card-title" type="button"
              data-target="#_${milestone.id}"
              data-toggle="collapse">${milestone.title} <span class="px-3">&#8675;</span>
      </button>
      <div class="collapse " id="_${milestone.id}">
        <div class="p-2 text-monospace">
          ${milestone.description}
        </div>
      </div>
    </div>
    <div class="card-footer">Due: ${milestone.dueDate}
      <span class="float-right">
<i class="fas fa-circle ${milestone.status}"></i>&nbsp;&nbsp;
  <button type="button" class="btn bg-green text-white" data-toggle="modal"
          data-target="#editForm${milestone.id}"><i class="fas fa-pen"></i></button>
  </span>
      <%@include file="milestone_form.jsp" %>

    </div>
  </div>
</div>
<script src="../../resources/js/jquery-3.3.1.min.js"></script>
