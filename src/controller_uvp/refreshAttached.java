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
import model_uvp.DAORichiesta;
import util.notifyStudent;

/**
 * Servlet implementation class refreshAttached
 */
@WebServlet("/refreshAttached")
public class refreshAttached extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public refreshAttached() {
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
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		DAORichiesta queryobj = new DAORichiesta();
		String addAttach;
		PreparedStatement statement;


		String[] filenames = request.getParameterValues("filenames[]");
		if (filenames.length != 1 || !filenames[0].endsWith(".pdf")) 
		{
			throw new IllegalArgumentException("Valore non corretto");
		}
		Integer idRequest = (Integer) request.getSession().getAttribute("idRequest_i");
		UserInterface user = (UserInterface) request.getSession().getAttribute("user");
		String emailNotify = queryobj.refreshAttachment(filenames[0], idRequest);
		if(emailNotify.equals(null))
		{
			content = "Allegati inseriti con successo.";
			notifyStudent notify = new notifyStudent();
			new Thread(() -> {
				try {
					notify.notify(emailNotify, idRequest);
				} catch (IOException e) {

					e.printStackTrace();
				} catch (ServletException e) {

					e.printStackTrace();
				} 
			}).start();

		}
		else
		{
			error = " Impossibile inserire l'allegato ." + filenames[0];
			result = 0;
		}
		if(queryobj.updateState(idRequest))
		{
			result = 1;
		}
		else
		{
			error += " Impossibile cambiare stato alla richiesta.";
			result = 0;
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

