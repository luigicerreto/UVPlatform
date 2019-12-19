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
 * Servlet implementation class addRequest
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

		id_request = (Integer.parseInt(request.getParameter("choice")));
		System.out.println("l'id è "+id_request);
		type_internship = (Integer.parseInt(request.getParameter("type")));
		if(type_internship==0)
		{
			internship_type = "Tirocinio Interno";
			is_internal = DAORichiesta.retriveInternship_internal(id_request);
			newRequest.setUser2(DAORichiesta.InternalPerform(id_request));
			newRequest.setId_ii(id_request);

		}
		else
		{
			internship_type = "Tirocinio Esterno";
			is_external = DAORichiesta.retriveInternship_external(id_request);
			newRequest.setUser2(DAORichiesta.ExternalPerform(id_request));
			newRequest.setId_ie(id_request);
			
		}
		newRequest.setType(internship_type);
		newRequest.setState(requestState);
		newRequest.setUser1(currUser.getEmail());
		returnMessage=DAORichiesta.addRequest(newRequest);
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

		
		request.getSession().setAttribute("idRequest_i", DAORichiesta.CheckLastPartialRequest(currUser.getEmail()));
		System.out.println("l'id della funzione del cazzo è "+ DAORichiesta.CheckLastPartialRequest(currUser.getEmail()));
		System.out.println("L'id request assegnato alla sessione è "+newRequest.getId_request_i());

		redirect = request.getContextPath() + "/uploadAttached_uvp.jsp";
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
