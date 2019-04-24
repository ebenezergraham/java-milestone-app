$("#nameInput").on('keypress', function (e) {
	if (e.which === 13) {
		localStorage.setItem("name", $("#nameInput").val());
		document.getElementById("nameInput").style.display = "none";
	}
});
