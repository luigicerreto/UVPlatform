package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model_uvp.DAOUser;
import util.Mailer;
import util.PasswordGenerator;

/**
 * 
 * Servlet per la gestione di password dimenticata. Questa fa si che invia una mail
 * all'utente interessato con la nuova password generata e la aggiorni nel database
 * 
 * @author Antonio Baldi
 *
 */
@WebServlet("/resetPassword")
public class resetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public resetPassword() {
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

	@SuppressWarnings({"unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		String mail= request.getParameter("email");
		String subject = "Password autogenerata";
		PasswordGenerator generatorePassword = new PasswordGenerator();
		String nuovaPsw = generatorePassword.generate(12);
		String text = "La tua nuova password è:\n\n"+nuovaPsw+
				"\nAccedi alla tua pagina utente per modificare la password"
				+ "\n http://localhost:8080/UVPlatform/index.jsp ";
		DAOUser queryobj = new DAOUser();
		try
		{
			if(!queryobj.checkMail(mail))
			{
				result=0;
				error = "L'Email inserita non e' registrata";
			}
			else
			{
				if(queryobj.updatePassword(mail, nuovaPsw))
				{
					new Thread(() -> {
						Mailer.send(mail,subject,text,"");  
					}).start();

					result=1;
					content = "Email inviata correttamente";
				}
				else
				{
					result=0;
					error = "Errore nell'aggiornamento della password";
				}
			}
		}
		catch (Exception e)
		{
			result=0;
			error ="Impossibile recuperare i dati";
			e.printStackTrace();
		}

		redirect = request.getContextPath() + "/login.jsp";
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
