package model_uvp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import controller.DbConnection;
import model.Attached;

/**
 * 
 * @author Antonio Baldi
 * @author Carmine
 *
 */
@SuppressWarnings("static-access")
public class DAORequest {
	/**
	 * Questa funzione consente di recuperare dal database tutte le informazioni relative
	 * a tutte le richieste di un utente dato l'email.
	 * 
	 * @author Antonio Baldi
	 * @param email
	 * @return ArrayList<RequestInternship>
	 */
	public ArrayList<RequestInternship> viewRequests(String email) throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		ResultSet resultAttached;
		ResultSet result;
		RequestInternship request;
		Attached a;
		ArrayList<RequestInternship> requests = new ArrayList<>();
		ArrayList<Attached> attached;

		String sql = "SELECT * FROM REQUEST_INTERNSHIP WHERE FK_USER1 = ?";

		String sql1="SELECT FILENAME FROM ATTACHED WHERE FK_REQUEST_I = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			result = statement.executeQuery();

			while(result.next()){
				request = new RequestInternship();
				attached = new ArrayList<>();
				
				statement=con.prepareStatement(sql1);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				if(resultAttached.wasNull()) {
					attached = null;
				} else {
					while(resultAttached.next()) {
						a = new Attached();
						a.setFilename(resultAttached.getString(1));
						attached.add(a);
					}
				}
				request.setId_request_i(result.getInt(1));
				request.setType(result.getInt(2));
				request.setStatus(result.getString(3));
				request.setStudent(new DAOUser().getUser(result.getString(4)));
				request.setTutor(new DAOUser().getUser(result.getString(5)));

				if (request.getType() == 0)
					request.setFk_i(result.getInt(7));
				else if (request.getType() == 1)
					request.setFk_i(result.getInt(6));

				request.setAttached(attached);
				requests.add(request);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return requests;
	}

	/**
	 * Questa funzione permette di recuperare gli allegati di una richiesta dato l'id.
	 * 
	 * @param idRequest
	 * @return Attached
	 */
	public Attached retrieveLatestAttached(int idRequest) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		Attached a = null;
		String sql="SELECT FILENAME FROM ATTACHED WHERE FK_REQUEST_I = ? ORDER BY ID_ATTACHED DESC";

