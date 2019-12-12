	$(document)
			.ready(
					function() {

						$(document)
								.on(
										'submit',
										'#recuperaPassword',
										function() {
											
					
											var email = $("#email").val();
											$
															.ajax({
																url : absolutePath
																		+ "/ForgetPassword",
																type : "POST",
																dataType : 'JSON',
																async : false,
																data : {
																	"email" : email,
																	
																},
																
																
																success : function(
																		msg) {
																			
																	if (!msg.result) {
																		showAlert(
																				1,
																				msg.error);
																				alert("flag 1");
																				
																	} else {
																		
																		showAlert(
																				0,
																				msg.content);
																				

																		setTimeout(
																				function() {
																					alert(msg.redirect);
																					window.location.href = msg.redirect;
																				},
																				6000000000000000000000);
																				//TODO
																	}
																},
												
																error : function(
																		msg) {
																	showAlert(1,
																			"Impossibile inviare l'email.");
																}
															});

													$(".preloader").hide();
												
												
												
											});

					});