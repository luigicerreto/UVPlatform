<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>

<%
	String pageName = "viewTeacher.jsp";
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

		<div class="sidebar-page-container basePage viewRequestAdmin">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content ">
							<div class="news-block-seven">
								<table id="AdminUserTable"
									class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center">E-Mail</th>
											<th class="text-center">Nome</th>
											<th class="text-center">Cognome</th>
											<th class="text-center">Sesso</th>
											<th width="20%" class="text-center">Ufficio</th>
											<th class="text-center">Telefono</th>
											<th class="text-center">Azioni</th>
										</tr>
									</thead>
									<tbody id="bodyAdminUserTable">

									</tbody>
								</table>
								<button onclick="window.location.href = 'addInternal.jsp'"
									type="button" class="btn btn-default" style="background: green"
									aria-label="Left Align">
									<span class="glyphicon glyphicon-plus" style="color: white"
										aria-hidden="true"></span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>
	
	<div id="details" class="modal fade" role="dialog">
		<div id="details-content" class="modal-content modal-dialog">
			<div class="header">
				<h1 class="details"></h1>
				<button type="button" class="close" data-dismiss="modal">
					<i class="fa fa-times-circle"></i>
				</button>
			</div>
			<div class="info"></div>
		</div>
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />

	<script
		src="<%=request.getContextPath()%>/js/pages_uvp/scripts_viewTeacher.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/pages_uvp/scripts_showInfo.js"></script>
</body>
</html>