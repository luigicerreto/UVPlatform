package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.DbConnection;

/**
 * Servlet implementation class ServletInternship
 */
@WebServlet("/ServletInternship")
public class ServletInternship extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletInternship() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		PreparedStatement stmt = null;
		Statement stmtSelect = null;
		Statement stmtSelectTwo = null;

		int flag = Integer.parseInt(request.getParameter("flag"));
		Connection conn = new DbConnection().getInstance().getConn();
		String sql = "";

		if (conn != null) {
			if (flag == 0) { // tirocinio interno
				try {
					stmtSelect = conn.createStatement();
					sql = "SELECT r.id_request, r.serial, s.description AS state "
							+ "FROM request r "
							+ "     INNER JOIN state s ON r.fk_state = s.id_state "
							+ "     INNER JOIN user u ON r.fk_user = u.email " + "WHERE u.email = '"
							+ "';";
					ResultSet r = stmtSelect.executeQuery(sql);
					if (r.wasNull()) {
						result *= 0;
						error = "Errore nell'esecuzione della Query";
					} else {
						result = 1;
						int count = r.last() ? r.getRow() : 0;
						if (count > 0) {
							r.beforeFirst();
							String classe = "even";
							while (r.next()) {
								int idRequest = r.getInt("id_request");
								if (classe.equals("odd")) {
									classe = "even";
								} else {
									classe = "odd";
								}
								content += "<tr class='" + classe + "' role='row'>";
								content += "    <td class='text-center'>" + idRequest + "</td>";
								content += "    <td class='text-center'>" + r.getString("serial") + "</td>";
								content += "    <td class='text-center'>";


								stmtSelectTwo = conn.createStatement();
								sql = "SELECT a.filename AS filename "
										+ "FROM attached a "
										+ "WHERE a.fk_request = " + idRequest + ";";
								ResultSet r2 = stmtSelectTwo.executeQuery(sql);
								if (r2.wasNull()) {
									result *= 0;
									error = "Errore nell'esecuzione della Query degli Allegati";
								} else {
									int countAttached = r2.last() ? r2.getRow() : 0;
									int i = 1;
									if (countAttached > 0) {
										r2.beforeFirst();
										while (r2.next()) {
											if (i == countAttached) {
												content += "<a href='" + request.getContextPath() + "/Downloader?filename=" + r2.getString("filename") + "&idRequest=" + idRequest + "'>" + r2.getString("filename") + "</a>";
											} else {
												content += "<a href='" + request.getContextPath() + "/Downloader?filename=" + r2.getString("filename") + "&idRequest=" + idRequest + "'>" + r2.getString("filename") + "</a>" + " - ";
											}                        
											i++;
										}                      
									}
								}

								content += "    </td>";
								content += "    <td class='text-center'>" + r.getString("state") + "</td>";
								content += "</tr>";
							}              
						} else {
							content += "<tr>"
									+ "<td class=\"text-center\"" + "></td>"
									+ "<td class=\"text-center\"" + "></td>"
									+ "<td class=\"text-center\"" + ">Nessuna Richiesta Presente</td>"
									+ "<td class=\"text-center\"" + "></td>"
									+ "</tr>";
						}
					}
				} catch (Exception e) {
					result *= 0;
					error += e.getMessage();
				}
			} else if (flag == 1) { // tirocinio esterno

			}
		} else {
			error += "Nessuna connessione al database.";
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
