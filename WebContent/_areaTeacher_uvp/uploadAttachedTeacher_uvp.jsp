<%@page import="model_uvp.DAORichiesta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession, model.SystemAttribute, controller.Utils, controller.DbConnection,interfacce.*, java.sql.Connection, java.sql.ResultSet, java.sql.Statement, java.text.SimpleDateFormat"%>

<%
	String pageName = "uploadAttachedTeacher.jsp";
	String pageFolder = "_areaTeacher_uvp";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if (!ck.isAllowed()) {
		response.sendRedirect(request.getContextPath() + ck.getUrlRedirect());
	}
	UserInterface currUser = (UserInterface) request.getSession().getAttribute("user");

	Integer id_request = Integer.parseInt(request.getParameter("id_request"));
	Integer requestNumberMaxUpload = 1;
	String requestAllowedExtensionUpload = ".pdf";
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



	<script>
			$( document ).ready(function() {	
				$(".dropzoneUploader").dropzone({
					  maxFiles: <%= requestNumberMaxUpload %>,
					  acceptedFiles: "<%= requestAllowedExtensionUpload %>",
					  accept: function(file, done){
					    done();
					  },
					  init: function() {		
					      this.on("maxfilesexceeded", function(file, errorMessage){
					    	  this.removeFile(file);
					    	  showAlert(1, errorMessage);		    	  
					      });
	                      
					      this.on("error", function(file, errorMessage) {
					    	  this.removeFile(file);
					    	  showAlert(1, errorMessage);
	                      });
	                    
						  this.on("success", function(file, response) {
							  var msg = jQuery.parseJSON(response);
						  	  if(!msg.result){
						  		showAlert(1, msg.error);
						  	  }	            		    
						  	  else{
						  		file.previewElement.querySelector("[data-dz-name]").innerHTML = msg.content;
						  	  }
						  });
					  }		  						
				});					
			});
		</script>
	<script src="<%= request.getContextPath() %>/js/filesystem_dropzone.js"></script>
	<script
		src="<%= request.getContextPath() %>/js/pages/scripts_uploadAttachedTeacher_uvp.js"></script>

</body>
</html>
