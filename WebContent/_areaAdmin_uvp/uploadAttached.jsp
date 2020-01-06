<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession"%>

<%
	String pageName = "uploadAttached.jsp";
	String pageFolder = "_areaAdmin_uvp";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if (!ck.isAllowed()) {
		response.sendRedirect(request.getContextPath() + ck.getUrlRedirect());
	}
	
	Integer id_request = Integer.parseInt(request.getParameter("id_request"));
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body>
	<div class="page-wrapper">
	
		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

		<div class="sidebar-page-container basePage uploadAttachedPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<div class="form-group"></div>

								<h2>
									Richiesta N.<%= id_request %>
								</h2>
								<h2>Trascina o premi sull'apposito riquadro per caricare un
									file</h2>
								<div action='<%= request.getContextPath() + "/Uploader" %>'
									class='dropzoneUploader'></div>

								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-submit"
										id='aggiungiAllegati'>Concludi</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />
	<script type="text/javascript">
	$(document).ready(function() {
	$(document).on('click','#aggiungiAllegati', function(e){
		var filenames = [];
		var id_request = new URLSearchParams(window.location.search).get('id_request');

		$(".dz-filename").each(	function(index, element){
			filenames.push($(this).text());
		});

		if (filenames.length > 0){
			$.ajax({
				url : absolutePath + "/updateAttached",
				type : "POST",
				dataType : 'JSON',
				async : false,
				data : 
				{
					"filenames" : filenames,
					"id_request" : id_request
				},
				success : function(msg){
					if (!msg.result){
						showAlert(1,msg.error);
					} 
					else {
						showAlert(0,msg.content);
						setTimeout(function(){
							window.location.href = absolutePath + "/_areaAdmin/viewRequestInternship.jsp";
						},1000);
					}
				},
				error : function(msg){
					showAlert(1,"Impossibile Recuperare i dati.");
				}
			});
		} 
		else {
			showAlert(1,"Controllare di aver inserito tutti gli allegati richiesti.");
		}
	});
	});
	</script>
	<script src="<%= request.getContextPath() %>/js/filesystem_dropzone.js"></script>
	<script src="<%= request.getContextPath() %>/js/pages_uvp/scripts_uploadAttached.js"></script>
</body>
</html>
