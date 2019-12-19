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
import model.SystemAttribute;
import model_uvp.DAORichiesta;
import util.notifyStudent;

/**
 * 
 * Servlet per gestire l'aggiunta degli allegati ad una determinata richiesta.
 * Questa servlet richiede "filenames[]" come parametro della request
 * e l'identificativo della richiesta come parametro di sessione.
 * 
 * 
 * @author Antonio Baldi
 *
 */
@WebServlet("/addAttached")
public class addAttached extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addAttached() {
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
		System.out.println("l'id nella servlet allegati è "+idRequest);

		if(queryobj.addAttachment(filenames[0], user.getEmail(), idRequest))
		{
			content = "Allegati inseriti con successo.";
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
