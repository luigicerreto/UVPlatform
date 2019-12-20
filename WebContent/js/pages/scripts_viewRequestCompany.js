$(document).ready(function() {

	
	
	// contenuto tabella
	$('#CompanyTableInternship').DataTable( {
		"order": [[ 0, "asc" ]],
		"lengthMenu": [[10, -1], [10, "Tutti"]],
		"autoWidth": false,
		"bAutoWidth": false,
		"processing": true,
		"ajax": {
			"url": absolutePath + "/showRequest_Company",
			"dataSrc": "data",
			"type": "POST"
		},
		"columns" : [
			{ "data" : "id" },
			{ "data" : "attached" },
			{ "data" : "theme" },
			{ "data" : "name" },
			{ "data" : "surname" },
			{ "data" : "type" },
			{ "data" : "state" },
			{ "data" : "azioni" },
			
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
	
});