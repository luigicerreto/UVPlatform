$(document).ready(function() {
	// contenuto tabella
	$('#studentTableInternship').DataTable( {
		"order": [[ 0, "desc" ]],
		"lengthMenu": [[10, -1], [10, "Tutti"]],
		"autoWidth": false,
		"bAutoWidth": false,
		"processing": true,
		"ajax": {
			"url": "/UVPlatform/showRequest",
			"dataSrc": "data",
			"type": "POST"
		},
		"columns" : [
			{ "data" : "id" },
			{ "data" : "user_serial" },
			{ "data" : "attached" },
			{ "data" : "type" },
			{ "data" : "status" },
			{ "data" : "actions" }
			],
			"language": {
				"sEmptyTable":     "Nessuna richiesta di tirocinio",
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