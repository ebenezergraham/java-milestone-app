<!-- Modal -->
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="editForm${milestone.id}" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3>Edit ${milestone.title}</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

        <form action="/project/edit/?title=${title}&mlID=${milestone.id}" id="edit${milestone.id}"
              METHOD="post">
          <div class="form-group">
            <label for="t${milestone.title}">Title</label><br>
            <h3><input type="text" class="form-control modal-title" id="t${milestone.title}" value="${milestone.title}" name="mlTitle"></h3>
          </div>

          <div class="form-group">
            <label for="desc${milestone.title}">Description</label>
            <textarea rows ="4" cols = "50" id = "desc${milestone.title}" maxlength="250"
                      class="form-control" name="mlDescription">${milestone.description}</textarea>
          </div>

          <div class="form-group">
            <label for="start${milestone.id}">Start ${milestone.startDate}</label>
            <input type="date" class="form-control" id="start${milestone.id}"
                   value="${milestone.startDate}" name="mlStartDate">
          </div>

          <div class="form-group">
            <label for="due${milestone.id}">Due</label>
            <input type="date" class="form-control" id="due${milestone.id}"
                   value="${milestone.dueDate}" name="mlDueDate">
          </div>
          ${milestone.status}

          <div class="form-group">
<%--            <c:if test="${milestone.status=='on'}">--%>
<%--              <i class="fas fa-circle ${milestone.status}"></i>--%>
<%--            </c:if>--%>
<%--            <c:if test="${milestone.status!='on'}">--%>
<%--              <i class="fas fa-circle off"></i>--%>
<%--            </c:if>--%>
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input status" id="customCheck${milestone.id}"
                     <c:if test="${milestone.status=='true'}"> checked</c:if> value="${milestone.status}"
                    name="mlStatus">
              <label class="custom-control-label" for="customCheck${milestone.id}">Completed</label>
            </div>
<%--            <div class="custom-control custom-radio custom-control-inline">--%>
<%--              <input type="radio" name="customRadioInline1" class="custom-control-input">--%>
<%--              <label class="custom-control-label" value="">Completed</label>--%>
<%--            </div>--%>
<%--            <div class="custom-control custom-radio custom-control-inline">--%>
<%--              <input type="radio" id="customRadioInline2" name="customRadioInline1" class="custom-control-input">--%>
<%--              <label class="custom-control-label" for="customRadioInline2">Not Completed</label>--%>
<%--            </div>--%>
          </div>

<%--          <div class="modal-footer">--%>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary float-right">Save changes</button>
<%--          </div>--%>
        </form>

      </div>
    </div>
  </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/js/project.js"></script>
