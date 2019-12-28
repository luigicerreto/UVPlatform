$(document).ready(function() {
	$(document).on('click','#aggiungiAllegati', function(e) {
		var filenames = [];
		var new_request = new URLSearchParams(window.location.search).get('new_request');
		var id_request = new URLSearchParams(window.location.search).get('id_request');
		
		$(".dz-filename").each(	function(index, element) {
			filenames.push($(this).text());
		});

		if (filenames.length > 0) {
			$.ajax({
				url : absolutePath + "/addAttached",
				type : "POST",
				dataType : 'JSON',
				async : false,
				data : {
					"filenames" : filenames,
					"new_request" : new_request,
					"id_request" : id_request
				},
				success : function(msg) {
					if (!msg.result) {
						showAlert(1,msg.error);
					} else {
						showAlert(0,msg.content);
						setTimeout(function() {
							window.location.href = absolutePath + "/_areaStudent_uvp/viewRequestInternship.jsp";
						}, 1000);

					}
				},
				error : function(msg) {
					showAlert(1,"Impossibile Recuperare i dati.");
				}
			});
		} else {
			showAlert(1,"Controllare di aver inserito tutti gli allegati richiesti.");
		}
	});
});