<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>

<%
	String pageName = "profile_uvp.jsp";
	String pageFolder = "";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if (!ck.isAllowed()) {
		response.sendRedirect(request.getContextPath() + ck.getUrlRedirect());
	}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body>
	<div class="page-wrapper">

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
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
									<form class="profile" autocomplete="off">
										<div class="left-section">
											<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<input type="text" class="form-control" id="name"
													maxlength="20" disabled>
											</div>
											<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<input type="text" class="form-control" id="surname"
													maxlength="20" disabled>
											</div>
											<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<input type="email" class="form-control" id="email" disabled>
											</div>


											<div
												class="edit-info form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<input type="text" class="form-control" id="phone"
													maxlength='10' disabled><i class='fa fa-phone'></i><i
													class='fa fa-edit'></i>
											</div>
										</div>
										<div class="right-section">

											<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<input type="password" class="form-control"
													id="currentpwd" placeholder="Password attuale"
													required>
											</div>
											<div
												class="grid-right form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<input type="password" class="form-control"
													id="newpwd" placeholder="Nuova password" required>
											</div>
											<div
												class="grid-left form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<input type="password" class="form-control"
													id="verifypwd" placeholder="Conferma password"
													required>
											</div>
											<div
												class="grid-center form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<button type="submit" class="btn btn-primary btn-submit">Conferma</button>
											</div>
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
		src="<%=request.getContextPath()%>/js/pages/scripts_showProfile.js"></script>

</body>
</html>
