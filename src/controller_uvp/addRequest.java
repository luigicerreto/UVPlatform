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
import model_uvp.DAOInternship;
import model_uvp.DAORequest;
import model_uvp.DAOUser;
import model_uvp.RequestInternship;
import model_uvp.User;

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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";

		RequestInternship req = new RequestInternship();
		DAORequest daoreq = new DAORequest();
		DAOUser daouser = new DAOUser();
		DAOInternship daoint = new DAOInternship();

		User student = null;
		if (request.getSession().getAttribute("user") != null)
			student = daouser.getUser(((UserInterface) request.getSession().getAttribute("user")).getEmail());
		
		int id_request = (Integer.parseInt(request.getParameter("choice")));
		int type_request = (Integer.parseInt(request.getParameter("type")));

		if (student != null) {
			req.setStudent(student);
			req.setTutor(daoint.getTutor(id_request, type_request));
			req.setType(type_request);
			req.setFk_i(id_request);
			req.setStatus("Parzialmente completata");
			int return_val=daoreq.addRequest(req);

			if(return_val == 1) {
				result = 1;
				content = "Richiesta parziale presentata";
				redirect = request.getContextPath() + "/_areaStudent_uvp/uploadAttached.jsp?id_request=" 
						+ daoreq.checkLastPartialRequest(student.getEmail()) + "&new_request=true";
			} else if(return_val == 2) {
				result = 0;
				error = "Hai una richiesta ancora non conclusa";
			} else {
				result = 0;
				error = "Errore nella presentazione della richiesta";
			}
		} else {
			result = 0;
			error = "Si è verificato un errore";
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
