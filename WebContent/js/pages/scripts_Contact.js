$(document).ready(function() 
		{
	$(document).on('submit','#Contact',function() 
			{
			var object = $("#oggetto").val();
			var text = $("#textareaContact").val();
		$.ajax(
				{
					url : absolutePath + "/Contact",
					type : "POST",
					dataType : 'JSON',
					async : false,
					data : 
					{
					"object" : object,
					"text" : text,
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
		$(".preloader").hide();
		return false;
			});
		});