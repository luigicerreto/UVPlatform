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
import util.Notifier;

/** 
 * Servlet per gestire l'aggiunta degli allegati ad una determinata richiesta.
 * Questa servlet richiede "filenames[]" come parametro della request
 * e l'identificativo della richiesta come parametro di sessione.
 * 
 * @author Antonio Baldi
 * @author Carmine
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
		DAORequest daoreq = new DAORequest();

		UserInterface user = (UserInterface) request.getSession().getAttribute("user");
		Integer id_request = Integer.parseInt(request.getParameter("id_request"));
		boolean new_request = Boolean.parseBoolean(request.getParameter("new_request"));
		RequestInternship req = daoreq.getRequest(id_request);
		String[] filenames = request.getParameterValues("filenames[]");

		if (filenames.length != 1 || !filenames[0].endsWith(".pdf")) 
		{
			throw new IllegalArgumentException("Valore non corretto");
		}

		// aggiunge l'allegato e notifica lo studente
		if(daoreq.addAttached(filenames[0], user.getEmail(), id_request)) {
			new Thread(() -> {
				Notifier.notifyStudent(user.getEmail(), id_request);
			}).start();

			if(new_request) { // se viene inserito il primo allegato
				if(req.getType() == 0) {
					if(daoreq.setStatus(id_request, "[DOCENTE] In attesa di accettazione")) {	
						content = "Allegato inserito con successo";
						result = 1;
					}
				} else if (req.getType() == 1) {
					if(daoreq.setStatus(id_request, "[AZIENDA] In attesa di accettazione")) {	
						content = "Allegato inserito con successo";
						result = 1;
					}
				} else {
					error = "Impossibile inserire l'allegato";
					result = 0;
				}
			} else { // se viene inserito un allegato aggiuntivo
				if(daoreq.setStatus(id_request, "[SEGRETERIA] In attesa di accettazione")) {	
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
