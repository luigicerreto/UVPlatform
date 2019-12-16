$(document).ready(function() {
	$("div#intInternshipTable").css("display", "none");
	$("div#extInternshipTable").css("display", "none");
	
	$('.btn').click(function(){
		var btnId = $(this).attr('id');
		var flag = (btnId === "internoBtn") ? 0 : 1;
		
		if(btnId === "internoBtn"){
			$("div#intInternshipTable").css("display", "block");
			$("div#internshipChoice").css("display", "none");
		} else if (btnId === "esternoBtn"){
			$("div#extInternshipTable").css("display", "block");
			$("div#internshipChoice").css("display", "none");
		}
		
		$(".preloader").show();
		$.ajax({
			url : absolutePath + "/ServletInternship",
			type : "POST",
			dataType : 'JSON',
			async : false,
			data : {
				"type" : flag
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
		
		$("div#internshipTable").css("display", "block");
	});
});