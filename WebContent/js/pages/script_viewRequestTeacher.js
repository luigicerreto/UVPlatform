$(document).ready(function() {

});

function showData() {
	$(".preloader").show();

	$.ajax({
		url : absolutePath + "/ShowRequest_Teacher",
		type : "POST",
		dataType : 'JSON',
		async : false,
		data : {
		},
		success : function(msg) {
			if (!msg.result) {
				showAlert(1, msg.error);
			} else {
				$("#bodyTeacherTableInternship").html(msg.content);
			}
		},
		error : function(msg) {
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});

	$(".preloader").hide();
	return false;
} 