package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.Utils;
import model_uvp.DAOInternship;
import model_uvp.DAOUser;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.User;
import util.PasswordGenerator;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/addInternship")
public class addInternship extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addInternship() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer result = 0;
		String error = "";
		String content = "";

		DAOUser daouser = new DAOUser();
		DAOInternship daoint = new DAOInternship();
		User user = null;
		Utils utils = new Utils();
		PasswordGenerator pwdgen = new PasswordGenerator();

		Integer flag = Integer.parseInt(request.getParameter("flag"));

		InternalInternship internal = null;
		ExternalInternship external = null;

		if(flag == 0) { // aggiungi interno
			// dati docente
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String office = request.getParameter("office");
			String pass = pwdgen.generate(8);
			char sex = request.getParameter("sex").charAt(0);

			// dati tirocinio
			String theme = request.getParameter("theme");
			Integer availability = Integer.parseInt(request.getParameter("availability"));
			String resources = request.getParameter("resources");
			String goals = request.getParameter("goals");

			String prefix = email.substring(0, email.indexOf("@"));

			if (name.length() < 2 || name.length() > 20 || name.matches(".*\\d+.*")) {
				error = "Il campo \"Nome\" non rispetta il formato";
			} 
			else if (surname.length() < 2 || surname.length() > 20 || surname.matches(".*\\d+.*")) {
				error = "Il campo \"Cognome\" non rispetta il formato";
			} 
			else if (email.length() == 0 || !email.endsWith("@unisa.it") || prefix.length() < 3) {
				error = "Il campo \"Email\" non rispetta il formato";
			} 
			else if (theme.length() < 2 || theme.length() > 20) {
				error = "Il campo \"Tema\" non rispetta la lunghezza";			
			} 
			else if (availability < 0) {
				error = "Il campo \"Disponibilità\" non rispetta i valori consentiti";
			} 
			else if (resources.length() < 2) {
				error = "Il campo \"Risorse\" non raggiunge la lunghezza minima";
			} 
			else if (goals.length() < 2) {
				error = "Il campo \"Obiettivi\" non raggiunge la lunghezza minima";
			} else {
				user = new User();
				user.setName(name);
				user.setSurname(surname);
				user.setEmail(email);
				user.setSex(sex);
				user.setPassword(utils.generatePwd(pass));
				user.setOffice(office);

				internal = new InternalInternship();
				internal.setTheme(theme);
				internal.setAvailability(availability);
				internal.setResources(resources);
				internal.setGoals(goals);
				internal.setTutorn_name(user.getName().concat(" ").concat(user.getSurname()));
				internal.setFk_tutor(user.getEmail());

				if(daouser.addTeacher(user)) {
					if(daoint.addInternship(internal, flag)) {
						result = 1;
						content = "Docente con tirocinio registrato";
					} else {
						result = 0;
						error = "Errore nella registrazione del tirocinio";
					}
				} else {
					result = 0;
					error = "Errore nella registrazione del docente";
				}
			}
		} 
		else if (flag == 1) { // aggiungi esterno
			// dati azienda
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String office = request.getParameter("office");
			String prefix = email.substring(0, email.indexOf("@"));

			// dati tirocinio
			Integer availability = Integer.parseInt(request.getParameter("availability"));
			Integer duration = Integer.parseInt(request.getParameter("duration"));
			Date date = null;
			String info = request.getParameter("info");

			try {
				date = new SimpleDateFormat("dd/MM/aaaa").parse(request.getParameter("duration"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (name.length() < 2 || name.length() > 20) {
				error = "Il campo \"Nome\" non rispetta il formato";
			} else if (email.length() == 0 || !email.endsWith("@unisa.it") || prefix.length() < 3) {
				error = "Il campo \"Email\" non rispetta il formato";
			} else if (availability < 0) {
				error = "Il campo \"Disponibilità\" non rispetta i valori consentiti";
			} else if (duration < 0) {
				error = "Il campo \"Durata\" non rispetta i valori consentiti";
			} else if (info.length() < 2) {
				error = "Il campo \"Info\" non raggiunge la lunghezza minima";
			} else {
				user = new User();
				user.setName(name);
				user.setSurname(surname);
				user.setEmail(email);
				user.setSex(sex);
				user.setPassword(utils.generatePwd(pass));
				user.setOffice(office);

				internal = new InternalInternship();
				internal.setTheme(theme);
				internal.setAvailability(availability);
				internal.setResources(resources);
				internal.setGoals(goals);
				internal.setTutorn_name(user.getName().concat(" ").concat(user.getSurname()));
				internal.setFk_tutor(user.getEmail());

				if(daouser.addTeacher(user)) {
					if(daoint.addInternship(internal, flag)) {
						result = 1;
						content = "Docente con tirocinio registrato";
					} else {
						result = 0;
						error = "Errore nella registrazione del tirocinio";
					}
				} else {
					result = 0;
					error = "Errore nella registrazione del docente";
				}
			}


			JSONObject res = new JSONObject();
			res.put("result", result);
			res.put("error", error);
			res.put("content", content);
			PrintWriter out = response.getWriter();
			out.println(res);
			response.setContentType("json");
		}
	}

