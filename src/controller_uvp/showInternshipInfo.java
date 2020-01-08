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

import model_uvp.DAOInternship;
import model_uvp.DAORequest;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.Internship;
import model_uvp.RequestInternship;

/**
 * Servlet implementation class showRequestInfo
 */
@WebServlet("/showInternshipInfo")
public class showInternshipInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showInternshipInfo() {
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
		JSONObject jObj = null;
		Internship internship;
		DAOInternship queryobj = new DAOInternship();

		int idInternship = Integer.parseInt(request.getParameter("id_internship"));
		int typeInternship = Integer.parseInt(request.getParameter("type_internship"));

		try {
			internship = queryobj.getInternshipData(idInternship, typeInternship);

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

		PrintWriter out = response.getWriter();
		out.println(jObj);
		response.setContentType("json");
	}

}
