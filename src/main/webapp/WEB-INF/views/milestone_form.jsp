<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Modal -->
<div class="modal fade" id="editForm${milestone.id}" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3>Edit Milestone</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

        <form action="/project/edit/?id=${id}&mlID=${milestone.id}" id="edit${milestone.id}"
              METHOD="post">
          <div class="form-group">
            <label for="t${milestone.title}" maxlength="30">Title</label><br>
            <h3><input type="text" class="form-control modal-title" id="t${milestone.title}" value="${milestone.title}" name="mlTitle"></h3>
          </div>

          <div class="form-group">
            <label for="desc${milestone.title}">Description</label>
            <textarea rows="4" cols="50" id="desc${milestone.title}" maxlength="250"
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

          <div class="form-group">
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input status" id="customCheck${milestone.id}"
              <c:if test="${milestone.status=='true'}"> checked</c:if> value="${milestone.status}"
                     name="mlStatus">
              <label class="custom-control-label" for="customCheck${milestone.id}">Completed</label>
            </div>
          </div>

          <button type="button" class="btn btn-secondary " data-dismiss="modal">Close</button>
          <button type="submit" class="btn bg-green text-white float-right">Save changes</button>
        </form>

      </div>
    </div>
  </div>
</div>
