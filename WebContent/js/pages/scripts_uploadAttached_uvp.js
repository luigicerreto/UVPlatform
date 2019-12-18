$(document).ready(function() 
		{
	$(document).on('click','#aggiungiAllegati',
			function(e) 
			{
		var filenames = [];
		$(".dz-filename").each(	function(index, element) 
				{
			filenames.push($(this).text());
				});

		if (filenames.length > 0) 
		{
			$(".preloader").show();
			$.ajax(
					{
						url : absolutePath + "/addAttached",
						type : "POST",
						dataType : 'JSON',
						async : false,
						data : 
						{
							"filenames" : filenames,
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
										2000);
							}
						},
						error : function(msg) 
						{
							showAlert(1,"Impossibile Recuperare i dati.");
						}
					});

			$(".preloader").hide();
		} 
		else 
		{
			
			showAlert(1,"Controllare di aver inserito tutti gli allegati richiesti.");
		}

		return false;
			});

		});