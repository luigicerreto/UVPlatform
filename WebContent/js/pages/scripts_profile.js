$(document).ready(
		function() {
		$('#name,#surname,#email,#serial,#phone').prop("disabled", true);
	
		$(document).on('submit','#signUp',function(e) {
				var name = $("#name").val();
				var surname = $("#surname").val();
				var email = $("#email").val();
				var serial = $('#serial').val();
				var password = $("#password").val();
				var verifyPassword = $("#verifyPassword").val();

				if (name != undefined
						&& surname != undefined
						&& email != undefined
						&& serial != undefined
						&& sex != undefined
						&& password != undefined
						&& verifyPassword != undefined) {
					if (password != verifyPassword) {
						showAlert(1,"Controllare che le due password coincidano");
					} else {
						$(".preloader").show();

						$
						.ajax({
							url : absolutePath
							+ "/ServletSignup",
							type : "POST",
							dataType : 'JSON',
							async : false,
							data : {
								"name" : name,
								"surname" : surname,
								"email" : email,
								"serial" : serial,
								"sex" : sex,
								"password" : password
							},
							success : function(
									msg) {
								if (!msg.result) {
									showAlert(1,msg.error);
								} else {
									showAlert(0,msg.content);

									setTimeout(function() {
										window.location.href = msg.redirect;
									},1000);
								}
							},
							error : function(msg) {
								showAlert(1,"Impossibile Recuperare i dati.");
							}
						});

						$(".preloader").hide();
					}
				} else {
					showAlert(1,"Errore prelevamento campi.");
				}
				return false;
			});
		});
