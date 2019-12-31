package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import interfacce.UserInterface;
import model.Attached;
import model_uvp.DAORequest;
import model_uvp.DAOUser;
import model_uvp.RequestInternship;
import model_uvp.User;

/** 
 * Servlet che consente di visualizzare tutti gli utenti sulla piattaforma
 * 
 * @author Carmine
 */
@WebServlet("/showUsers")
public class showUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showUsers() {
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

		JSONObject jObj;
		JSONArray jArr = new JSONArray();
		JSONObject mainObj = new JSONObject();
		ArrayList<User> users;
		DAOUser queryobj = new DAOUser();

		if (currUser != null && currUser.getUserType() == 2){
			try {
				users = queryobj.viewUsers();
				
				if(users.size()>0)
					for(User u : users){
						jObj = new JSONObject();
						jObj.put("email", u.getEmail());
						jObj.put("name", 
								"<div class='edit-info' id='" + u.getEmail()+ "'"
								+"class='form-group col-lg-6 col-md-6 col-sm-12 col-xs-12'>"
								+"<input type='text' class='form-control' data-field='name'"
								+"value='" + u.getName() + "' minlength='10' maxlength='10'"
								+"required disabled></input> <i class='fa fa-edit'></i>"
								+ "</div>");
						jObj.put("surname", 
								"<div class='edit-info' id='" + u.getEmail()+ "'"
								+"class='form-group col-lg-6 col-md-6 col-sm-12 col-xs-12'>"
								+"<input type='text' class='form-control' data-field='surname'"
								+"value='" + u.getSurname() + "' minlength='10' maxlength='10'"
								+"required disabled></input> <i class='fa fa-edit'></i>"
								+ "</div>");
						jObj.put("sex", String.valueOf(u.getSex()));
						jObj.put("serial", u.getSerial());
						jObj.put("phone", 
								"<div class='edit-info' id='" + u.getEmail()+ "'"
								+"class='form-group col-lg-6 col-md-6 col-sm-12 col-xs-12'>"
								+"<input type='text' class='form-control' data-field='phone'"
								+"value='" + u.getPhone() + "' minlength='10' maxlength='10'"
								+"required disabled></input> <i class='fa fa-edit'></i>"
								+ "</div>");

						jArr.add(jObj);
					}

			} catch(Exception e){
				e.printStackTrace();
			}
		}

		mainObj.put("data", jArr);
		PrintWriter out = response.getWriter();
		out.println(mainObj);
		response.setContentType("json");
	}
}
