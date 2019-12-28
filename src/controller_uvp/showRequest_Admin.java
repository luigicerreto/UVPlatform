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
import model_uvp.DAORequest;
import model_uvp.RequestInternship;

/**
 * Servlet implementation class showRequest_Admin
 */
@WebServlet("/showRequest_Admin")
public class showRequest_Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showRequest_Admin() {
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
		ArrayList<RequestInternship> requests;
		DAORequest queryobj = new DAORequest();
		List<String> attached;
		JSONObject jObj;
		JSONArray jArr = new JSONArray();
		JSONObject mainObj = new JSONObject();

		if (currUser != null) 
		{
			try
			{
				requests = queryobj.viewRequestsSecretary();

				for(RequestInternship a : requests)
				{
					attached = new ArrayList<>();
					jObj = new JSONObject();
					jObj.put("id",a.getId_request_i());
					jObj.put("theme", a.getUserSerial());

					if(a.getAttached().isEmpty()) {
						jObj.put("attached", "");
					}
					else 
						for (Attached b : a.getAttached())
							attached.add("<a href='" + request.getContextPath() + "/Downloader?filename=" + b.getFilename()+ "&idRequest=" + a.getId_request_i() + "'>" + b.getFilename() + "</a>");

					jObj.put("attached", attached);					
					jObj.put("name",a.getUserName());
					jObj.put("surname", a.getUserSurname());
					jObj.put("type", a.getType());
					jObj.put("state",a.getStatus());
					if(a.getStatus().equalsIgnoreCase("[ADMIN] In attesa di accettazione")) {
						jObj.put("actions", ""
								+ "<label class=\"actionInternship btn btn-default\">" 
								+ "<input type=\"button\" data-action=\"accept\" id=\""+a.getId_request_i() +"\">"
								+ "<span class=\"acceptBtn glyphicon glyphicon-ok\"></span>" 
								+ "</label>"
								+ "<label class=\"actionInternship btn btn-default\">" 
								+ "<input type=\"button\" data-action=\"reject\" id=\""+a.getId_request_i()+"\">" 
								+ "<span class=\"refuseBtn glyphicon glyphicon-remove\"></span>" 
								+ "</label>"
								+ "<label class=\"infoInternship btn btn-default\">"
								+ "<input type=\"button\" data-action=\"info\" data-toggle=\"modal\" data-target=\"#details\" id=\""+a.getId_request_i()+"\">" 
								+ "<span class=\"infoBtn glyphicon glyphicon-info-sign\"></span>" 
								+ "</label>");
					}
					else
						jObj.put("actions", ""
								+ "<label class=\"actionInternship btn btn-default\" disabled>" 
								+ "<input type=\"button\" data-action=\"accept\" id=\""+a.getId_request_i() +"\">"
								+ "<span class=\"acceptBtn glyphicon glyphicon-ok\"></span>" 
								+ "</label>"
								+ "<label class=\"actionInternship btn btn-default\" disabled>" 
								+ "<input type=\"button\" data-action=\"reject\" id=\""+a.getId_request_i()+"\">" 
								+ "<span class=\"refuseBtn glyphicon glyphicon-remove\"></span>" 
								+ "</label>"
								+ "<label class=\"infoInternship btn btn-default\">"
								+ "<input type=\"button\" data-action=\"info\" data-toggle=\"modal\" data-target=\"#details\" id=\""+a.getId_request_i()+"\">" 
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



