<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession, model.SystemAttribute, java.text.SimpleDateFormat, java.time.*, controller.DbConnection, java.sql.Connection, java.sql.ResultSet, java.sql.Statement"%>

<%
	String pageName = "firstForm_uvp.jsp";
	String pageFolder = "_areaStudent_uvp";
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

<body onLoad="">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!-- <div class="preloader"></div>  -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>


		<div class="sidebar-page-container basePage indexPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven" id="internshipChoice">
								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 index-container">
									<div class="panel">
										<h2 class="text-center">Scegli Tirocinio</h2>
										<p></p>
									</div>
									<a href="#" id="internoBtn"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Tirocinio Interno</a>
									<p></p>
									<a href="#" id="esternoBtn"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Tirocinio Esterno</a>
									<p></p>
								</div>
							</div>
							<div class="news-block-seven" id="intInternshipTable">
								<table id="internshipTable"
									class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center" align="center">ID Docente</th>
											<th class="text-center" align="center">Nome</th>
											<th class="text-center" align="center">Sede</th>
											<th class="text-center" align="center">Scelta</th>
										</tr>
									</thead>
									<tbody id="bodyInternshipTable">
									</tbody>
								</table>
							</div>

							<div class="news-block-seven" id="extInternshipTable">
								<table id="internshipTable"
									class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center" align="center">ID Azienda</th>
											<th class="text-center" align="center">Azienda</th>
											<th class="text-center" align="center">Sede</th>
											<th class="text-center" align="center">Data Inizio</th>
											<th class="text-center" align="center">Scelta</th>

										</tr>
									</thead>
									<tbody id="bodyInternshipTable">
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
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />
	<script>
		jQuery(document)
				.ready(
						function($) {
							$('#internshipTable')
									.DataTable(
											{
												"order" : [ [ 0, "desc" ] ],
												"lengthMenu" : [ [ 10, -1 ],
														[ 10, "Tutti" ] ],
												"autoWidth" : false,
												"bAutoWidth" : false,
												"language" : {
													"sEmptyTable" : "Nessuna Richiesta Presente",
													"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
													"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
													"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
													"sInfoPostFix" : "",
													"sInfoThousands" : ".",
													"sLengthMenu" : "Visualizza _MENU_ elementi",
													"sLoadingRecords" : "Caricamento...",
													"sProcessing" : "Elaborazione...",
													"sSearch" : "Cerca:",
													"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
													"oPaginate" : {
														"sFirst" : "Inizio",
														"sPrevious" : '<i class="fa fa-caret-left"></i>',
														"sNext" : '<i class="fa fa-caret-right"></i>',
														"sLast" : "Fine"
													},
													"oAria" : {
														"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
														"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
													}
												}
											});
						});
	</script>
	<script
		src="<%=request.getContextPath()%>/js/pages/scripts_internship.js"></script>

</body>
</html>
