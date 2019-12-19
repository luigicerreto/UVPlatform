$(document).ready(function() {

});

function showDataUser() {
	$(".preloader").show();

	$.ajax({
		url : absolutePath + "/showProfile",
		type : "POST",
		dataType : 'JSON',
		async : false,
		data : {
		},
		success : function(msg) {
			if (!msg.result) {
				showAlert(1, msg.error);
			} else 
			{
				document.getElementById("name").innerHTML = msg.name;
				$("#name").text(msg.name);
				$("#surname").html(msg.surname);
				$("#email").value(msg.email)
				$("#phone").html(msg.phone);
				
			}
		},
		error : function(msg) {
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});

	$(".preloader").hide();
} 