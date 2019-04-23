console.log("I am loading");
var URL = window.location.href;
console.log("you are at "+URL);

function delMilestone(title,id) {
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
        var delURL = URL.slice(0, URL.indexOf("?")) + "delete/?id="+ id;
        console.log(delURL);
        httpGetAsync(delURL,true);
        console.log("END");
      }
    }
  });
}

function httpGetAsync(theUrl, callback) {
  var xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function() {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
      callback(xmlHttp.responseText);
  }
  xmlHttp.open("DELETE", theUrl, true); // true for asynchronous
  xmlHttp.send(null);
  location.reload();

}

function editMilestone(t) {
  // console.log("editing milestone");
  // console.log(ml).id;
  // var lol = ml.toJSON();
  // console.log(lol);
  bootbox.prompt({
    title: t,
    inputType: 'textarea',

    callback: function (result) {
      console.log(result);
    }
  });
}
