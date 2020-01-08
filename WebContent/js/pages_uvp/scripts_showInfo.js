$(document).ready(function() {
	// popup info
	$(document).on('click', 'label.infoInternship', function(e){
		e.stopPropagation();
		e.preventDefault();

		var id = $(this).children("input").attr("id");

		$.ajax({
			type: "POST",
			url: "/UVPlatform/showInternshipInfo",
			async : false,
			data: {
				"id_internship" : id,
				"type_internship" : flag
			},
			dataType: "JSON",
			success: function(data) {
				if(flag == 0){
					$('div.info').html(
							'<h4> ID.: ' + data.id + '</h4>' +
							'<h4> TUTOR: ' + data.tutor_name + '</h4>' +
							'<h4> TEMA: ' + data.theme + '</h4>' +
							'<h4> DISPONIBILITA\': ' + data.availability + '</h4>' +
							'<h4> RISORSE: ' + data.resources + '</h4>' +
							'<h4> OBIETTIVI: ' + data.goals + '</h4>' +
							'<h4> SEDE: ' + data.place + '</h4>'
					);
				} else if (flag == 1) {
					$('div.info').html(
							'<h4> ID.: ' + data.id + '</h4>' +
							'<h4> NOME: ' + data.name + '</h4>' +
							'<h4> DURATA: ' + data.duration_convention + '</h4>' +
							'<h4> DATA STIPULA: ' + data.date_convention + '</h4>' +
							'<h4> DISPONIBILITA\': ' + data.availability + '</h4>' +
							'<h4> INFO: ' + data.info + '</h4>' 
					);
				}
			},
			error : function(msg) {
				showAlert(1,"Impossibile ottenere le info");
			}
		});
	});
});