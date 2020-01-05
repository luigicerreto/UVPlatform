<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession"%>

<%
	String pageName = "uploadAttached_uvp.jsp";
	String pageFolder = "_areaStudent_uvp";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if (!ck.isAllowed()) {
		response.sendRedirect(request.getContextPath() + ck.getUrlRedirect());
	}

	Integer id_request = Integer.parseInt(request.getParameter("id_request"));
	Boolean new_request = Boolean.parseBoolean(request.getParameter("new_request"));
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

		<div class="sidebar-page-container basePage uploadAttachedPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<%
									if (new_request) {
								%>
								<div class="form-group">
									<button type="button"
										class="btn btn-primary btn-submit generatePDF"
										onclick="createPdf()">Genera PDF</button>
								</div>
								<%
									}
								%>
								<h2>
									Richiesta N.<%=id_request%>
								</h2>
								<h2>Trascina o premi sull'apposito riquadro per caricare un
									file</h2>
								<div action='<%=request.getContextPath() + "/Uploader?id_request=" + id_request%>'
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
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script>
	<script src="<%=request.getContextPath()%>/js/filesystem_dropzone.js"></script>
	<script src="<%=request.getContextPath()%>/js/pages/scripts_uploadAttached_uvp.js"></script>
</body>
</html>
