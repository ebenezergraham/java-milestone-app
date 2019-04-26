<!-- Modal -->
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="editForm${milestone.id}" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
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
            <input type="datetime-local" class="form-control" id="start${milestone.id}"
                   value="${milestone.startDate}" name="mlStartDate">
          </div>

          <div class="form-group">
            <label for="due${milestone.id}">End</label>
            <input type="datetime-local" class="form-control" id="due${milestone.id}"
                   value="${milestone.dueDate}" name="mlDueDate">
          </div>

          <div class="form-group">
            <label for="end${milestone.id}">End</label>
            <input type="datetime-local" class="form-control" id="end${milestone.id}"
                   value="${milestone.endDate}" name="mlEndDate">
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Save changes</button>
          </div>
        </form>

      </div>
    </div>
  </div>
</div>
