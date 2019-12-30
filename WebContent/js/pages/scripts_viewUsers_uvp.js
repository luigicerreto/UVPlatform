$(document).ready(function() {
	// contenuto tabella
	var table = $('#AdminUserTable').DataTable( {
		"order": [[ 1, "asc" ]],
		"lengthMenu": [[10, -1], [10, "Tutti"]],
		"autoWidth": false,
		"bAutoWidth": false,
		"processing": true,
		"ajax": {
			"url": absolutePath + "/showUsers",
			"dataSrc": "data",
			"type": "POST"
		},
		"columns" : [
			{ "data" : "email" },
			{ "data" : "name" },
			{ "data" : "surname" },
			{ "data" : "sex" },
			{ "data" : "serial" },
			{ "data" : "phone" },
			],
			"language": {
				"sEmptyTable":     "Nessun utente",
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
