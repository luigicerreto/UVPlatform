$(document).ready(function() {
	$("#internshipTableDiv").css("display", "none");
	/*
	$('label').click(function () {
		  $(this).removeClass('active')
		  .end().find('[type="radio"]').prop('checked', false);
		});
	*/
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
					"<th class=\"text-center\" align=\"center\">Sede</th>" +
					"<th class=\"text-center\" align=\"center\">Scelta</th>" +
					"</tr>"
			);
			 
			$('#internshipTable tbody').on('click', '.prova1' ,function(){
			    var currow = $(this).closest('tr');
			    var col1 = $(this).closest('tr').children('td:eq(0)').text();
			    var col2 = $(this).closest('tr').children('td:eq(1)').text();
			    var col3 = $(this).closest('tr').children('td:eq(2)').text();
			    var result= col1 + '\n' + col2 + '\n' + col3;
			    alert(result);
			    
			    
			    $.ajax(
						{
							url : absolutePath + "/",
							type : "POST",
							dataType : 'JSON',
							async : false,
							data : 
							{
								"email" : email,
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
											500);
								}
							},
							error : function(msg) 
							{
								showAlert(1,"Impossibile inviare l'email.");
							}
						});
			   });
			
			
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
			
			$('#internshipTable tbody').on('click', '.prova1' ,function(){
			    var currow = $(this).closest('tr');
			    var col1 = $(this).closest('tr').children('td:eq(0)').text();
			    var col2 = $(this).closest('tr').children('td:eq(1)').text();
			    var col3 = $(this).closest('tr').children('td:eq(2)').text();
			    var col4 = $(this).closest('tr').children('td:eq(3)').text();
			    var result= col1 + '\n' + col2 + '\n' + col3 + col4;
			    alert(result);
			    $.ajax(
						{
							url : absolutePath + "/",
							type : "POST",
							dataType : 'JSON',
							async : false,
							data : 
							{
								"email" : email,
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
											500);
								}
							},
							error : function(msg) 
							{
								showAlert(1,"Impossibile inviare l'email.");
							}
						});
			    
			    
			    
			   });
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
	
	
});