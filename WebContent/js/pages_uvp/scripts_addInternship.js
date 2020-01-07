$(document).ready(function() {
	$('form#addInternship').submit(function(e) {
		e.stopPropagation();
		e.preventDefault();
		
		var internshipType = $(this).data("internship");
		var dataArray = $(this).serializeArray();
		dataArray.push({name: "flag", value: internshipType});
		
		$.ajax({
			url : absolutePath + "/addInternship",
			type : "POST",
			dataType : 'JSON',
			async : false,
			data : dataArray,
			success : function(
					msg) {
				if (!msg.result) {
					showAlert(1,msg.error);
				} else {
					showAlert(0,msg.content);
					setTimeout(function(){
						if (internshipType == 0)
							window.location.href = absolutePath + "/_areaAdmin_uvp/viewTeacher.jsp";
						else if (internshipType == 1)
							window.location.href = absolutePath + "/_areaAdmin_uvp/viewCompany.jsp";
					},1000);
				}
			},
			error : function(msg) {
				showAlert(1,"Impossibile effettuare la registrazione");
			}
		});
	});
});