<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

        <form action="edit/?title=${title}&mlID=${milestone.id}" id="edit${milestone.id}"
              METHOD="post">
          <div class="form-group">
            <label for="title${milestone.id}">Title</label><br>
            <h3><input type="text" class="form-control modal-title" id="title${milestone.id}"
                       value="${milestone.title}" name="mlTitle"></h3>
          </div>

          <div class="form-group">
            <label for="description${milestone.id}">Description</label>
            <textarea rows ="5" cols = "50" id = "description${milestone.id}"
                      class="form-control" name="mlDescription">${milestone.description}</textarea>
          </div>

          <div class="form-group">
            <label for="start${milestone.id}">Start ${milestone.startDate}</label>
            <input type="datetime-local" class="form-control" id="start${milestone.id}"
                   value="${milestone.startDate}" name="mlStartDate">
          </div>

          <div class="form-group">
            <label for="end${milestone.id}">End</label>
            <input type="datetime-local" class="form-control" id="end${milestone.id}"
                   value="${milestone.endDate}" name="mlDueDate">
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
