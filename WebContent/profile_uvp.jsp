<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>

<%
	String pageName = "profile_uvp.jsp";
	String pageFolder = "";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
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
										<h2 class="text-center">Il mio profilo</h2>
										<p></p>
									</div>
									<form id="signUp">
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12 disabled">
											<input type="text" class="form-control" id="name"
												minlength="1" maxlength="20" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="text" class="form-control" id="surname"
												 minlength="1" maxlength="20" value="david" required >
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="email" class="form-control" id="email"
												 minlength="1" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="text" class="form-control" id="serial"
												 minlength="10" maxlength="10" required>
										</div>
										
										<div id="phoneNumber" class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="text" class="form-control" id="phone"
												placeholder="Telefono" minlength="10" maxlength="10" required></input>
												<i class="fa fa-edit"></i>
											
										</div>
										
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="password" class="form-control" id="vecchiaPassword"
												placeholder="Vecchia password" minlength="8" required>
										</div>
										
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="password" class="form-control" id="nuovaPassword"
												placeholder="Nuova password" minlength="8" required>
												</div>
												
												<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="password" class="form-control" id="confermaPassword"
												placeholder="Conferma password" minlength="8" required>
												</div>

										<div
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button type="submit" class="btn btn-primary btn-submit">Conferma</button>
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
