<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>

<%
	String pageName = "scelta.jsp";
	String pageFolder = "";
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
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage indexPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">

							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 index-container">
								<div class="panel">
									<h2 class="text-center">Fai la tua scelta</h2>
										<p></p>
									</div>
							<div class="news-block-seven">
									<a href="_areaStudent/viewRequest.jsp" class="btn btn-primary btn-lg btn-block"
										role="button" aria-pressed="true">English Validation</a>
									<p></p>
									<a href="_areaStudent_uvp/firstForm_uvp.jsp"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Richiesta Tirocinio</a>
									<p></p>
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
	<script src="<%= request.getContextPath() %>/js/pages/scripts_login.js"></script>

</body>
</html>