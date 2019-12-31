package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import interfacce.UserInterface;
import model_uvp.DAOUser;


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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInterface currUser = (UserInterface) request.getSession().getAttribute("user");
		Integer result = 0;
		String error = "";
		String content = "";

		DAOUser queryobj = new DAOUser();

		String email = request.getParameter("email");
		String field = request.getParameter("field");
		String value = request.getParameter("value");
		
		System.out.println("VAL: " + value + "PARAM: " + field + " EMAIL: " + email);

		if (currUser != null && currUser.getUserType() == 2){
			try {
				if(queryobj.editUser(email, field, value)) {
					result = 1;
					content = "Modifica effettuata";
				} else {
					result = 0;
					content = "Non è stato possibile effettuare alcuna modifica";
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		} else {
			result = 0;
			content = "Non è stato possibile effettuare alcuna modifica";
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
