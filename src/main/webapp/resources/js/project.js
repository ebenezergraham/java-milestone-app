console.log("I am loading");
var URL = window.location.href;
console.log("you are at " + URL);

document.getElementById('add_form').setAttribute('action', window.location.href);

$(".status").on("click", function () {
  console.log($(this).val());
  $(this).prop('value', $(this).is(":checked"));
  console.log("Value of this checkbox " + $(this).attr("id") + " is " + $(this).val());
});


function delMilestone(prID, id, title) {
  bootbox.confirm({
    message: "Are you sure you want to delete milestone ".concat(title),
    buttons: {
      confirm: {
        label: 'Delete',
        className: 'btn-danger'
      },
      cancel: {
        label: 'Cancel',
        className: 'btn'
      }
    },
    callback: function (result) {
      console.log('This was logged in the callback: ' + result);

      if (result) {
        delCall(prID, id);
        console.log("the location url is " + location);
        location.reload();
        console.log("END");
      }
    }
  });
}


function delCall(projectID, mlID) {
  console.log("Deleting milestone");
  var delURL = URL + "&ml=" + mlID;
  var params = {
    projectId: projectID,
    milestoneId: mlID
  };
  $.ajax({
    type: 'DELETE',
    url: delURL,
    async: false,
    contentType: 'application/json',
    data: params,
    success: function (status) {
      console.log("Deletion completed" + status);
      location.reload();

    },
    error: function (xhr, status, error) {
      location.reload();
      console.log("couldn't delete element " + status)
      // $('#msg').html('<span style=\'color:red;\'>' + error + '</span>')
    }
  });

}


function editMilestone() {
  document.getElementById('theform').onsubmit = function () {
    console.log(document.getElementById('searchTerm').value);
    return false;
  };
  console.log("editing milestone");
  console.log(this);
  var form = this;
  var url = 'http://localhost:8080/milestone';

  $.ajax({
    type: 'PUT',
    url: url,
    contentType: 'application/json',
    data: JSON.stringify(form),
    success: function (data, status, xhr) {
    },
    error: function (xhr, status, error) {
      $('#msg').html('<span style=\'color:red;\'>' + error + '</span>')
    }
  });

}
