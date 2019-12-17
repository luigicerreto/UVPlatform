$(document).ready(function() {
	$("#internshipTableDiv").css("display", "none");
	

	$('.btn').click(function(){
		// nasconde i tasti scelta
		$("div#internshipChoice").css("display", "none");
		$("#internshipTableDiv").css("display", "block");
		
		// tasto scelta cliccato
		var btnId = $(this).attr('id');
		var flag = (btnId === "internoBtn") ? 0 : 1;

		// mostra tabella in base alla scelta
		if(flag == 0){ // interno
			// intestazione tabella
			$("#internshipTable").children("thead").append(
					"<tr align=\"center\">" +
					"<th class=\"text-center\" align=\"center\">ID Docente</th>" +
					"<th class=\"text-center\" align=\"center\">Nome</th>" +
					"<th class=\"text-center\" align=\"center\">Disponibilità</th>" +
					"<th class=\"text-center\" align=\"center\">Scelta</th>" +
					"</tr>"
			);
			// contenuto tabella
			$('#internshipTable').DataTable( {
		        "order": [[ 0, "desc" ]],
		        "lengthMenu": [[10, -1], [10, "Tutti"]],
		        "autoWidth": false,
		        "bAutoWidth": false,
		        "processing": true,
		        "ajax": {
		        	"url": "/UVPlatform/InternalTrainee",
		        	"dataSrc": "data",
		        	"type": "POST"
		        },
		        "columns" : [
		            { "data" : "id" },
		            { "data" : "name" },
		            { "data" : "availability" }
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
					"<th class=\"text-center\" align=\"center\">Data Inizio</th>" +
					"<th class=\"text-center\" align=\"center\">Scelta</th>" +
					"</tr>"
			);
			// contenuto tabella
			$('#internshipTable').DataTable( {
		        "order": [[ 0, "desc" ]],
		        "lengthMenu": [[10, -1], [10, "Tutti"]],
		        "autoWidth": false,
		        "bAutoWidth": false,
		        "processing": true,
		        "ajax": {
		        	"url": "/UVPlatform/ExternalTrainee",
		        	"dataSrc": "data",
		        	"type": "POST"
		        },
		        "columns" : [
		            { "data" : "id" },
		            { "data" : "name" },
		            { "data" : "place" },
		            { "data" : "date" }
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
});