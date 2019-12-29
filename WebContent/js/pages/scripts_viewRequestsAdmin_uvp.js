$(document).ready(function() {
	// contenuto tabella
	var table = $('#AdminTableInternship').DataTable( {
		"order": [[ 0, "desc" ]],
		"lengthMenu": [[10, -1], [10, "Tutti"]],
		"autoWidth": false,
		"bAutoWidth": false,
		"processing": true,
		"ajax": {
			"url": absolutePath + "/showRequest_Admin",
			"dataSrc": "data",
			"type": "POST"
		},
		"columns" : [
			{ "data" : "id" },
			{ "data" : "theme" },
			{ "data" : "attached", "render": "[<br>]" },
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
	// azioni tirocinio
	$(document).on('click', 'label.actionInternship', function(e){
		e.stopPropagation();
		e.preventDefault();

		var action = $(this).children("input").data("action");
		var id_request = $(this).children("input").attr("id");

		if(!($(this).attr('disabled') == "disabled")){
			if(action === "accept"){ // accetta richiesta
				$.ajax({
					url : absolutePath + "/finishInternship",
					type : "POST",
					dataType : 'JSON',
					async : false,
					data : {
						"id_request" : id_request
					},
					success : function(msg) {
						if (!msg.result) {
							showAlert(1,msg.error);
							table.ajax.reload();
						} else {
							showAlert(0,msg.content);
							table.ajax.reload();
						}
					},
					error : function(msg) {
						showAlert(1,"Si è verificato un errore.");
						table.ajax.reload();
					}
				});
			} else if (action === "reject"){ // rifiuta richiesta
				$.ajax({
					url : absolutePath + "/rejectRequest",
					type : "POST",
					dataType : 'JSON',
					async : false,
					data : {
						"id_request" : id_request
					},
					success : function(msg) {
						if (!msg.result) {
							showAlert(1,msg.error);
							table.ajax.reload();
						} else {
							showAlert(0,msg.content);
							table.ajax.reload();
						}
					},
					error : function(msg) {
						showAlert(1,"Si è verificato un errore.");
						table.ajax.reload();
					}
				});
			} else if (action === "upload"){ // carica allegato
				var url_redirect = absolutePath + "/_areaAdmin/uploadAttachedAdmin_uvp.jsp?id_request=" + id_request;
				$(window.location).attr('href', url_redirect);
			} else if (action === "download"){ // scarica allegato
				var url_download = absolutePath + "/Downloader?idRequest=" + id_request;
				$(window.location).attr('href', url_download);
			}
		}
	});
});
