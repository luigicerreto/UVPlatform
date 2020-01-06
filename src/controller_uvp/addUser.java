package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.DbConnection;
import controller.Utils;
import interfacce.UserInterface;
import model.Student;
import model_uvp.DAORequest;
import model_uvp.DAOUser;
import model_uvp.User;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addUser() {
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
		
		DAOUser queryobj = new DAOUser();
		User user = null;
		Utils utils = new Utils();
		

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String office = request.getParameter("office");
		char sex = request.getParameter("sex").charAt(0);
		
		String prefix = "";

		if (name.length() == 0 || name.length() > 20) {
			error = "Nome: formato non corretto";
		} else if (surname.length() == 0 || surname.length() > 20 || surname.matches(".*\\d+.*")) {
			error = "Cognome: formato non corretto";
		} else if (email.length() > 0) {
			prefix = email.substring(0, email.indexOf("@"));
		} else if (email.length() == 0 || !email.endsWith("@unisa.it") || prefix.length() < 3) {
			error = "E-Mail: formato non corretto";
		} else if (sex != 'M' && sex != 'F') {
			error = "Sesso: valore non corretto";
		} else if (pass.length() < 8) {
			error = "Password: formato non corretto";
		} else {
			user = new User();
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			user.setSex(sex);
			user.setPassword(utils.generatePwd(pass));
			user.setOffice(office);
			if(queryobj.addTeacher(user)) {
				result = 1;
				content = "Utente aggiunto correttamente";
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

