console.log("I am loading");
var URL = window.location.href;
console.log("you are at "+URL);

function delMilestone(title,id) {
  console.log(this);

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
      console.log(URL.slice(0, URL.indexOf("?")) + "delete/" + URL.slice(URL.indexOf("?")));

      if (result) {
        var delURL = URL.slice(0, URL.indexOf("?")) + "delete/?title="+title+"&ml="+ id;
        console.log(delURL);
        httpGetAsync(delURL,true,"DELETE");
        console.log("END");
      }
    }
  });
}

function httpGetAsync(theUrl, callback, request) {
  var xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function() {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
      callback(xmlHttp.responseText);
  }
  xmlHttp.open(request, theUrl, true); // true for asynchronous
  xmlHttp.send(null);
  // location.reload();

}

function editMilestone(t) {
  console.log("editing milestone");
  // console.log(ml).id;
  // var lol = ml.toJSON();
  console.log(this);
    var form = this;
    var userId = $form.find('input[name="userId"]').val();
    var url = 'http://localhost:8080/users/'+userId;
    var userName = $form.find('input[name="name"]').val();
    var userEmail = $form.find('input[name="email"]').val();

    // $.ajax({
    //   type : 'PUT',
    //   url : url,
    //   contentType: 'application/json',
    //   data : JSON.stringify({name: userName, email: userEmail}),
    //   success : function(data, status, xhr){
    //     window.location.replace("http://localhost:8080/users/"+userId);
    //   },
    //   error: function(xhr, status, error){
    //     $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
    //   }
    // });

}
