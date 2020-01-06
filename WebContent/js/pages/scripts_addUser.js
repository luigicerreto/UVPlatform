$(document).ready(function() {
	$(document).on('submit','#addTeacher',function(e) {
		var name = $("#name").val();
		var surname = $("#surname").val();
		var email = $("#email").val();
		var office = $("#office").val();
		var sex = $(".sex:checked").val();
		var password = $("#password").val();

		if (name != undefined
				&& surname != undefined
				&& email != undefined
				&& office != undefined
				&& sex != undefined
				&& password != undefined) {
			$.ajax({
				url : absolutePath + "/addUser",
				type : "POST",
				dataType : 'JSON',
				async : false,
				data : {
					"name" : name,
					"surname" : surname,
					"email" : email,
					"office" : office,
					"sex" : sex,
					"password" : password
				},
				success : function(
						msg) {
					if (!msg.result) {
						showAlert(1,msg.error);
					} else {
						showAlert(0,msg.content);
					}
				},
				error : function(msg) {
					showAlert(1,"Impossibile eseguire l'operazione");
				}
			});
		} else {
			showAlert(1,"Controllare di aver compilato tutti i campi");
		}
	});
});
