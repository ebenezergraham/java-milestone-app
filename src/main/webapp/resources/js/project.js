console.log("I am loading");
var URL = window.location.href;
console.log("you are at " + URL);

document.getElementById('add_form').setAttribute('action', window.location.href);

$(".status").on("click", function () {
	// console.log($(this).getAttribute("id"));
	console.log($(this).val());
	// console.log($(this).is(":checked"));
	$(this).prop('value', $(this).is(":checked"));
	console.log("Value of this checkbox " + $(this).attr("id") + " is " + $(this).val());

});


function delMilestone(title, id) {
	var $this = $(Event.target);
	console.log($this.attributes);
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
				var delURL = URL.slice(0, URL.indexOf("?")) + "delete/?title=" + title + "&ml=" + id;
				console.log(delURL);
				httpGetAsync(delURL, true, "DELETE");
				console.log("END");
			}
		}
	});
}

// })

function httpGetAsync(theUrl, callback, request) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
			callback(xmlHttp.responseText);
	}
	xmlHttp.open(request, theUrl, true); // true for asynchronous
	xmlHttp.send(null);
	// location.reload();

}

//
// function editMilestone(t) {
//   console.log("editing milestone");
//   // console.log(ml).id;
//   // var lol = ml.toJSON();
//   console.log(this);
//     var form = this;
//     var mlTitle = $form.find('input[name="mlTitle"]').val();
//     var mlDescription = $form.find('input[name="mlDescription"]').val();
//     // var mlStatus = $form.find('input[name="mlStatus"]').val();
//     var mlStartDate = $form.find('input[name="mlStartDate"]').val();
//     var mlDue_Date = $form.find('input[name="mlDueDate"]').val();
//     var mlend_Date = $form.find('input[name="mlEndDate"]').val();
//     // var url = 'http://localhost:8080/project?title="'+t;
//     // var userName = $form.find('input[name="name"]').val();
//     // var userEmail = $form.find('input[name="email"]').val();
//
//     $.ajax({
//       type : 'PUT',
//       url : URL,
//       contentType: 'application/json',
//       data : JSON.stringify({
//         'title': mlTitle,'description': mlDescription,
//         // 'status':mlStatus,
//           'startDate':mlStartDate,'dueDate':mlDue_Date,'endDate':mlend_Date}),
//       success : function(data, status, xhr){
//         location.reload();
//         // window.location.replace("http://localhost:8080/users/"+userId);
//       },
//       error: function(xhr, status, error){
//         // $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
//         console.log(error);
//       }
//     });
//
// }

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
