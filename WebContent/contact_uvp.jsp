<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>

<%
	String pageName = "contact_uvp.jsp";
	String pageFolder = "";
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body onLoad="showDataUser()">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!-- <div class="preloader"></div>  -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage signUpPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
									<div class="panel">
										<h2 class="text-center">Contattaci</h2>
										<p></p>
									</div>
									<form id="signUp">
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12 ">
											<input type="text" class="form-control" id="oggetto" placeholder="Oggetto"
												 maxlength="30" required>
										</div>
										<div class="form-group col-sm-12">
											<input type="text" class="form-control" id="testo" placeholder="Testo"
												  maxlength="180" required>
												 </div>
										
										<div
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button type="submit" class="btn btn-primary btn-submit">Invia</button>
										</div>

										<div class="clearfix"></div>
									</form>
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
	<script
		src="<%= request.getContextPath() %>/js/pages/scripts_profile.js"></script>
	<script 
	src="<%= request.getContextPath()%>/js/pages/showUserDate.js"></script>

</body>
</html>
