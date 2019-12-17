package controller_uvp;

import java.awt.Color;
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
import model_uvp.InternalInternship;
import model_uvp.RequestInternship;

/**
 * Servlet implementation class InternalTrainee
 */
@WebServlet("/InternalTrainee")
public class InternalTrainee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InternalTrainee() {
		super();
		// TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";

		ArrayList<InternalInternship> internship = new ArrayList<InternalInternship>();
		try
		{
			internship =  DAOTirocini.viewTraineeInternal();
			if(internship.size()==0)
			{

				content += "<tr>"
						+ "<td class=\"text-center\"" + "></td>"
						+ "<td class=\"text-center\"" + "></td>"
						+ "<td class=\"text-center\"" + ">Nessun tirocinio interno disponibile</td>"
						+ "<td class=\"text-center\"" + "></td>"
						+ "<td class=\"text-center\"" + "></td>"
						+ "</tr>";


			}
			else
				for(InternalInternship a : internship)
				{

					content += "<tr role='row'>";
					content += "    <td class='text-center'>" + a.getId_ii() + "</td>";
					content += "    <td class='text-center'>" + a.getTutorn_name() + "</td>";
					content += "    <td class='text-center'>" + a.getAvailability() + "</td>";
					content += " <td class='text-center'>" + 
					"<button class=\"glyphicon glyphicon-info-sign\" style=\"color:orange\" aria-hidden=\"true\" href=\"#\"></button>" + "</td>";
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
