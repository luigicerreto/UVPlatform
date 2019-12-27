$(document).ready(function() {
	var flag;
	$("#internshipTableDiv").css("display", "none");
	$('input[type="submit"]').prop("disabled", true);
	
	$('#intBtn, #extBtn').click(function(){
		// nasconde i tasti scelta
		$("div#internshipChoice").css("display", "none");
		$("#internshipTableDiv").css("display", "block");

		// tasto scelta cliccato
		var btnId = $(this).attr('id');
		flag = (btnId === "intBtn") ? 0 : 1;

		// mostra tabella in base alla scelta
		if(flag == 0){ // interno
			// intestazione tabella
			$("#internshipTable").children("thead").append(
					"<tr align=\"center\">" +
					"<th class=\"text-center\" align=\"center\">ID Docente</th>" +
					"<th class=\"text-center\" align=\"center\">Nome</th>" +
					"<th class=\"text-center\" align=\"center\">Sede</th>" +
					"<th class=\"text-center\" align=\"center\">Scelta</th>" +
					"</tr>"
			);

			// contenuto tabella
			$('#internshipTable').DataTable( {
				"order": [[ 0, "asc" ]],
				"lengthMenu": [[10, -1], [10, "Tutti"]],
				"autoWidth": false,
				"bAutoWidth": false,
				"processing": true,
				"ajax": {
					"url": "/UVPlatform/getInternalInternships",
					"dataSrc": "data",
					"type": "POST"
				},
				"columns" : [
					{ "data" : "id" },
					{ "data" : "name" },
					{ "data" : "place" },
					{ "data" : "choice" }
					],
					"language": {
						"sEmptyTable":     "Nessun tirocinio interno disponibile",
						"sInfo":           "",
						"sInfoEmpty":      "",
						"sInfoFiltered":   "",
						"sInfoPostFix":    "",
						"sInfoThousands":  ".",
						"sLengthMenu":     "Visualizza _MENU_ elementi",
						"sLoadingRecords": "Caricamento...",
						"sProcessing":     "Elaborazione...",
						"sSearch":         "Cerca:",
						"sZeroRecords":    "La ricerca non ha portato alcun risultato.",
						"oPaginate": {
							"sFirst":      "Inizio",
							"sPrevious":   '<i class="fa fa-caret-left"></i>',
							"sNext":       '<i class="fa fa-caret-right"></i>',
							"sLast":       "Fine"
						}
					}        
			});
		} else if (flag == 1){ // esterno
			// intestazione tabella
			$("#internshipTable").children("thead").append(
					"<tr align=\"center\">" +
					"<th class=\"text-center\" align=\"center\">ID Azienda</th>" +
					"<th class=\"text-center\" align=\"center\">Azienda</th>" +
					"<th class=\"text-center\" align=\"center\">Sede</th>" +
					"<th class=\"text-center\" align=\"center\">Data Stipula</th>" +
					"<th class=\"text-center\" align=\"center\">Scelta</th>" +
					"</tr>"
			);

			// contenuto tabella
			$('#internshipTable').DataTable( {
				"order": [[ 0, "asc" ]],
				"lengthMenu": [[10, -1], [10, "Tutti"]],
				"autoWidth": false,
				"bAutoWidth": false,
				"processing": true,
				"ajax": {
					"url": "/UVPlatform/getExternalInternships",
					"dataSrc": "data",
					"type": "POST"

				},
				"columns" : [
					{ "data" : "id" },
					{ "data" : "name" },
					{ "data" : "place" },
					{ "data" : "date" },
					{ "data" : "choice" }
					],
					"language": {
						"sEmptyTable":     "Nessun tirocinio esterno disponibile",
						"sInfo":           "",
						"sInfoEmpty":      "",
						"sInfoFiltered":   "",
						"sInfoPostFix":    "",
						"sInfoThousands":  ".",
						"sLengthMenu":     "Visualizza _MENU_ elementi",
						"sLoadingRecords": "Caricamento...",
						"sProcessing":     "Elaborazione...",
						"sSearch":         "Cerca:",
						"sZeroRecords":    "La ricerca non ha portato alcun risultato.",
						"oPaginate": {
							"sFirst":      "Inizio",
							"sPrevious":   '<i class="fa fa-caret-left"></i>',
							"sNext":       '<i class="fa fa-caret-right"></i>',
							"sLast":       "Fine"
						}
					}        
			});
		}
	});

	$('table').on('change', 'input[type="radio"]', function (e) {
		$('input[type="submit"]').prop("disabled", false);
	});

	$('#choiceForm').submit(function() {
		var id = $('input[type="radio"]:checked').attr('id');
		$.ajax(
				{
					url : absolutePath + "/addRequest",
					type : "POST",
					dataType : 'JSON',
					async : false,
					data : 
					{
						"choice": id,
						"type": flag
					},
					success : function(msg) 
					{
						if (!msg.result) 
						{
							showAlert(1,msg.error);
						} 
						else 
						{
							showAlert(0,msg.content);
							setTimeout(function() 
									{
								window.location.href = msg.redirect;
									},
									1000);
						}
					},
					error : function(msg) 
					{
						showAlert(1,"Impossibile effettuare la richiesta");
					}
				});
		return false;
	});

});