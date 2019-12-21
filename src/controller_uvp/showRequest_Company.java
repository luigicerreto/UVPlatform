package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import interfacce.UserInterface;
import model.Attached;
import model_uvp.DAORichiesta;
import model_uvp.RequestInternship;

/**
 * Servlet implementation class ShowRequest_Teacher
 */
@WebServlet("/showRequest_Company")
public class showRequest_Company extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showRequest_Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserInterface currUser = (UserInterface) request.getSession().getAttribute("user"); 
		String email="";
		ArrayList<RequestInternship> requests;
		DAORichiesta queryobj = new DAORichiesta();
		JSONObject jObj;
		JSONArray jArr = new JSONArray();
		JSONObject mainObj = new JSONObject();

		if (currUser != null) 
		{
			email = currUser.getEmail();

			try
			{
				requests = queryobj.viewRequestsCompany(email);

				for(RequestInternship a : requests)
				{

					jObj = new JSONObject();
					jObj.put("id", a.getId_request_i());
					for (Attached b : a.getAttached())
					{
						jObj.put("attached","<a href='" + request.getContextPath() + "/Downloader?filename=" + b.getFilename()+ "&idRequest=" + a.getId_request_i() + "'>" + b.getFilename() + "</a><br>");
					}
					jObj.put("theme", a.getTheme());	
					jObj.put("name", a.getUserName());
					jObj.put("surname", a.getUserSurname());
					jObj.put("type", a.getType());
					jObj.put("state",a.getState());
					jObj.put("actions", ""
							+ "<label class=\"actionInternship btn btn-default\">" 
							+ "<input type=\"button\" id=\""+a.getId_request_i() +"\">"
							+ "<span class=\"acceptBtn glyphicon glyphicon-ok\"></span>" 
							+ "</label>"
							+ "<label class=\"actionInternship btn btn-default\">" 
							+ "<input type=\"button\" id=\""+a.getId_request_i()+"\">" 
							+ "<span class=\"refuseBtn glyphicon glyphicon-remove\"></span>" 
							+ "</label>"
							+ "<label class=\"actionInternship btn btn-default\">"
							+ "<input type=\"button\" id=\""+a.getId_request_i()+"\">" 
							+ "<span class=\"uploadBtn glyphicon glyphicon-open\"></span>" 
							+ "</label>"
							+ "<label class=\"actionInternship btn btn-default\">"
							+ "<input type=\"button\" id=\""+a.getId_request_i()+"\">" 
							+ "<span class=\"downloadBtn glyphicon glyphicon-save\"></span>" 
							+ "</label>"
							+ "<label class=\"actionInternship btn btn-default\">"
							+ "<input type=\"button\" id=\""+a.getId_request_i()+"\">" 
							+ "<span class=\"infoBtn glyphicon glyphicon-info-sign\"></span>" 
							+ "</label>");
					jArr.add(jObj);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			mainObj.put("data", jArr);
			PrintWriter out = response.getWriter();
			out.println(mainObj);
			response.setContentType("json");
		}
	}
}

