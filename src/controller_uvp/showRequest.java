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
import model_uvp.RequestInternship;

/**
 * 
 * Servlet che consente di visualizzare tutte le richieste ad un dato studente.
 * 
 * 
 * @author Antonio Baldi
 *
 */
@WebServlet("/showRequest")
public class showRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showRequest() {
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
		ArrayList<RequestInternship> requests;
		List<String> attached;
		DAORequest queryobj = new DAORequest();

		if (currUser != null){
			try {
				requests = queryobj.viewRequests(currUser.getEmail());

				if(requests.size()>0)
					for(RequestInternship a : requests){
						
						attached = new ArrayList<>();
						jObj = new JSONObject();
						jObj.put("id", a.getId_request_i());
						jObj.put("user_serial", a.getUserSerial());

						if(a.getAttached().isEmpty()) {
							jObj.put("attached", "");
						}
						else 
							for (Attached b : a.getAttached())
								attached.add("<a href='" + request.getContextPath() + "/Downloader?filename=" + b.getFilename()+ "&idRequest=" + a.getId_request_i() + "'>" + b.getFilename() + "</a><br>");
								
						jObj.put("attached", attached);
						jObj.put("type", a.getType());
						jObj.put("status", a.getStatus());
						jObj.put("actions", ""
								+ "<label class=\"infoInternship btn btn-default\">"
								+ "<input type=\"button\" data-action=\"info\" data-toggle=\"modal\" data-target=\"#details\" id=\""+a.getId_request_i()+"\">" 
								+ "<span class=\"infoBtn glyphicon glyphicon-info-sign\"></span>" 
								+ "</label>");
						jArr.add(jObj);
					}
			}
			catch(Exception e){
				e.printStackTrace();
			}

			mainObj.put("data", jArr);
			PrintWriter out = response.getWriter();
			out.println(mainObj);
			response.setContentType("json");
		}
	}
}
