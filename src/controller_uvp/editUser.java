package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.Utils;
import interfacce.UserInterface;
import model_uvp.DAOUser;
import model_uvp.User;


/** 
 * Servlet che consente di visualizzare tutti gli utenti sulla piattaforma
 * 
 * @author Carmine
 */
@WebServlet("/editUser")
public class editUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editUser() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInterface currUser = (UserInterface) request.getSession().getAttribute("user");
		Integer result = 0;
		String error = "";
		String content = "";

		DAOUser queryobj = new DAOUser();
		Utils utils = new Utils();

		String email = request.getParameter("email");
		String field = request.getParameter("field");
		String value = request.getParameter("value");


		if (currUser != null){
			if(field.equals("password")) { // se modifica la password
				String currentPwd = request.getParameter("current_pwd");
				String currentPwdHashed = utils.generatePwd(currentPwd);
				String newPwdHashed = utils.generatePwd(value);

				try {
					User u = queryobj.getUser(email);

					if(u.getPassword().equals(currentPwdHashed)) {
						if(queryobj.editUser(email, field, newPwdHashed)) {
							result = 1;
							content = "Password modificata";
						} else {
							result = 0;
							error = "Non è stato possibile modificare la password";
						}
					} else {
						result = 0;
						error = "Password attuale errata";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { // se modifica qualsiasi altra info
				try {
					if(queryobj.editUser(email, field, value)) {
						result = 1;
						content = "Modifica effettuata";
					} else {
						result = 0;
						error = "Non è stato possibile effettuare alcuna modifica";
					}
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		} else {
			result = 0;
			error = "Non è stato possibile effettuare alcuna modifica";
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
