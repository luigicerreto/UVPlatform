<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page import="java.util.*,model.Request"%>
<%
	String pageName = "viewRequestCompany.jsp";
	String pageFolder = "_areaCompany_uvp";
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

<body onLoad="showData()">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage viewRequestStudent">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<table id="CompanyTableInternship" class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center" align="center">ID</th>
											<th class="text-center" align="center">Allegati</th>
											<th class="text-center" align="center">Tema</th>
											<th class="text-center" align="center">Nome</th>
											<th class="text-center" align="center">Cognome</th>
											<th class="text-center" align="center">Tipo di richiesta</th>
											<th class="text-center" align="center">Stato</th>
											<th class="text-center" align="center">Azioni</th>
										</tr>
									</thead>
									<tbody id="bodyCompanyTableInternship">
									</tbody>
								</table>

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
				<h1 class="details">DETTAGLI RICHIESTA</h1>
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
		src="<%= request.getContextPath() %>/js/pages/scripts_viewRequestCompany.js"></script>

</body>
</html>
