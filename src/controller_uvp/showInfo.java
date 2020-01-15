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

import model.Attached;
import model_uvp.DAOInternship;
import model_uvp.DAORequest;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.Internship;
import model_uvp.RequestInternship;

/**
 * Servlet implementation class showRequestInfo
 */
@WebServlet("/showInfo")
public class showInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showInfo() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jObj = null;
		DAOInternship daoint = new DAOInternship();
		DAORequest daoreq = new DAORequest();

		List<String> attached;
		Integer flag = Integer.parseInt(request.getParameter("flag"));



		if (flag == 0) { // info richiesta 
			Integer id = Integer.parseInt(request.getParameter("id"));

			try {
				RequestInternship req = daoreq.getRequest(id);
				attached = new ArrayList<>();
				jObj = new JSONObject();
				jObj.put("id", req.getId_request_i());
				jObj.put("status", req.getStatus());
				if (req.getType() == 0) {
					jObj.put("type", req.getType());
					jObj.put("theme", ((InternalInternship) req.getInternship()).getTheme());
					jObj.put("tutor", ((InternalInternship) req.getInternship()).getTutor_name());
					jObj.put("tutor_email", req.getTutor().getEmail());
					jObj.put("place", ((InternalInternship) req.getInternship()).getPlace());
					jObj.put("goals", ((InternalInternship) req.getInternship()).getGoals());
					jObj.put("resources", ((InternalInternship) req.getInternship()).getResources());
					if(req.getAttached().isEmpty())
						jObj.put("attached", "");
					else 
						for (Attached b : req.getAttached())
							attached.add("<a href='" + request.getContextPath() + "/Downloader?flag=1&filename=" + b.getFilename()
							+ "&idRequest=" + req.getId_request_i() + "'>" + b.getFilename() + "</a>");
					jObj.put("attached", attached);
				}
				else if (req.getType() == 1) {
					jObj.put("type", req.getType());
					jObj.put("company", ((ExternalInternship) req.getInternship()).getName());
					jObj.put("company_email", req.getTutor().getEmail());
					jObj.put("date_convention", String.valueOf(((ExternalInternship) req.getInternship()).getDate_convention()));
					jObj.put("duration_convention", ((ExternalInternship) req.getInternship()).getDuration_convention());
					jObj.put("info", ((ExternalInternship) req.getInternship()).getInfo());
					if(req.getAttached().isEmpty())
						jObj.put("attached", "");
					else 
						for (Attached b : req.getAttached())
							attached.add("<a href='" + request.getContextPath() + "/Downloader?flag=1&filename=" + b.getFilename()
							+ "&idRequest=" + req.getId_request_i() + "'>" + b.getFilename() + "</a>");
					jObj.put("attached", attached);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (flag == 1 || flag == 2) { // info tirocinio
			try {
				Internship internship = null;
				Integer typeInternship = Integer.parseInt(request.getParameter("type_internship"));
				if (flag == 1) { // ottieni info tirocinio via mail tutor
					String email = request.getParameter("email");
					internship = daoint.getInternshipByEmail(email, typeInternship);
				} else if (flag == 2) { // ottieni info tirocinio via id tirocinio
					Integer id = Integer.parseInt(request.getParameter("id"));
					internship = daoint.getInternship(id, typeInternship);
				}

				if(internship != null) {
					if(typeInternship == 0) {
						jObj = new JSONObject();
						jObj.put("id", internship.getId());
						jObj.put("tutor_name", ((InternalInternship) internship).getTutor_name());
						jObj.put("theme", ((InternalInternship) internship).getTheme());
						jObj.put("availability", internship.getAvailability());
						jObj.put("resources", ((InternalInternship) internship).getResources());
						jObj.put("goals", ((InternalInternship) internship).getGoals());
						jObj.put("place", ((InternalInternship) internship).getPlace());
					}
					else if (typeInternship == 1) {
						jObj = new JSONObject();
						jObj.put("id", internship.getId());
						jObj.put("name", ((ExternalInternship) internship).getName());
						jObj.put("duration_convention", ((ExternalInternship) internship).getDuration_convention());
						jObj.put("date_convention", String.valueOf(((ExternalInternship) internship).getDate_convention()));
						jObj.put("availability", internship.getAvailability());
						jObj.put("info", ((ExternalInternship) internship).getInfo());
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		PrintWriter out = response.getWriter();
		out.println(jObj);
		response.setContentType("json");
	}
}
