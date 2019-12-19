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

import model.Attached;
import model_uvp.DAORichiesta;
import model_uvp.DAOTirocini;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.RequestInternship;

/**
 * 
 * Servlet per la gestione di un tirocinio esterno. 
 * 
 * 
 * @author Antonio Baldi
 * @author Carmine Rovito
 *
 */
@WebServlet("/ExternalTrainee")
public class ExternalTrainee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExternalTrainee() {
        super();
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jObj;
		JSONArray jArr = new JSONArray();
		JSONObject mainObj = new JSONObject();
		DAOTirocini queryobj = new DAOTirocini();
		
		ArrayList<ExternalInternship> internship = new ArrayList<ExternalInternship>();
		try
		{
		internship =  queryobj.viewTraineeExternal();
		if(internship.size()>0)
			for(ExternalInternship a : internship)
			{
				jObj = new JSONObject();
				jObj.put("id", a.getId_ie());
				jObj.put("name", a.getName());
				jObj.put("place", a.getPlace());
				jObj.put("date", String.valueOf(a.getDate_convention()));
				jObj.put("choice","<div class=\"tableButtons\">"
						+ "<button type =\"button\" class=\"showDetails glyphicon glyphicon-info-sign\" data-toggle=\"modal\" data-target=\"#details\" aria-hidden=\"true\"></button>"
						+ "<label class=\"btn btn-default\">" 
						+ "<input type=\"radio\" class=\"prova1\" name=\"options\" id=\""+a.getId_ie()+"\">" 
						+ "<span class=\"glyphicon glyphicon-ok\"></span>" 
						+ "</label>"
						+ "</div>");
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
