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

import model_uvp.DAOInternship;
import model_uvp.DAOUser;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.Internship;
import model_uvp.User;
import util.Notifier;
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
		final User user = new User();
		PasswordGenerator pwdgen = new PasswordGenerator();
		String pass = pwdgen.generate(8);

		Integer flag = Integer.parseInt(request.getParameter("flag"));

		Internship internship = null;

		if(flag == 0) { // aggiungi interno
			// dati docente
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String office = request.getParameter("office");
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
				user.setName(name);
				user.setSurname(surname);
				user.setEmail(email);
				user.setSex(sex);
				user.setPassword(pass);
				user.setOffice(office);

				internship = new InternalInternship();
				((InternalInternship) internship).setTheme(theme);
				((InternalInternship) internship).setAvailability(availability);
				((InternalInternship) internship).setResources(resources);
				((InternalInternship) internship).setGoals(goals);
				((InternalInternship) internship).setTutor_name(user.getName().concat(" ").concat(user.getSurname()));
				((InternalInternship) internship).setFk_tutor(user.getEmail());

				if(daouser.addTeacher(user)) {
					if(daoint.addInternship(internship, flag)) {
						result = 1;
						content = "Docente con tirocinio registrato";
					} else {
						error = "Errore nella registrazione del tirocinio";
					}
				} else {
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
			Integer duration_convention = Integer.parseInt(request.getParameter("duration"));
			String info = request.getParameter("info");

			Date date_convention = null;
			try {
				date_convention = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (name.length() < 2 || name.length() > 20) {
				error = "Il campo \"Nome\" non rispetta il formato";
			} else if (email.length() == 0 || prefix.length() < 3) {
				error = "Il campo \"Email\" non rispetta il formato";
			} else if (availability < 0) {
				error = "Il campo \"Disponibilità\" non rispetta i valori consentiti";
			} else if (duration_convention < 0) {
				error = "Il campo \"Durata\" non rispetta i valori consentiti";
			} else if (info.length() < 2) {
				error = "Il campo \"Info\" non raggiunge la lunghezza minima";
			} else {
				user.setName(name);
				user.setEmail(email);
				user.setPassword(pass);
				user.setOffice(office);

				internship = new ExternalInternship();
				((ExternalInternship) internship).setName(name);
				((ExternalInternship) internship).setAvailability(availability);
				((ExternalInternship) internship).setDate_convention(date_convention);
				((ExternalInternship) internship).setInfo(info);
				((ExternalInternship) internship).setDuration_convention(duration_convention);
				((ExternalInternship) internship).setFk_tutor(user.getEmail());

				if(daouser.addCompany(user)) {
					if(daoint.addInternship(internship, flag)) {
						result = 1;
						content = "Azienda con tirocinio registrata";
					} else {
						error = "Errore nella registrazione del tirocinio";
					}
				} else {
					error = "Errore nella registrazione dell'azienda";
				}
			}

			if(result == 1) { // notifica docente/azienda
				new Thread(() -> {
					Notifier.notifyNewUser(user);
				}).start();
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
}

