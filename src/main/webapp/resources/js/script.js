$("#nameInput").on('keypress', function (e) {
	if (e.which === 13) {
		document.getElementById("add-new-project").style.display = "none";
	}
});

$(".btn-add-project").on('click', function (e) {
		document.getElementById("add-new-project").style.display = "block";
});
