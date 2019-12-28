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
import util.notifyStudent;

/** 
 * Servlet per gestire l'aggiunta degli allegati ad una determinata richiesta.
 * Questa servlet richiede "filenames[]" come parametro della request
 * e l'identificativo della richiesta come parametro di sessione.
 * 
 * @author Antonio Baldi
 */
@WebServlet("/addAttached")
public class addAttached extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addAttached() {
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
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		DAORequest queryobj = new DAORequest();

		UserInterface user = (UserInterface) request.getSession().getAttribute("user");
		Integer id_request = Integer.parseInt(request.getParameter("id_request"));
		boolean new_request = Boolean.parseBoolean(request.getParameter("new_request"));
		String type_request = queryobj.getRequestTypeById(id_request);
		String[] filenames = request.getParameterValues("filenames[]");

		if (filenames.length != 1 || !filenames[0].endsWith(".pdf")) 
		{
			throw new IllegalArgumentException("Valore non corretto");
		}

		// aggiunge l'allegato e notifica lo studente
		if(queryobj.addAttached(filenames[0], user.getEmail(), id_request)) {
			notifyStudent notify = new notifyStudent();
			new Thread(() -> {
				try {
					notify.notify(user.getEmail(), id_request);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();

			if(new_request) { // se viene inserito il primo allegato
				if(type_request.equalsIgnoreCase("tirocinio interno")) {
					if(queryobj.setStatus(id_request, "[DOCENTE] In attesa di accettazione")) {	
						content = "Allegato inserito con successo";
						result = 1;
					}
				} else if (type_request.equalsIgnoreCase("tirocinio esterno")) {
					if(queryobj.setStatus(id_request, "[AZIENDA] In attesa di accettazione")) {	
						content = "Allegato inserito con successo";
						result = 1;
					}
				} else {
					error = "Impossibile inserire l'allegato";
					result = 0;
				}
			} else { // se viene inserito un allegato aggiuntivo
				if(queryobj.setStatus(id_request, "[SEGRETERIA] In attesa di accettazione")) {	
					content = "Allegato inserito con successo";
					result = 1;
				} else {
					error = "Impossibile inserire l'allegato";
					result = 0;
				}
			}
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
