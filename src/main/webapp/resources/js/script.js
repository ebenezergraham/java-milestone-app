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
	console.log($.param(params));
	$.ajax({
		url: window.location.href+'?projectId='+e.target.id,
		type: 'DELETE',
		contentType: 'application/json; charset=utf-8',
		data: $.param(params),
		success: function (result) {
			console.log('working');
			console.log(e)
			id='#'+e.currentTarget.parentNode.id;
			document.getElementById(e.currentTarget.parentNode.id).style.visibility ="hidden";

		}
	});
});

$(".share-project").on('click', function (e) {
	$.post( "/view?projectId="+e.target.parentNode.id, function( data ) {
		$( "#shareable-link" ).parent().parent().css("visibility","visible");
		$( "#shareable-link" ).val( window.location.host+'/view/'+data );
		setTimeout(function(){
			$( "#shareable-link" ).parent().parent().css("visibility","hidden")
		}, 10000);
	});
});
