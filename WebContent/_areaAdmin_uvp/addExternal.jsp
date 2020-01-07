<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>

<%
	String pageName = "addExternal.jsp";
	String pageFolder = "_areaAdmin_uvp";
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
								<form id="addInternship" data-internship="1">
									<div
										class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
										<div class="panel">
											<h2 class="text-center">Dati azienda</h2>
										</div>

										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="text" class="form-control" name="name"
												placeholder="Nome" maxlength="20" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="text" class="form-control" name="office"
												placeholder="Sede" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input readonly="readonly"
												onfocus="this.removeAttribute('readonly');" type="email"
												class="form-control" name="email" placeholder="Email" required>
										</div>
										<div class="clearfix"></div>
										<div class="form-group">
											<div class="panel">
												<h2 class="text-center">Dati tirocinio esterno</h2>
											</div>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="number" class="form-control" name="availability"
												placeholder="Disponibilità" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="number" class="form-control" name="duration"
												placeholder="Durata" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="text" onfocus="(this.type='date')"
												class="form-control" name="date" placeholder="Data" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<textarea class="form-control" maxlength="180" name="info"
												placeholder="Info" required></textarea>
										</div>
										<div
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button type="submit" class="btn btn-primary btn-submit">Registra</button>
										</div>
										<div class="clearfix"></div>
									</div>
								</form>
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
		src="<%=request.getContextPath()%>/js/pages_uvp/scripts_addInternship.js"></script>
</body>
</html>
