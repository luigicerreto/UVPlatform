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
});

function createPdf(){
	var x=new XMLHttpRequest();
	x.open("POST", "/UVPlatform/createPdf", true);
	x.responseType = 'blob';
	x.onload=function(e){download(x.response, "modulo.pdf", "application/pdf"); }
	x.send();
}