		try {
			statement = con.prepareStatement(sql);
			a = new Attached();
			statement.setInt(1, idRequest);
			resultAttached = statement.executeQuery();

			if(resultAttached.next()){
				a = new Attached();
				a.setFilename(resultAttached.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * 
	 * Questa funzione permette di aggiungere una richiesta al database
	 * data una determinata richiesta come parametro.
	 * 
	 * @param req
	 * @return int
	 */
	public int addRequest(RequestInternship req) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String sql = "SELECT ID_REQUEST_I FROM REQUEST_INTERNSHIP WHERE FK_USER1 = ? AND STATE NOT LIKE '%CONCLUSA%'";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, req.getStudent().getEmail());
			ResultSet r = statement.executeQuery();
			int count = r.last() ? r.getRow() : 0;
			if (count == 0){

				String addRequest = "INSERT INTO REQUEST_INTERNSHIP (TYPE, STATE, FK_USER1, FK_USER2, FK_II, FK_IE) VALUES (?, ?, ?, ?, ?, ?)";
				try {
					statement = con.prepareStatement(addRequest);
					statement.setInt(1, req.getType());
					statement.setString(2, req.getStatus());
					statement.setString(3, req.getStudent().getEmail());
					statement.setString(4, req.getTutor().getEmail());

					if(req.getType() == 0) {
						statement.setInt(5, req.getFk_i());
						statement.setNull(6, java.sql.Types.NULL);
					}
					else if (req.getType() == 1) {
						statement.setNull(5, java.sql.Types.NULL);
						statement.setInt(6, req.getFk_i());
					}
					if(statement.executeUpdate()>0) {
						con.commit();
						return 1;
					} else {
						con.rollback();
						return 0;
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			} else {
				return 2;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Questa funzione restituisce l'id dell'ultima richiesta in stato
	 * "Parzialmente completata" di un dato utente tramite il parametro email
	 * 
	 * @param email
	 * @return int
	 */
	public int checkLastPartialRequest(String email) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		int id_request;
		String sql = "SELECT ID_REQUEST_I FROM REQUEST_INTERNSHIP WHERE FK_USER1 = ? AND STATE = \"Parzialmente completata\";";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			result = statement.executeQuery();
			if(result.next()) {
				id_request=result.getInt(1);
				return id_request;
			}
			else
				return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Questa funzione restituisce lo Stato di una richiesta dato l'id di questa
	 * 
	 * @param idRequest
	 * @return String
	 */
	public String getStatus(int idRequest) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String sql = "SELECT STATE FROM REQUEST_INTERNSHIP WHERE ID_REQUEST_I = ?";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, idRequest);
			result = statement.executeQuery();
			if(result.next())
				return result.getString(1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Questa funzione permette di cambiare lo stato di una richiesta
	 * 
	 * @param idRequest
	 * @param newStatus
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */
	public boolean setStatus(int idRequest, String newStatus) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String sql = "UPDATE REQUEST_INTERNSHIP SET STATE = ? WHERE ID_REQUEST_I = ?;";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, newStatus);
			statement.setInt(2, idRequest);
			if(statement.executeUpdate()>0) {
				con.commit();
				return true;
			} else {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Questa funzione permette di aggiungere un allegato ad una richiesta.
	 * 
	 * 
	 * @param filename
	 * @param mail
	 * @param idRequest
	 * @return true se l'allegato viene aggiunto correttamente, false altrimenti
	 */
	public boolean addAttached(String filename, String mail, int idRequest) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String sql = "INSERT INTO ATTACHED (FILENAME, FK_USER, FK_REQUEST_I) VALUES (?, ?, ?)";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, filename);
			statement.setString(2, mail);
			statement.setInt(3, idRequest);
			if(statement.executeUpdate()>0) {
				con.commit();
				return true;
			} else {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Questa funzione permette di aggiornare gli allegati di una.
	 * 
	 * 
	 * @param filename
	 * @param idRequest
	 * @return email dell'utente se gli allegati vengono aggiornati, null altrimenti
	 */
	public String updateAttached(String filename, int idRequest) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String sql = "UPDATE ATTACHED SET FILENAME = ? WHERE FK_REQUEST_I = ? ORDER BY ID_ATTACHED LIMIT 1";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, filename);
			statement.setInt(2, idRequest);
			if(statement.executeUpdate()>0) {
				con.commit();
				return this.getEmailByRequest(idRequest);
			} else {
				con.rollback();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Questa funzione permette di ottenere gli allegati di una richiesta
	 * 
	 * 
	 * @param filename
	 * @param idRequest
	 * @return email dell'utente se gli allegati vengono aggiornati, null altrimenti
	 */
	public List<String> retrieveAttached(int idRequest) {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		List<String> filenames = new ArrayList<>();
		String sql = "SELECT FILENAME FROM ATTACHED WHERE FK_REQUEST_I = ?";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, idRequest);
			result = statement.executeQuery();
			while(result.next()) {
				filenames.add(result.getString(1));
			}
			return filenames;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RequestInternship> viewRequestsTeacher(String email) throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;
		RequestInternship request;
		Attached a;
		ArrayList<RequestInternship> requests = new ArrayList<>();
		ArrayList<Attached> attached;

		String sql = "SELECT R.* "
				+ "FROM REQUEST_INTERNSHIP R "
				+ "INNER JOIN INTERNSHIP_I AS I ON R.FK_II =  I.ID_II "
				+ "WHERE R.FK_USER2 = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			result = statement.executeQuery();

			String sql1="SELECT FILENAME FROM ATTACHED WHERE FK_REQUEST_I = ?";

			while(result.next()) {
				request = new RequestInternship();
				attached = new ArrayList<>();
				
				statement=con.prepareStatement(sql1);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				if(resultAttached.wasNull()) {
					attached = null;
				} 
				else 
				{
					while(resultAttached.next()) {
						a = new Attached();
						a.setFilename(resultAttached.getString(1));
						attached.add(a);
					}
				}
				request.setId_request_i(result.getInt(1));
				request.setType(result.getInt(2));
				request.setStatus(result.getString(3));
				request.setStudent(new DAOUser().getUser(result.getString(4)));
				request.setTutor(new DAOUser().getUser(result.getString(5)));

				if (request.getType() == 0)
					request.setFk_i(result.getInt(7));
				else if (request.getType() == 1)
					request.setFk_i(result.getInt(6));

				request.setInternship((InternalInternship) new DAOInternship().getInternship(request.getFk_i(), request.getType()));
				request.setAttached(attached);
				requests.add(request);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return requests;
	}

	public ArrayList<RequestInternship> viewRequestsCompany(String email) throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;
		RequestInternship request;
		Attached a;
		ArrayList<RequestInternship> requests = new ArrayList<>();
		ArrayList<Attached> attached;
		
		String sql = "SELECT R.* "
				+ "FROM REQUEST_INTERNSHIP R "
				+ "INNER JOIN INTERNSHIP_E E ON R.FK_IE = E.ID_IE "
				+ "WHERE R.FK_USER2 = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			result = statement.executeQuery();

			String sql1="SELECT FILENAME FROM ATTACHED WHERE FK_REQUEST_I = ?";

			while(result.next()) {
				request = new RequestInternship();
				attached = new ArrayList<>();
				
				statement=con.prepareStatement(sql1);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				if(resultAttached.wasNull()) {
					attached = null;
				} else {
					while(resultAttached.next()) {
						a = new Attached();
						a.setFilename(resultAttached.getString(1));
						attached.add(a);
					}
				}
				request.setId_request_i(result.getInt(1));
				request.setType(result.getInt(2));
				request.setStatus(result.getString(3));
				request.setStudent(new DAOUser().getUser(result.getString(4)));
				request.setTutor(new DAOUser().getUser(result.getString(5)));

				if (request.getType() == 0)
					request.setFk_i(result.getInt(7));
				else if (request.getType() == 1)
					request.setFk_i(result.getInt(6));

				request.setInternship((ExternalInternship) new DAOInternship().getInternship(request.getFk_i(), request.getType()));
				request.setAttached(attached);
				requests.add(request);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return requests;
	}

	public ArrayList<RequestInternship> viewAllRequests() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;
		RequestInternship request;
		Attached a;
		ArrayList<RequestInternship> requests = new ArrayList<>();
		ArrayList<Attached> attached;

		String sql = "(SELECT R.* "
				+ "FROM REQUEST_INTERNSHIP R " 
				+ "INNER JOIN INTERNSHIP_I I ON R.FK_II = I.ID_II)"
				+ "UNION"
				+ "(SELECT R.* "
				+ "FROM REQUEST_INTERNSHIP R " 
				+ "INNER JOIN INTERNSHIP_E E ON R.FK_IE = E.ID_IE)";

		try {
			statement = con.prepareStatement(sql);
			result = statement.executeQuery();

			String sql1="SELECT FILENAME FROM ATTACHED WHERE FK_REQUEST_I = ?";

			while(result.next()) {
				request = new RequestInternship();
				attached = new ArrayList<>();
				
				statement=con.prepareStatement(sql1);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				if(resultAttached.wasNull()) {
					attached = null;
				} else {
					while(resultAttached.next()) {
						a = new Attached();
						a.setFilename(resultAttached.getString(1));
						attached.add(a);
					}
				}
				request.setId_request_i(result.getInt(1));
				request.setType(result.getInt(2));
				request.setStatus(result.getString(3));
				request.setStudent(new DAOUser().getUser(result.getString(4)));
				request.setTutor(new DAOUser().getUser(result.getString(5)));

				if (request.getType() == 0) {
					request.setFk_i(result.getInt(7));
					request.setInternship((InternalInternship) new DAOInternship().getInternship(request.getFk_i(), request.getType()));
				}
				else if (request.getType() == 1) {
					request.setFk_i(result.getInt(6));
					request.setInternship((ExternalInternship) new DAOInternship().getInternship(request.getFk_i(), request.getType()));
				}

				request.setAttached(attached);
				requests.add(request);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return requests;
	}

	/**
	 * Questa funzione permette a docente ed azienda di accettare una richiesta
	 * @param idRequest
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */ 
	public boolean acceptRequestByTutor(int idRequest)
	{
		final String newStatus = "In attesa di caricamento Registro di Tirocinio";
		return this.setStatus(idRequest, newStatus);
	}

	/**
	 * Questa funzione permette alla segreteria di accettare una richiesta
	 * @param idRequest
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */
	public boolean acceptRequestBySecretary(int idRequest)
	{
		final String newStatus = "[ADMIN] In attesa di accettazione";
		return this.setStatus(idRequest, newStatus);
	}

	/**
	 * Questa funzione permette all'admin di accettare una richiesta
	 * @param idRequest
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */
	public boolean acceptRequestByAdmin(int idRequest)
	{
		final String newStatus = "[CONCLUSA] Convalidata";
		return this.setStatus(idRequest, newStatus);
	}

	/**
	 * Questa funzione permette a docente, azienda, segreteria ed admin di rifiutare una richiesta
	 * @param idRequest
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */
	public boolean rejectRequest(int idRequest)
	{
		final String newStatus = "[CONCLUSA] Non convalidata";
		return this.setStatus(idRequest, newStatus);
	}

	/**
	 * Questa funzione permette di ottenere l'email dell'utente che ha effettuato la richiesta
	 * @param idRequest
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */
	public String getEmailByRequest(int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String sql = "SELECT FK_USER FROM attached WHERE FK_REQUEST_I = ? ";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, idRequest);
			result = statement.executeQuery();

			int size = result.last() ? result.getRow() : 0;

			if(size>0)
				return result.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Questa funzione permette di ottenere il tipo di una richiesta
	 * @param idRequest
	 * @return
	 */
	public RequestInternship getRequest(int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		ResultSet resultAttached;
		Attached a;
		List<Attached> attached;
		RequestInternship request = null;
		
		String sql = "SELECT * FROM REQUEST_INTERNSHIP WHERE ID_REQUEST_I = ?";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, idRequest);
			result = statement.executeQuery();

			int size = result.last() ? result.getRow() : 0;

			if(size>0) {
				String sql1="SELECT FILENAME FROM ATTACHED WHERE FK_REQUEST_I = ?";

				request = new RequestInternship();
				attached = new ArrayList<>();
				statement=con.prepareStatement(sql1);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				if(resultAttached.wasNull()) {
					attached = null;
				} else {
					while(resultAttached.next()) {
						a = new Attached();
						a.setFilename(resultAttached.getString(1));
						attached.add(a);
					}
				}
				request = new RequestInternship();
				request.setId_request_i(result.getInt(1));
				request.setType(result.getInt(2));
				request.setStatus(result.getString(3));
				request.setStudent(new DAOUser().getUser(result.getString(4)));
				request.setTutor(new DAOUser().getUser(result.getString(5)));

				if (request.getType() == 0) {
					request.setFk_i(result.getInt(7));
					request.setInternship((InternalInternship) new DAOInternship().getInternship(request.getFk_i(), request.getType()));
				}
				else if (request.getType() == 1) {
					request.setFk_i(result.getInt(6));
					request.setInternship((ExternalInternship) new DAOInternship().getInternship(request.getFk_i(), request.getType()));
				}
				request.setAttached(attached);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}
}
