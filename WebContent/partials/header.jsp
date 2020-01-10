<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession , controller.Utils , interfacce.UserInterface "%>

<%
	String pageName = request.getParameter("pageName");
	String pageFolder = request.getParameter("pageFolder");
	String menu = "";
	String hiddenMenu = "";
	String logoRedirect = request.getContextPath() + "/choice.jsp"; //tiene traccia del path a cui reindirizzare il sito quando si preme sul logo
	UserInterface user = (UserInterface) session.getAttribute("user");

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if (!ck.isAllowed()) { //cliccando sul logo reinderizza a index se non si è loggati
		logoRedirect = request.getContextPath() + ck.getUrlRedirect();
	}

	if (pageFolder.equals("_areaAdmin") && ck.isAllowed()) { //se stiamo in una pagina dell'area admin
		logoRedirect = request.getContextPath() + "/_areaAdmin/viewRequest.jsp";

		if (pageName.equals("viewRequest.jsp") && ck.isAllowed()) {
			menu += "<li class=\"current\"><a>Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "_uvp" + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "_uvp" + "/viewUser.jsp\">Utenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "_uvp" + "/viewTeacher.jsp\">Docenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "_uvp" + "/viewCompany.jsp\">Aziende</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
	} else if (pageFolder.equals("_areaAdmin_uvp") && ck.isAllowed()) { //se stiamo in una pagina dell'area admin
			logoRedirect = request.getContextPath() + "/_areaAdmin/viewRequest.jsp";
	
		if (pageName.equals("viewRequestInternship.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + "_areaAdmin" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li class=\"current\"><a>Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewUser.jsp\">Utenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewTeacher.jsp\">Docenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewCompany.jsp\">Aziende</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("viewUser.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + "_areaAdmin" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li class=\"current\"><a>Utenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewTeacher.jsp\">Docenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewCompany.jsp\">Aziende</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("viewTeacher.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + "_areaAdmin" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewUser.jsp\">Utenti</a></li>";
			menu += "<li class=\"current\"><a>Docenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewCompany.jsp\">Aziende</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("viewCompany.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + "_areaAdmin" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewUser.jsp\">Utenti</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewTeacher.jsp\">Docenti</a></li>";
			menu += "<li class=\"current\"><a>Aziende</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("uploadAttached.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + "_areaAdmin" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li class=\"current\"><a>Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
	} else if (pageFolder.equals("_areaSecretary") && ck.isAllowed()) { //se stiamo in una pagina dell'area segreteria
		logoRedirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";

		if (pageName.equals("viewRequest.jsp") && ck.isAllowed()) { //pagina delle richieste
			menu += "<li class=\"current\"><a>Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "_uvp" +"/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}
	} else if (pageFolder.equals("_areaSecretary_uvp") && ck.isAllowed()) { //se stiamo in una pagina dell'area segreteria
		logoRedirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
	
		if (pageName.equals("viewRequestInternship.jsp") && ck.isAllowed()) { //se stiamo in viewRequestSecretary
			menu += "<li><a href=\"" + request.getContextPath() + "/_areaSecretary" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li class=\"current\"><a>Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}

		if (pageName.equals("uploadAttached.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/_areaSecretary" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "_uvp" + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li class=\"current\"><a>Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}
	}

	else if (pageFolder.equals("_areaStudent") && ck.isAllowed()) { //se stiamo in una pagina dell'area studente

		if (pageName.equals("viewRequest.jsp") && ck.isAllowed()) { //se stiamo in viewRequest
			menu += "<li class=\"current\"><a>Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "_uvp" + "/viewRequestInternship.jsp\">Richieste tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";

		}
		if (pageName.equals("firstForm.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li class=\"current\"><a>Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}

		if (pageName.equals("uploadAttached.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li class=\"current\"><a>Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}
		
		if (pageName.equals("uploadAttached.jsp") && ck.isAllowed()) {
			menu += "<li class=\"current\"><a>Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}

	} else if (pageFolder.equals("_areaStudent_uvp") && ck.isAllowed()) { //se stiamo in una pagina dell'area studente tirocinio

		if (pageName.equals("viewRequestInternship.jsp") && ck.isAllowed()) { //se stiamo in viewRequestTirocinio
			menu += "<li><a href=\"" + request.getContextPath() + "/_areaStudent" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li class=\"current\"><a>Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/internshipRequest.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}
		if (pageName.equals("internshipRequest.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/_areaStudent" + "/viewRequest.jsp\">Richieste EV</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li class=\"current\"><a>Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}
		if (pageName.equals("uploadAttached.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li class=\"current\"><a>Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}
	}

	else if (pageFolder.equals("_areaCompany_uvp") && ck.isAllowed()) { //se stiamo in una pagina dell'area Azienda
		logoRedirect = request.getContextPath() + "/_areaCompany_uvp/viewRequestInternship.jsp";
		
		if (pageName.equals("viewRequestInternship.jsp") && ck.isAllowed()) { //se stiamo in viewRequestCompany
			menu += "<li class=\"current\"><a>Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}

		if (pageName.equals("uploadAttached.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}

	} else if (pageFolder.equals("_areaTeacher_uvp") && ck.isAllowed()) { //se stiamo in una pagina dell'area Docente
		logoRedirect = request.getContextPath() + "/_areaTeacher_uvp/viewRequestInternship.jsp";

		if (pageName.equals("viewRequestInternship.jsp") && ck.isAllowed()) { //se stiamo in viewRequestTeacher
			menu += "<li class=\"current\"><a>Richieste Tirocinio</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}

		if (pageName.equals("uploadAttached.jsp") && ck.isAllowed()) {
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder + "/viewRequestInternship.jsp\">Richieste Tirocinio</a></li>";
			menu += "<li class=\"current\"><a>Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
			menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
					+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
		}

	} else if (pageFolder.equals("") && ck.isAllowed()) { //se non siamo (o siamo) loggati
		if (pageName.equals("login.jsp")) { //se ci troviamo in login.jsp
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/login.jsp\">Login</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/index.jsp\">Benvenuto</a></li>";
		} else if (pageName.equals("index.jsp")) { //se ci troviamo in index.jsp
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/index.jsp\">Benvenuto</a></li>";
		} else if (pageName.equals("choice.jsp")) { //se ci troviamo in scelta.jsp
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/logout.jsp\">Disconnetti</a></li>";
		} else if (pageName.equals("recuperaPassword.jsp")) {
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() //se ci troviamo in recuperaPassword.jsp
					+ "/recuperaPassword.jsp\">Recupera password</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/index.jsp\">Benvenuto</a></li>";
		} else if (pageName.equals("profile_uvp.jsp") && ck.isAllowed()) {
			if (user.getUserType() == 4) {
				logoRedirect = request.getContextPath() + "/_areaCompany_uvp/viewRequestCompany.jsp";
				
				menu = "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaCompany_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";

			} else if (user.getUserType() == 3) {
				logoRedirect = request.getContextPath() + "/_areaTeacher_uvp/viewRequestInternship.jsp";
				
				menu = "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaTeacher_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
			} else if (user.getUserType() == 1) {
				logoRedirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
				
				menu = "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaSecretary/viewRequest.jsp\">Richiesta EV</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaSecretary_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
			} else {
				menu = "<li><a href=\"" + request.getContextPath()
						+ "/_areaStudent/viewRequest.jsp\">Richiesta EV</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaStudent_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
			}

		} else if (pageName.equals("contact_uvp.jsp") && ck.isAllowed()) {

			if (user.getUserType() == 4) {
				logoRedirect = request.getContextPath() + "/_areaCompany_uvp/viewRequestInternship.jsp";
				
				menu = "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaCompany_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";

			} else if (user.getUserType() == 3) {
				logoRedirect = request.getContextPath() + "/_areaTeacher_uvp/viewRequestInternship.jsp";
				
				menu = "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaTeacher_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
			} else if (user.getUserType() == 1) {
				logoRedirect = request.getContextPath() + "/_areaSecretary_uvp/viewRequestInternship.jsp";
				
				menu = "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaSecretary/viewRequest.jsp\">Richiesta EV</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaSecretary_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
			} else {
				menu = "<li><a href=\"" + request.getContextPath()
						+ "/_areaStudent/viewRequest.jsp\">Richiesta EV</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "_areaStudent_uvp/viewRequestInternship.jsp\">Richiesta Tirocinio</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/profile_uvp.jsp\"><i class=\"fa fa-user\"></i>" + user.getName() + "</a></li>";
				menu += "<li style=\"float:right\"><a href=\"" + request.getContextPath()
						+ "/contact_uvp.jsp\"><i class=\"fa fa-envelope\"></i>Contattaci</a></li>";
			}
		}
	}

	hiddenMenu = menu;
%>
<!-- Modal -->
<div id="defaultModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<form action="#">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</form>

	</div>
</div>
<!-- Main Header -->
<header class="main-header">
	<!--Header-Upper-->
	<div class="header-upper">
		<div class="auto-container">
			<div class="clearfix">

				<div class="logo-outer">
					<div class="logo">
						<a href="<%=logoRedirect%>"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--End Header Upper-->

	<!--Header Lower-->
	<div class="header-lower">
		<div class="auto-container">
			<div class="nav-outer clearfix">

				<!-- Main Menu -->
				<nav class="main-menu">
					<div class="navbar-collapse collapse clearfix"
						id="bs-example-navbar-collapse-1">
						<ul class="navigation clearfix">
							<%=menu%>
						</ul>
					</div>
				</nav>


				<!-- Hidden Nav Toggler -->
				<div class="nav-toggler">
					<button class="hidden-bar-opener">
						<span class="icon qb-menu1"></span>
					</button>
				</div>

			</div>
		</div>
	</div>
	<!--End Header Lower-->

</header>
<!--End Header Style Two -->

<!-- Hidden Navigation Bar -->
<section class="hidden-bar left-align">

	<div class="hidden-bar-closer">
		<button>
			<span class="qb-close-button"></span>
		</button>
	</div>

	<!-- Hidden Bar Wrapper -->
	<div class="hidden-bar-wrapper">
		<div class="logo">
			<a href="#"></a>
		</div>
		<!-- .Side-menu -->
		<div class="side-menu">
			<!--navigation-->
			<ul class="navigation clearfix">
				<%=hiddenMenu%>
			</ul>
		</div>
		<!-- /.Side-menu -->

	</div>
	<!-- / Hidden Bar Wrapper -->


</section>
<!-- End / Hidden Bar -->
