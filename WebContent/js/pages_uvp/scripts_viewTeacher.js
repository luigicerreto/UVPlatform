$(document).ready(function() {
	// contenuto tabella
	var table = $('#AdminUserTable').DataTable( {
		"order": [[ 1, "asc" ]],
		"lengthMenu": [[10, -1], [10, "Tutti"]],
		"autoWidth": false,
		"bAutoWidth": false,
		"processing": true,
		"ajax": {
			"url": absolutePath + "/showTeachers",
			"dataSrc": "data",
			"type": "POST"
		},
		"columns" : [
			{ "data" : "email" },
			{ "data" : "name" },
			{ "data" : "surname" },
			{ "data" : "sex" },
			{ "data" : "office" },
			{ "data" : "phone" },
			{ "data" : "action" },
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

	// azioni sull'utente
	$(document).on('click', 'div.edit-info i', function(e){
		e.stopPropagation();
		e.preventDefault();
		
		var div = $(this).parent();
		var input = $(div).children("input[type='text']");
		

		if($(this).hasClass('fa-check')) {
			$.ajax({
				url : absolutePath + "/editUser",
				type : "POST",
				dataType : 'JSON',
				async : false,
				data : {
					"value" : $(input).val(),
					"field" : $(input).data('field'),
					"email" : $(div).attr('id')
				},
				success : function(msg) {
					if (!msg.result) {
						showAlert(1,msg.error);
						table.ajax.reload();
					} else {
						$(input).attr('disabled', true);
						showAlert(0,msg.content);
						table.ajax.reload();
					}
				},
				error : function(msg) {
					showAlert(1,"Impossibile effettuare le modifiche");
					table.ajax.reload();
				}
			});
		}
		else if ($(this).hasClass('fa-edit')){
			$(input).attr('disabled', false);
		}
		
		$(this).toggleClass("fa-edit fa-check");
	});
	
	$(document).on('click', 'label.actionInternship', function(e){
		e.stopPropagation();
		e.preventDefault();
		
		var action = $(this).children("input").data("action");
		var email = $(this).children("input").attr("id");
		
		if (action === "remove"){
			$.ajax({
				url : absolutePath + "/removeUser",
				type : "POST",
				dataType : 'JSON',
				async : false,
				data : {
					"email" : email
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
		}
	});
});