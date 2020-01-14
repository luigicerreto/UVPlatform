package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model_uvp.DAORequest;

/**
 * Servlet implementation class rejectRequest
 */
@WebServlet("/rejectRequest")
public class rejectRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public rejectRequest() {
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

		int id_request = (Integer.parseInt(request.getParameter("id_request")));
		DAORequest queryobj = new DAORequest();

		if(queryobj.rejectRequest(id_request)){
			result = 1;
			content = "Richiesta non convalidata e conclusa";
		} else {
			error = "Errore nell'elaborazione della richiesta";
		}

		JSONObject res = new JSONObject();
		
		res.put("result", result);
		res.put("error", error);
		res.put("content", content);
		PrintWriter out = response.getWriter();
		out.println(res);
		response.setContentType("json");
	}

}
