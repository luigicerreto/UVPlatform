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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			System.out.println("l'email è "+email);
			try
			{
				richieste = queryobj.viewRequestsCompany(email);

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
						int index = a.getUser2().indexOf("+");
						String nome = a.getUser2().substring(0, index);
						String cognome = a.getUser2().substring(index+1);
						content += "    <td class='text-center'>" + nome + "</td>";
						content += "    <td class='text-center'>" + cognome + "</td>";
						content += "    <td class='text-center'>" + a.getType() + "</td>";
						
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

