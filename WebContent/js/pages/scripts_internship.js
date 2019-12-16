$(document).ready(function() {
	$("#internshipTableDiv").css("display", "none");

	$('.btn').click(function(){
		// nasconde i tasti scelta
		$("div#internshipChoice").css("display", "none");
		$("#internshipTableDiv").css("display", "block");

		
		var btnId = $(this).attr('id');
		var flag = (btnId === "internoBtn") ? 0 : 1;

		// mostra tabella in base alla scelta
		if(btnId === "internoBtn"){
			$("#internshipTable").children("thead").append(
					"<tr align=\"center\">" +
					"<th class=\"text-center\" align=\"center\">ID Docente</th>" +
					"<th class=\"text-center\" align=\"center\">Nome</th>" +
					"<th class=\"text-center\" align=\"center\">Sede</th>" +
					"<th class=\"text-center\" align=\"center\">Scelta</th>" +
					"</tr>"
			);
		} else if (btnId === "esternoBtn"){
			$("#internshipTable").children("thead").append(
					"<tr align=\"center\">" +
					"<th class=\"text-center\" align=\"center\">ID Azienda</th>" +
					"<th class=\"text-center\" align=\"center\">Azienda</th>" +
					"<th class=\"text-center\" align=\"center\">Sede</th>" +
					"<th class=\"text-center\" align=\"center\">Data Inizio</th>" +
					"<th class=\"text-center\" align=\"center\">Scelta</th>" +
					"</tr>"
			);
		}
		
		$(".preloader").show();
		
		// carica i dati dal db
		$.ajax({
			url : absolutePath + "/ServletInternship",
			type : "POST",
			dataType : 'JSON',
			async : false,
			data : {
				"type" : flag
			},
			success : function(msg) {
				if (!msg.result) {
					showAlert(1, msg.error);
				} else {
					$('#bodyInternshipTable').html(msg.content);
				}
			},
			error : function(msg) {
				showAlert(1, "Impossibile Recuperare i dati.");
			}
		});
		
		$(".preloader").hide();

		$('#internshipTable').DataTable( {
	        "order": [[ 0, "desc" ]],
	        "lengthMenu": [[10, -1], [10, "Tutti"]],
	        "autoWidth": false,
	        "bAutoWidth": false,			        
	        "language": {
				    "sEmptyTable":     "Nessun tirocinio disponibile",
				    "sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
				    "sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
				    "sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
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
				    },
				    "oAria": {
				        "sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
				        "sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
				    }
	        }        
	    } );
	});
});