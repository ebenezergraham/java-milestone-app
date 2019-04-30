$("#nameInput").on('keypress', function (e) {
	if (e.which === 13) {
		document.getElementById("add-new-project").style.display = "none";
	}
});

$(".btn-add-project").on('click', function (e) {
	document.getElementById("add-new-project").style.display = "block";
});

$(".btn-delete-project").on('click', function (e) {
	var params = {
		projectId: e.target.id,
	};
	$.ajax({
		url: window.location.href + '?projectId=' + e.target.id,
		type: 'DELETE',
		contentType: 'application/json; charset=utf-8',
		data: $.param(params),
		success: function () {
			document.getElementById(e.currentTarget.parentNode.parentNode.id).style.display = "none";

		}
	});
});

$(".share-project").on('click', function (e) {
	console.log("/view?projectId=" + e.target.parentNode.parentNode.id)
	$.post("/view?projectId=" + e.target.parentNode.parentNode.id, function (data) {
		$("#shareable-link").parent().parent().css("display", "block");
		$("#shareable-link").val(window.location.host + '/view/' + data);
		setTimeout(function () {
			$("#shareable-link").parent().parent().css("display", "none")
		}, 10000);
	});
});
