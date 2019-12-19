package controller_uvp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfacce.UserInterface;
import util.notifyStudent;

/**
 * 
 * Servlet per l'invio di una notifica allo studente dopo aver effettuato
 * una richiesta di tirocinio.
 * 
 * 
 * @author Antonio Baldi
 *
 */
@WebServlet("/Notify")
public class Notify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ci passo qua dentro 2");
		notifyStudent app = new notifyStudent();
		Integer idRequest = (Integer) request.getSession().getAttribute("idRequest_i");
		UserInterface user = (UserInterface) request.getSession().getAttribute("user");
		app.notify(user.getEmail(), idRequest);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("ci passo qua dentro");
		notifyStudent app = new notifyStudent();
		Integer idRequest = (Integer) request.getSession().getAttribute("idRequest_i");
		UserInterface user = (UserInterface) request.getSession().getAttribute("user");
		app.notify(user.getEmail(), idRequest);
	}

}
