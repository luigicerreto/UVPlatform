package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.mysql.jdbc.PreparedStatement;

import interfacce.UserInterface;
import model_uvp.DAORequest;
import util.Notifier;

/**
 * Servlet implementation class updateAttached
 */
@WebServlet("/updateAttached")
public class updateAttached extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateAttached() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		DAORequest queryobj = new DAORequest();
		String status = null;

		UserInterface currUser = null;
		if (request.getSession().getAttribute("user") != null)
			currUser = (UserInterface) request.getSession().getAttribute("user");
		
		String[] filenames = request.getParameterValues("filenames[]");
		Integer idRequest = Integer.parseInt(request.getParameter("id_request"));
		String flag = request.getParameter("flag");

		if (filenames.length != 1 || !filenames[0].endsWith(".pdf")) 
		{
			result = 0;
			error = "Quantità o formato degli allegati non valido";
		} else if(currUser != null) {
			String emailNotify = queryobj.updateAttached(filenames[0], idRequest);
			if(emailNotify != null){
				if (flag != null) {
					if (flag.equals("0")) status = "[DOCENTE] Richiesta firmata";
					else if (flag.equals("1"))  status = "[AZIENDA] Richiesta firmata";
					queryobj.setStatus(idRequest, status);
				}

				new Thread(() -> {
					Notifier.notifyStudent(emailNotify, idRequest);
				}).start();

				content = "Allegati inseriti con successo.";
				result = 1;
			}
			else {
				error = "Impossibile inserire l'allegato: " + filenames[0];
				result = 0;
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
