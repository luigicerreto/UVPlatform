$(document).ready(function() {
	$("div#internshipTableDiv").css("display", "none");
	
	$('.btn').click(function(){
		$("div#internshipChoice").css("display", "none");
		$('.panel').css("display", "none");
		
		$(".preloader").show();
		$.ajax({
			url : absolutePath + "/ServletStudent",
			type : "POST",
			dataType : 'JSON',
			async : false,
			data : {
				"flag" : 4
				//"type" : 0
			},
			success : function(msg) {
				if (!msg.result) {
					showAlert(1, msg.error);
				} else {
					$("#bodyInternshipTable").html(msg.content);
				}
			},
			error : function(msg) {
				showAlert(1, "Impossibile Recuperare i dati.");
			}
		});
		$(".preloader").hide();
		
		$("div#internshipTableDiv").css("display", "block");
	});
});