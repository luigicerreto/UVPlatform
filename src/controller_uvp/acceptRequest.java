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
 * Servlet implementation class acceptRequest
 */
@WebServlet("/acceptRequest")
public class acceptRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public acceptRequest() {
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

		DAORequest queryobj = new DAORequest();

		Integer id_request = (Integer.parseInt(request.getParameter("id_request")));
		Integer flag = Integer.parseInt(request.getParameter("flag"));

		boolean return_val = false;

		if (flag == 1) {
			return_val = queryobj.acceptRequestByTutor(id_request);
			content = "Richiesta accettata";
		}
		else if (flag == 2) {
			return_val = queryobj.acceptRequestBySecretary(id_request);
			content = "Richiesta inoltrata all'admin";
		}
		else if (flag == 3) {
			return_val = queryobj.acceptRequestByAdmin(id_request);
			content = "Richiesta convalidata e conclusa";
		}

		if (return_val)
			result = 1;
		else
			error = "Errore nell'elaborazione della richiesta";


		JSONObject res = new JSONObject();

		res.put("result", result);
		res.put("error", error);
		res.put("content", content);
		PrintWriter out = response.getWriter();
		out.println(res);
		response.setContentType("json");

	}

}
