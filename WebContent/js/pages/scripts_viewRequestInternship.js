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
			{ "data" : "attached", "render": "[<br>]" },
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
	// azioni tirocinio
	$(document).on('click', 'label.actionInternship', function(e){
		e.stopPropagation();
		e.preventDefault();

		var action = $(this).children("input").data("action");
		var id_request = $(this).children("input").attr("id");

		if(!($(this).attr('disabled') == "disabled")){
			if (action === "upload"){ // carica allegato
				var url_redirect = absolutePath + "/_areaStudent_uvp/uploadAttached_uvp.jsp?id_request=" + id_request;
				$(window.location).attr('href', url_redirect);
			} else if (action === "download"){ // scarica allegato
				var url_download = absolutePath + "/Downloader?idRequest=" + id_request;
				$(window.location).attr('href', url_download);
			}
		}
	});
});