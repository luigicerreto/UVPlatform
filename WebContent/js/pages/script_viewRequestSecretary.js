$(document).ready(function() {
	// contenuto tabella
	$('#TeacherTableInternship').DataTable( {
		"order": [[ 0, "asc" ]],
		"lengthMenu": [[10, -1], [10, "Tutti"]],
		"autoWidth": false,
		"bAutoWidth": false,
		"processing": true,
		"ajax": {
			"url": absolutePath + "/ShowRequest_Teacher",
			"dataSrc": "data",
			"type": "POST"
		},
		"columns" : [
			{ "data" : "id" },
			{ "data" : "theme" },
			{ "data" : "attached" },
			{ "data" : "name" },
			{ "data" : "surname" },
			{ "data" : "type" },
			{ "data" : "state" },
			{ "data" : "actions" },
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