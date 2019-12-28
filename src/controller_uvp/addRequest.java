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
import model_uvp.DAORequest;
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
		UserInterface currUser = (UserInterface) request.getSession().getAttribute("user"); 
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		int id_request;
		int type_request;
		RequestInternship newRequest = new RequestInternship();
		int returnMessage;
		DAORequest queryobj = new DAORequest();


		id_request = (Integer.parseInt(request.getParameter("choice")));
		type_request = (Integer.parseInt(request.getParameter("type")));
		
		if(type_request==0){
			newRequest.setUserFullName(queryobj.InternalPerform(id_request));
			newRequest.setType("Tirocinio Interno");
			newRequest.setId_ii(id_request);
		} else {
			newRequest.setUserFullName(queryobj.ExternalPerform(id_request));
			newRequest.setType("Tirocinio Esterno");
			newRequest.setId_ie(id_request);
		}

		newRequest.setStatus("Parzialmente completata");
		newRequest.setUserEmail(currUser.getEmail());
		returnMessage=queryobj.addRequest(newRequest);

		if(returnMessage == 1) {
			result = 1;
			content = "Richiesta parziale presentata";
		} else if(returnMessage == 2) {
			result = 0;
			error = "Hai una richiesta ancora non conclusa";
		} else {
			result = 0;
			error = "Errore nella presentazione della richiesta";
		}


		int partial_request = queryobj.checkLastPartialRequest(currUser.getEmail());
		redirect = request.getContextPath() + "/_areaStudent_uvp/uploadAttached_uvp.jsp?id_request=" 
					+ partial_request + "&new_request=true";
		
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
