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

import java.util.List;
import interfacce.UserInterface;
import model.Attached;
import model_uvp.DAORichiesta;
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
		UserInterface currUser = (UserInterface) request.getSession().getAttribute("user"); 
		String email="";
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		ArrayList<RequestInternship> richieste;
		List<Attached> allegati;
		DAORichiesta queryobj = new DAORichiesta();
		
		if (currUser != null) 
		{
			email = currUser.getEmail();
			try
			{
			richieste = queryobj.viewRequests(email);

			if(richieste.size()==0)
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
				for(RequestInternship a : richieste)
				{

					content += "<tr role='row'>";
					content += "    <td class='text-center'>" + a.getId_request_i() + "</td>";
					content += "    <td class='text-center'>" + a.getUser1() + "</td>";
					content += "    <td class='text-center'>";
					allegati = a.getAttached();
					for (Attached b : allegati)
					{
						content += "<a href='" + request.getContextPath() + "/Downloader?filename=" + b.getFilename()+ "&idRequest=" + a.getId_request_i() + "'>" + b.getFilename() + "</a><br>";
					}
					content += "    </td>";
					content += "    <td class='text-center'>" + a.getType() + "</td>";
					content += "    <td class='text-center'>" + a.getState() + "</td>";
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
}
