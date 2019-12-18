package controller_uvp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

import interfacce.UserInterface;
import model.SystemAttribute;

/**
 * Servlet implementation class addAttached
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

		String addAttach;
		PreparedStatement statement;


		String[] filenames = request.getParameterValues("filenames[]");
		if (filenames.length != 1 
				|| !filenames[0].endsWith(".pdf")) 
				{
			throw new IllegalArgumentException("Valore non corretto");
				}
		Integer idRequest = (Integer) request.getSession().getAttribute("idRequest_i");
		UserInterface user = (UserInterface) request.getSession().getAttribute("user");
		System.out.println("l'id nella servlet allegati è "+idRequest);
		result = 1;
		//TODO
		/*
			try {
				for (int i = 0; i < filenames.length; i++) {
					addAttach = " INSERT INTO attached " + " (filename, fk_request, fk_user) " + " VALUES "
							+ " (?, ?, ?) ";
					statement = conn.prepareStatement(addAttach);
					statement.setString(1, filenames[i]);
					statement.setInt(2, idRequest);
					statement.setString(3, user.getEmail());
					if (statement.executeUpdate() > 0) {
						result *= 1;
					} else {
						error += " Impossibile inserire l'allegato ." + filenames[i];
						result *= 0;
					}
				}

				if (result == 1) {
					Integer newState =
							Integer.parseInt(new SystemAttribute().getValueByKey("request-working-secretary"));
					addAttach = " UPDATE request SET fk_state = ? WHERE id_request = ?; ";
					statement = conn.prepareStatement(addAttach);
					statement.setInt(1, newState);
					statement.setInt(2, idRequest);
					if (statement.executeUpdate() > 0) {
						result = 1;
						redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
						content = "Allegati inseriti con successo.";
					} else {
						error += " Impossibile cambiare stato alla richiesta.";
						result = 0;
					}
				}

				if (result == 0) {
					conn.rollback();
					result *= 0;
				} else {
					conn.commit();
				}

			} catch (Exception e) {
				error += e.getMessage();
				result *= 0;
			}
		 */
	}

}
