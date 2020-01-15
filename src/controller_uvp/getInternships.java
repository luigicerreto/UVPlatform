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

import model_uvp.DAOInternship;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;

/**
 * Servlet implementation class getInternships
 */
@WebServlet("/getInternships")
public class getInternships extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getInternships() {
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
		JSONObject jObj;
		JSONArray jArr = new JSONArray();
		JSONObject mainObj = new JSONObject();
		DAOInternship queryobj = new DAOInternship();

		ArrayList<InternalInternship> internal;
		ArrayList<ExternalInternship> external;

		Integer flag = Integer.parseInt(request.getParameter("flag"));

		try{
			if (flag == 0) {
				internal =  (ArrayList<InternalInternship>) ((ArrayList<?>) queryobj.viewInternalInternships());
				if(internal.size()>0)
					for(InternalInternship a : internal)
					{
						jObj = new JSONObject();
						jObj.put("id", a.getId());
						jObj.put("name", a.getTutor_name());
						jObj.put("theme", a.getTheme());
						jObj.put("place", a.getPlace());
						jObj.put("choice",""
								+ "<label class=\"info btn btn-default\">"
								+ "<input type='button' data-type-internship='0' data-type-info='2' data-toggle='modal' data-target='#details' id='"+ a.getId() +"'>" 
								+ "<span class=\"infoBtn glyphicon glyphicon-info-sign\"></span>" 
								+ "</label>"
								+ "<label class=\"selectInternship btn btn-default\">" 
								+ "<input type=\"radio\" name=\"options\" id=\""+a.getId()+"\">" 
								+ "<span class=\"glyphicon glyphicon-ok\"></span>" 
								+ "</label>");
						jArr.add(jObj);
					}
			} else if (flag == 1) {
				external =  (ArrayList<ExternalInternship>) ((ArrayList<?>) queryobj.viewExternalInternships());
				if(external.size()>0)
					for(ExternalInternship a : external)
					{
						jObj = new JSONObject();
						jObj.put("id", a.getId());
						jObj.put("name", a.getName());
						jObj.put("date_convention", String.valueOf(a.getDate_convention()));
						jObj.put("duration_convention", a.getDuration_convention());
						jObj.put("choice",""
								+ "<label class=\"info btn btn-default\">"
								+ "<input type='button' data-type-internship='1' data-type-info='2' data-toggle='modal' data-target='#details' id='"+ a.getId() +"'>" 
								+ "<span class=\"infoBtn glyphicon glyphicon-info-sign\"></span>" 
								+ "</label>"
								+ "<label class=\"selectInternship btn btn-default\">" 
								+ "<input type=\"radio\" name=\"options\" id=\""+a.getId()+"\">" 
								+ "<span class=\"glyphicon glyphicon-ok\"></span>" 
								+ "</label>");
						jArr.add(jObj);
					}
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
