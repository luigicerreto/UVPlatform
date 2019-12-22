$(document).ready(function() {

});

function showDataUser() {
	$(".preloader").show();

	$.ajax({
		url : absolutePath + "/ShowProfile",
		type : "POST",
		dataType : 'JSON',
		async : false,
		data : {
		},
		success : function(msg) {
			if (!msg.result) {
				showAlert(1, msg.error);
			}
			else 
			{
				$("#name").val(msg.name);
				$("#surname").val(msg.surname);
				$("#email").val(msg.email)
				$("#phone").val(msg.phone);

			}
		},
		error : function(msg) {
			showAlert(1, "Impossibile Recuperhhhhhare i dati.");
		}
	});

	$(".preloader").hide();
} 