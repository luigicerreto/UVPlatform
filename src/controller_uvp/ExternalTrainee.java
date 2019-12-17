package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.Attached;
import model_uvp.DAOTirocini;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.RequestInternship;

/**
 * Servlet implementation class InternalTrainee
 */
@WebServlet("/ExternalTrainee")
public class ExternalTrainee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExternalTrainee() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		
		ArrayList<ExternalInternship> internship = new ArrayList<ExternalInternship>();
		try
		{
		internship =  DAOTirocini.viewTraineeExternal();
		if(internship.size()==0)
		{
			content += "<tr>"
					+ "<td class=\"text-center\"" + "></td>"
					+ "<td class=\"text-center\"" + "></td>"
					+ "<td class=\"text-center\"" + ">Nessuna Richiesta Presente</td>"
					+ "<td class=\"text-center\"" + "></td>"
					+ "<td class=\"text-center\"" + "></td>"
					+ "</tr>";
		}
		else
			for(ExternalInternship a : internship)
			{

				content += "<tr role='row'>";
				content += "    <td class='text-center'>" + a.getId_ie() + "</td>";
				content += "    <td class='text-center'>" + a.getName() + "</td>";
				content += "    <td class='text-center'>" + a.getPlace() + "</td>";
				content += "    <td class='text-center'>" + a.getDate_convention() + "</td>";
				content += "</tr>";


			}
		result=1;
		}
		catch(Exception e)
		{
			error = "catch";
			result=0;
			e.printStackTrace();
		}



		JSONObject res = new JSONObject();
		res.put("result", result);
		res.put("error", error);
		res.put("content", content);
		res.put("redirect", redirect);
		PrintWriter out = response.getWriter();
		out.println(res);
		response.setContentType("json");
	}

}
