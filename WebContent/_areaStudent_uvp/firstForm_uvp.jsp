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
									<a href="#" id="intBtn"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Tirocinio Interno</a>
									<p></p>
									<a href="#" id="extBtn"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Tirocinio Esterno</a>
									<p></p>
								</div>
							</div>
							<div class="news-block-seven" id="internshipTableDiv">
								<form id="choiceForm" action="#" method="post">
									<table id="internshipTable"
										class="display data-results table-striped table-hover table-bordered">
										<thead>

										</thead>
										<tbody id="bodyInternshipTable">

										</tbody>
									</table>
									<input type="submit" id="submit1" class="btn btn-success"
										value="CONTINUA">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>

	<div id="details" class="modal">
		<div id="details-content" class="modal-content animate">
			<h1 class="popup-h1">DETTAGLI</h1>

		</div>
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />
	<script
		src="<%=request.getContextPath()%>/js/pages/scripts_internship.js"></script>

</body>
</html>
