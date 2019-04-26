<!-- Modal -->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3>Add Milestone</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

        <form action="project/?title=${title}" METHOD="post">
          <div class="form-group">
            <label for="title${milestone.title}">Title</label><br>
            <h3><input type="text" class="form-control modal-title" id="title${milestone.id}"
                        name="mlTitle"></h3>
          </div>

          <div class="form-group">
            <label for="description${milestone.id}">Description</label>
            <textarea rows ="5" cols = "50" id = "description${milestone.id}" maxlength="250"
                      class="form-control" name="mlDescription"></textarea>
          </div>

          <div class="form-group">
            <label for="start${milestone.id}">Start ${milestone.startDate}</label>
            <input type="date" class="form-control" id="start${milestone.id}"
                    name="mlStartDate">
          </div>

          <div class="form-group">
            <label for="due${milestone.id}">Due</label>
            <input type="date" class="form-control" id="due${milestone.id}"
                   name="mlDueDate">
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
