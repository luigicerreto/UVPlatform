//funzione che permette di ottenere i dati dell'utente
function loadData(){
	$.ajax({
		url : absolutePath + "/showProfile",
		type : "POST",
		dataType : 'JSON',
		async : false,
		data : {
		},
		success : function(msg) {
			// ripulisci i campi di testo
			$("#currentpwd").val('');
			$("#newpwd").val('');
			$("#verifypwd").val('');
			
			if (!msg.result) {
				showAlert(1, msg.error);
			} else {
				$("#name").val(msg.name);
				$("#surname").val(msg.surname);
				$("#email").val(msg.email)
				$("#phone").val(msg.phone);
			}
		},
		error : function(msg) {
			showAlert(1, "Impossibile Recuperare i dati");
		}
	});
};

$(document).ready(function() {
	// carica i dati dell'utente
	loadData();

	// modifica numero telefono
	$(document).on('click', 'div.edit-info i', function(e){
		e.stopPropagation();
		e.preventDefault();

		var email = $(this).parent().parent().find("input[type='email']#email").val();
		var input = $(this).parent().children("input[type='text']");

		if($(this).hasClass('fa-check')) {
			$.ajax({
				url : absolutePath + "/editUser",
				type : "POST",
				dataType : 'JSON',
				async : false,
				data : {
					"value" : $(input).val(),
					"field" : "phone",
					"email" : email
				},
				success : function(msg) {
					if (!msg.result) {
						showAlert(1,msg.error);
					} else {
						$(input).attr('disabled', true);
						showAlert(0,msg.content);
					}
					loadData();
				},
				error : function(msg) {
					showAlert(1,"Impossibile effettuare le modifiche");
					loadData();
				}
			});
		}
		else if ($(this).hasClass('fa-edit')){
			$(input).attr('disabled', false);
		}

		$(this).toggleClass("fa-edit fa-check");
	});

	// modifica password
	$(document).on('click', "button[type='submit']", function(e){
		e.stopPropagation();
		e.preventDefault();

		var email = $(this).parent().parent().parent().find("input[type='email']#email").val();
		var current_pwd = $("#currentpwd").val();
		var new_pwd = $("#newpwd").val();
		var verify_pwd = $("#verifypwd").val();

		if (current_pwd != undefined
				&& new_pwd != undefined
				&& verify_pwd != undefined) {
			if (new_pwd != verify_pwd) {
				showAlert(1,"Controlla che le due password coincidano");
			} else {
				$.ajax({
					url : absolutePath + "/editUser",
					type : "POST",
					dataType : 'JSON',
					async : false,
					data : {
						"current_pwd" : current_pwd,
						"value" : new_pwd,
						"field" : "password",
						"email" : email
					},
					success : function(msg) {
						if (!msg.result) {
							showAlert(1,msg.error);
						} else {
							showAlert(0,msg.content);
						}
						loadData();
					},
					error : function(msg) {
						showAlert(1,"Impossibile effettuare le modifiche");
						loadData();
					}
				});
			}
		} else {
			showAlert(1,"Controlla di aver compilato tutti i campi");
			loadData();
		}
	});
});