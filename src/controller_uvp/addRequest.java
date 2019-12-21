package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import interfacce.UserInterface;
import model_uvp.DAORichiesta;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.RequestInternship;

/**
 * 
 * Servlet per gestire l'aggiunta di una nuova richiesta.
 * Come parametri necessari ci sono il tipo di richiesta (Interno oppure Esterno)
 * denominati da "type", e l'identificativo del tirocinio denominato da "choice".
 * 
 * 
 * @author Antonio Baldi
 *
 */
@WebServlet("/addRequest")
public class addRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addRequest() {
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
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		final String requestState = "Parzialmente Completata";
		int id_request;
		int type_internship;
		String internship_type;
		InternalInternship is_internal;
		ExternalInternship is_external;
		RequestInternship newRequest = new RequestInternship();
		int returnMessage;
		DAORichiesta queryobj = new DAORichiesta();


		id_request = (Integer.parseInt(request.getParameter("choice")));
		type_internship = (Integer.parseInt(request.getParameter("type")));
		if(type_internship==0)
		{
			internship_type = "Tirocinio Interno";
			is_internal = queryobj.retriveInternship_internal(id_request);
			newRequest.setUserFullName(queryobj.InternalPerform(id_request));
			newRequest.setId_ii(id_request);

		}
		else
		{
			internship_type = "Tirocinio Esterno";
			is_external = queryobj.retriveInternship_external(id_request);
			newRequest.setUserFullName(queryobj.ExternalPerform(id_request));
			newRequest.setId_ie(id_request);
			
		}
		newRequest.setType(internship_type);
		newRequest.setState(requestState);
		newRequest.setTheme(currUser.getEmail());
		returnMessage=queryobj.addRequest(newRequest);
		if(returnMessage==1)
		{
			result=1;
			content="Richiesta Parziale presentata con successo";
		}
		else if(returnMessage==2)
		{
			result=0;
			error = "Una richiesta gi&agrave; presentata non &egrave; stata ancora conclusa.";
		}
		else
		{
			result=0;
			error="Errore nella presentazione della richiesta";
		}

		
		request.getSession().setAttribute("idRequest_i", queryobj.CheckLastPartialRequest(currUser.getEmail()));

		redirect = request.getContextPath() + "/_areaStudent_uvp/uploadAttached_uvp.jsp";
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
