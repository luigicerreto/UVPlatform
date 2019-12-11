package controller_uvp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MailSender;
import util.PasswordGenerator;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassword() {
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
		
		String mail= request.getParameter("email");
		String subject = "Password autogenerata";
		PasswordGenerator generatorePassword = new PasswordGenerator();
		String text = "La tua nuova password è:\n\n"+generatorePassword.generate(32)+
				"\n\n\nAccedi alla tua pagina utente per modificare la password ";
		
		System.out.println("la mail è "+mail);
		log("la mail è "+mail);
		
		MailSender.send(mail, subject, text);
		
		doGet(request, response);
	}

}
