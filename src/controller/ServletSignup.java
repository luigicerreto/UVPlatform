package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import interfacce.UserInterface;
import model.Student;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/ServletSignup")
public class ServletSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSignup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		PreparedStatement stmt = null;

		Connection conn = new DbConnection().getInstance().getConn();
		String sql = "";

		if (conn != null) { // registrazione nuovo utente
			String name = request.getParameter("name");
			
			if (name.length() == 0 || name.length() > 20 || name.matches(".*\\d+.*")) {
				throw new IllegalArgumentException("Formato non corretto");
			}

			String surname = request.getParameter("surname");
			
			if (surname.length() == 0 || surname.length() > 20 || surname.matches(".*\\d+.*")) {
				throw new IllegalArgumentException("Formato non corretto");
			}

			String email = request.getParameter("email");
			String serial = request.getParameter("serial");
			String prefix = "";

			if (email.length() > 0) {
				prefix = email.substring(0, email.indexOf("@"));
			}

			if (email.length() == 0 
					|| !email.endsWith("@studenti.unisa.it") 
					|| prefix.length() < 3 || prefix.indexOf(".") == -1) {
				throw new IllegalArgumentException("Formato non corretto");
			}

			char sex = request.getParameter("sex").charAt(0);
			
			if (sex != 'M' && sex != 'F') {
				throw new IllegalArgumentException("Valore non corretto");
			}

			String pass = request.getParameter("password");
			
			if (pass.length() < 8) {
				throw new IllegalArgumentException("Formato non corretto");
			}

			String password = new Utils().generatePwd(pass);
			int userType = 0;
			UserInterface user = null;

			try {
				sql = " SELECT  email FROM user WHERE TRIM(LOWER(email)) = TRIM(?) ";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, email.toLowerCase());
				ResultSet r = stmt.executeQuery();
				if (r.wasNull()) {
					error = "Errore nell'esecuzione della Query";
				} else {
					int count = r.last() ? r.getRow() : 0;
					if (count == 0) {
						sql = " INSERT INTO user " + " (email, name, surname, sex, password, user_type, serial) "
								+ " VALUES " + " (?, ?, ?, ?, ?, ?,?) ";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, email.toLowerCase());
						stmt.setString(2, name);
						stmt.setString(3, surname);
						stmt.setString(4, String.valueOf(sex));
						stmt.setString(5, password);
						stmt.setInt(6, userType);

						stmt.setString(7, serial);
						if (stmt.executeUpdate() > 0) {
							// modifica effettuata per effettuare la scelta di english validation o tirocinio esterno
							redirect = request.getContextPath() + "/choice.jsp"; 
							// redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
							user = new Student(email, name, surname, sex, password, userType);
							request.getSession().setAttribute("user", user);
							content = "Registrazione effettuata correttamente.";
							result = 1;
						} else {
							error = "Impossibile effettuare la registrazione.";
						}
					} else {
						error = "Utente gi&agrave; registrato.";
					}
				}

				if (result == 0) {
					conn.rollback();
				} else {
					conn.commit();
				}

			} catch (Exception e) {
				error += e.getMessage();
			}
		} else {
			error += "Nessuna connessione al database.";
		}


		JSONObject res = new JSONObject();
		res.put("result", result);
		res.put("error", error);
		res.put("content", content);
		res.put("redirect", redirect);
		PrintWriter out = response.getWriter();
		out.println(res);
		response.setContentType("json");
	}
}
