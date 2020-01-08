$(document).ready(function() {
	// popup info
	$(document).on('click', 'label.info input', function(e){
		var id = $(this).attr("id");
		var flag = $(this).data("type-info");

		var data;
		
		if (flag == 0)
			data = ({ "flag": flag, "id": id });
		else if (flag == 1)
			data = ({ "id": id });
		
		$.ajax({
			type: "POST",
			url: "/UVPlatform/showInfo",
			async : false,
			data: data,
			dataType: "JSON",
			success: function(data) {
				if(data.type == 0){
					$('h1.details').html("DETTAGLI RICHIESTA");
					$('div.info').html(
							'<h4> ID: ' + data.id + '</h4>' +
							'<h4> STATO: ' + data.status + '</h4>' +
							'<h4> TIPO: Tirocinio Interno</h4>' +
							'<h4> ALLEGATI: ' + data.attached + '</h4>' +
							'<p>' +
							'<h3> INFO TIROCINIO </h3>' +
							'<h4> TEMA: ' + data.theme + '</h4>' +
							'<h4> DOCENTE: ' + data.tutor + '</h4>' +
							'<h4> EMAIL DOCENTE: ' + data.tutor_email + '</h4>' +
							'<h4> SEDE: ' + data.place + '</h4>' +
							'<h4> OBIETTIVI: ' + data.goals + '</h4>' +
							'<h4> RISORSE: ' + data.resources + '</h4>'
					);
				} else if (data.type == 1) {
					$('h1.details').html("DETTAGLI RICHIESTA");
					$('div.info').html(
							'<h4> ID: ' + data.id + '</h4>' +
							'<h4> STATO: ' + data.status + '</h4>' +
							'<h4> TIPO: Tirocinio Esterno</h4>' +
							'<h4> ALLEGATI: ' + data.attached + '</h4>' +
							'<p>' +
							'<h3> INFO TIROCINIO </h3>' +
							'<h4> AZIENDA: ' + data.company + '</h4>' +
							'<h4> EMAIL AZIENDA: ' + data.company_email + '</h4>' +
							'<h4> DATA SCADENZA: ' + data.date_convention + '</h4>' +
							'<h4> DURATA (ORE): ' + data.duration_convention + '</h4>' +
							'<h4> INFO: ' + data.info + '</h4>'
					);
				}
			},
			error : function(msg) {
				$('h1.details').html(" ");
				$('div.info').html(" ");
				showAlert(1,"Impossibile ottenere le info");
			}
		});
	});
});