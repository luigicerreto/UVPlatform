$(document).ready(function() {
	$(".dropzoneUploader").dropzone({
		maxFiles: 1,
		acceptedFiles: ".pdf",
		accept: function(file, done){
			done();
		},
		init: function() {		
			this.on("maxfilesexceeded", function(file, errorMessage){
				this.removeFile(file);
				showAlert(1, errorMessage);		    	  
			});

			this.on("error", function(file, errorMessage) {
				this.removeFile(file);
				showAlert(1, errorMessage);
			});

			this.on("success", function(file, response) {
				var msg = jQuery.parseJSON(response);
				if(!msg.result){
					showAlert(1, msg.error);
				}	            		    
				else{
					file.previewElement.querySelector("[data-dz-name]").innerHTML = msg.content;
				}
			});
		}		  						
	});	

	$(document).on('click','#aggiungiAllegati', function(e){
		var filenames = [];
		var id_request = new URLSearchParams(window.location.search).get('id_request');

		$(".dz-filename").each(	function(index, element){
			filenames.push($(this).text());
		});

		if (filenames.length > 0){
			$.ajax({
				url : absolutePath + "/updateAttached",
				type : "POST",
				dataType : 'JSON',
				async : false,
				data : 
				{
					"filenames" : filenames,
					"id_request" : id_request,
					"flag": "0"
				},
				success : function(msg){
					if (!msg.result){
						showAlert(1,msg.error);
					} 
					else {
						showAlert(0,msg.content);
						setTimeout(function(){
							window.location.href = absolutePath + "/_areaTeacher_uvp/viewRequestTeacher.jsp";
						},1000);
					}
				},
				error : function(msg){
					showAlert(1,"Impossibile Recuperare i dati.");
				}
			});
		} 
		else {
			showAlert(1,"Controllare di aver inserito tutti gli allegati richiesti.");
		}
		return false;
	});
});