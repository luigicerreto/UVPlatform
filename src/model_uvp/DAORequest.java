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
	public ArrayList<RequestInternship> viewRequests(String email) throws SQLException 
	{
		ArrayList<RequestInternship> requests = new ArrayList<RequestInternship>();
		RequestInternship request1 = null;
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;

		String viewRequest = "Select richiesta.id_request_i, user.SERIAL, richiesta.type, richiesta.STATE\r\n" + 
				"				from request_internship as richiesta\r\n" + 
				"				inner join user on richiesta.FK_USER1 = user.EMAIL \r\n" + 
				"				where richiesta.FK_USER1 = ?";
		try 
		{

			statement = con.prepareStatement(viewRequest);
			statement.setString(1, email);
			result = statement.executeQuery();
			String attachedQuery="SELECT a.filename AS filename \r\n" + 
					"FROM attached a \r\n" + 
					"WHERE a.fk_request_I = ?";
			List<Attached> attacheds;
			Attached attac;
			while(result.next())
			{

				request1 = new RequestInternship();
				statement=con.prepareStatement(attachedQuery);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				attacheds = new ArrayList<Attached>();
				if(resultAttached.wasNull())
				{
					attacheds = null;
				}
				else
				{
					while(resultAttached.next())
					{
						attac = new Attached();
						attac.setFilename(resultAttached.getString(1));
						attacheds.add(attac);
					}
				}
				request1.setId_request_i(result.getInt(1));
				request1.setTheme(result.getString(2));
				request1.setAttached(attacheds);
				request1.setType(result.getString(3));
				request1.setState(result.getString(4));
				requests.add(request1);
			}

		}
		catch(Exception e)
		{
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
	public Attached retriveAttached(int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		Attached attac = null;
		String attachedQuery="SELECT a.filename AS filename \r\n" + 
				"FROM attached a \r\n" + 
				"WHERE a.fk_request_I = ?";
		try {
			statement = con.prepareStatement(attachedQuery);
			attac = new Attached();
			statement.setInt(1, idRequest);
			resultAttached = statement.executeQuery();

			if(resultAttached.next())
			{
				attac = new Attached();
				attac.setFilename(resultAttached.getString(1));
			}
			return attac;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attac;
	}

	/**
	 * 
	 * Questa funzione permette di aggiungere una richiesta al database
	 * data una determinata richiesta come parametro.
	 * 
	 * @param richiesta
	 * @return int
	 */
	public int addRequest(RequestInternship richiesta)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String checkRequest;
		int messageResult = 0;

		try {
			checkRequest = "SELECT id_request_i\r\n" + 
					"FROM request_internship WHERE FK_USER1 = ? AND STATE != ? AND STATE != ? ";
			statement = con.prepareStatement(checkRequest);
			statement.setString(1, richiesta.getTheme());
			statement.setString(2, "Accettata");
			statement.setString(3, "Rifiutata");
			ResultSet r = statement.executeQuery();
			int count = r.last() ? r.getRow() : 0;
			if (count == 0) 
			{

				String addRequest = "INSERT INTO `uvplatform`.`request_internship` (`type`, `STATE`, `FK_USER1`, `FK_USER2`, `FK_II`, `FK_IE`) VALUES \r\n" + 
						"(?, ?, ?, ?, ?, ?)";
				try
				{
					statement = con.prepareStatement(addRequest);
					statement.setString(1, richiesta.getType());
					statement.setString(2, richiesta.getState());
					statement.setString(3, richiesta.getTheme());
					statement.setString(4, richiesta.getUserFullName());
					if(richiesta.getId_ie()>0)
					{
						statement.setNull(5, java.sql.Types.INTEGER);
						statement.setInt(6, richiesta.getId_ie());
					}
					else
					{
						statement.setInt(5, richiesta.getId_ii());
						statement.setNull(6, java.sql.Types.INTEGER);
					}
					if(statement.executeUpdate()>0)
					{
						con.commit();
						messageResult = 1;
						return messageResult;
					}
					else
					{
						con.rollback();
						messageResult = 0;
						return messageResult;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

			}
			else
			{
				messageResult = 2; 
				return messageResult;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return messageResult;
	}

	/**
	 * 
	 * Questa funzione permette di recuperare tutte le informazioni relative
	 * ad un tirocinio interno dato l'id di questo.
	 * 
	 * @param id
	 * @return InternalIntership
	 */
	public InternalInternship retriveInternship_internal(int id)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		InternalInternship internship = new InternalInternship();
		String retriveInternship = "SELECT * FROM uvplatform.internship_i\r\n" + 
				"Where id_ii =?;";
		try {
			statement = con.prepareStatement(retriveInternship);
			statement.setInt(1, id);
			result = statement.executeQuery();
			if(result.next())
			{
				internship.setId_ii(result.getInt(1));
				internship.setTutorn_name(result.getString(2));
				internship.setTheme(result.getString(3));
				internship.setAvailability(result.getInt(4));
				internship.setResources(result.getString(5));
				internship.setGoals(result.getString(6));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return internship;

	}

	/**
	 * Questa funzione permette di recuperare tutte le informazioni relative
	 * ad un tirocinio esterno dato l'id di questo.
	 * 
	 * @param id
	 * @return ExternalInternship
	 */
	public ExternalInternship retriveInternship_external(int id)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		ExternalInternship internship = new ExternalInternship();
		String retriveInternship = "SELECT * FROM uvplatform.internship_e\r\n" + 
				"Where id_ie =?;";
		try {
			statement = con.prepareStatement(retriveInternship);
			statement.setInt(1, id);
			result = statement.executeQuery();
			if(result.next())
			{
				internship.setId_ie(result.getInt(1));
				internship.setName(result.getString(2));
				internship.setDuration_convention(result.getInt(3));
				internship.setDate_convention(result.getDate(4));
				internship.setAvailability(result.getInt(5));
				internship.setInfo(result.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return internship;
	}
	/**
	 * 
	 * Questa funzione restituisce la mail dell'azienda dato l'id del
	 * tirocinio esterno.
	 * 
	 * 
	 * @param id_request
	 * @return String
	 */
	public String ExternalPerform(int id_request)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String email_azienda = null;
		String retriveInternship = "SELECT FK_USER FROM uvplatform.do\r\n" + 
				"WHERE id_ie = ?";
		try {
			statement = con.prepareStatement(retriveInternship);
			statement.setInt(1, id_request);
			result = statement.executeQuery();
			if(result.next())
			{
				email_azienda = result.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return email_azienda;
	}

	/**
	 * Questa funzione restituisce la mail del docente dato l'id del
	 * tirocinio interno.
	 * 
	 * @param id_request
	 * @return String
	 */
	public String InternalPerform(int id_request)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String email_docente = null;
		String retriveInternship = "SELECT FK_USER FROM uvplatform.perform\r\n" + 
				"WHERE id_ii = ?";
		try {
			statement = con.prepareStatement(retriveInternship);
			statement.setInt(1, id_request);
			result = statement.executeQuery();
			if(result.next())
			{
				email_docente = result.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return email_docente;
	}


	/**
	 * Questa funzione restituisce l'id dell'ultima richiesta in stato
	 * "Parzialmente completata" di un dato utente tramite il parametro email
	 * 
	 * 
	 * @param email
	 * @return int
	 */
	public int checkLastPartialRequest(String email)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		int id_request;
		String retriveLR = "SELECT id_request_i FROM request_internship\r\n" + 
				"WHERE FK_USER1 = ? AND STATE = \"Parzialmente Completata\";";
		try
		{
			statement = con.prepareStatement(retriveLR);
			statement.setString(1, email);
			result = statement.executeQuery();
			if(result.next())
			{
				id_request=result.getInt(1);
				return id_request;
			}
			else
				return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * Questa funzione restituisce lo Stato di una richiesta dato l'id di questa.
	 * 
	 * @param idRequest
	 * @return String
	 */
	public String checkStatus(int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String state="";
		String retriveLR = "SELECT STATE FROM request_internship\r\n" + 
				"WHERE id_request_i = ?";
		try
		{
			statement = con.prepareStatement(retriveLR);
			statement.setInt(1, idRequest);
			result = statement.executeQuery();
			if(result.next())
			{
				state=result.getString(1);
				return state;
			}
			else
				return state;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return state;
	}

	/**
	 * 
	 * Questa funzione permette di aggiungere un allegato ad una richiesta.
	 * 
	 * 
	 * @param filename
	 * @param mail
	 * @param idRequest
	 * @return true se l'allegato viene aggiunto correttamente, false altrimenti
	 */
	public boolean addAttached(String filename, String mail, int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addAttach;

		addAttach = "INSERT INTO attached (FILENAME, FK_USER, FK_REQUEST_I) VALUES (?, ?, ?) ";
		try {
			statement = con.prepareStatement(addAttach);
			statement.setString(1, filename);
			statement.setString(2, mail);
			statement.setInt(3, idRequest);
			if(statement.executeUpdate()>0)
			{
				con.commit();
				return true;
			}
			else
			{
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * Questa funzione permette di aggiornare gli allegati di una.
	 * 
	 * 
	 * @param filename
	 * @param idRequest
	 * @return email dell'utente se gli allegati vengono aggiornati, null altrimenti
	 */
	public String updateAttached(String filename, int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addAttach;

		addAttach = "UPDATE attached \r\n" + 
				"SET FILENAME = ? \r\n" + 
				"WHERE FK_REQUEST_I = ?";
		try {
			statement = con.prepareStatement(addAttach);
			statement.setString(1, filename);
			statement.setInt(2, idRequest);
			if(statement.executeUpdate()>0)
			{
				con.commit();
				return this.getEmailByRequest(idRequest);
			}
			else
			{
				con.rollback();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RequestInternship> viewRequestsTeacher(String email) throws SQLException 
	{
		ArrayList<RequestInternship> requests = new ArrayList<RequestInternship>();
		RequestInternship request1 = null;
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;

		String viewRequest = "Select richiesta.id_request_i, i.theme, user.NAME, user.SURNAME, richiesta.type, richiesta.STATE\r\n" + 
				"				from request_internship as richiesta\r\n" + 
				"				inner join user on richiesta.FK_USER1 = user.EMAIL inner join internship_i as i on richiesta.FK_II =  i.id_ii \r\n" + 
				"				where richiesta.FK_USER2 = ?";
		try 
		{

			statement = con.prepareStatement(viewRequest);
			statement.setString(1, email);
			result = statement.executeQuery();
			String attachedQuery="SELECT a.filename AS filename \r\n" + 
					"FROM attached a \r\n" + 
					"WHERE a.fk_request_I = ?";
			List<Attached> attacheds;
			Attached attac;
			while(result.next())
			{

				request1 = new RequestInternship();
				statement=con.prepareStatement(attachedQuery);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				attacheds = new ArrayList<Attached>();
				if(resultAttached.wasNull())
				{
					attacheds = null;
				}
				else
				{
					while(resultAttached.next())
					{
						attac = new Attached();
						attac.setFilename(resultAttached.getString(1));
						attacheds.add(attac);
					}
				}
				request1.setId_request_i(result.getInt(1));
				request1.setTheme(result.getString(2));
				request1.setAttached(attacheds);
				request1.setUserFullName(result.getString(3)+"+"+result.getString(4));
				request1.setType(result.getString(5));
				request1.setState(result.getString(6));
				requests.add(request1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return requests;
	}


	public ArrayList<RequestInternship> viewRequestsCompany(String email) throws SQLException 
	{
		ArrayList<RequestInternship> requests = new ArrayList<RequestInternship>();
		RequestInternship request1 = null;
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;

		String viewRequest = "Select richiesta.id_request_i, e.name, user.NAME, user.SURNAME, richiesta.type, richiesta.STATE\r\n" + 
				"				from request_internship as richiesta\r\n" + 
				"				inner join user on richiesta.FK_USER1 = user.EMAIL inner join internship_e as e on richiesta.FK_IE =  e.id_ie \r\n" + 
				"				where richiesta.FK_USER2 = ?";
		try 
		{

			statement = con.prepareStatement(viewRequest);
			statement.setString(1, email);
			result = statement.executeQuery();
			String attachedQuery="SELECT a.filename AS filename \r\n" + 
					"FROM attached a \r\n" + 
					"WHERE a.fk_request_I = ?";
			List<Attached> attacheds;
			Attached attac;
			while(result.next())
			{

				request1 = new RequestInternship();
				statement=con.prepareStatement(attachedQuery);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				attacheds = new ArrayList<Attached>();
				if(resultAttached.wasNull())
				{
					attacheds = null;
				}
				else
				{
					while(resultAttached.next())
					{
						attac = new Attached();
						attac.setFilename(resultAttached.getString(1));
						attacheds.add(attac);
					}
				}
				request1.setId_request_i(result.getInt(1));
				request1.setTheme(result.getString(2));
				request1.setAttached(attacheds);
				request1.setUserFullName(result.getString(3)+"+"+result.getString(4));
				request1.setType(result.getString(5));
				request1.setState(result.getString(6));
				requests.add(request1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return requests;
	}

	public ArrayList<RequestInternship> viewRequestsSecretary() throws SQLException 
	{
		ArrayList<RequestInternship> requests = new ArrayList<RequestInternship>();
		RequestInternship request1 = null;
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;

		String viewRequest = "(select richiesta.id_request_i, i.theme, user.NAME, user.SURNAME, richiesta.type, richiesta.STATE\r\n"
				+ "from request_internship as richiesta\r\n" 
				+ "inner join user on richiesta.FK_USER1 = user.EMAIL "
				+ "inner join internship_i as i on richiesta.FK_II =  i.id_ii)"
				+ "UNION"
				+ "(select richiesta.id_request_i, null, user.NAME, user.SURNAME, richiesta.type, richiesta.STATE\r\n"
				+ "from request_internship as richiesta\r\n" 
				+ "inner join user on richiesta.FK_USER1 = user.EMAIL "
				+ "inner join internship_e as e on richiesta.FK_IE =  e.id_ie)";
		try 
		{

			statement = con.prepareStatement(viewRequest);
			result = statement.executeQuery();
			String attachedQuery="SELECT a.filename AS filename \r\n" + 
					"FROM attached a \r\n" + 
					"WHERE a.fk_request_I = ?";
			List<Attached> attacheds;
			Attached attac;
			while(result.next())
			{

				request1 = new RequestInternship();
				statement=con.prepareStatement(attachedQuery);
				statement.setInt(1, result.getInt(1));
				resultAttached = statement.executeQuery();
				attacheds = new ArrayList<Attached>();
				if(resultAttached.wasNull())
				{
					attacheds = null;
				}
				else
				{
					while(resultAttached.next())
					{
						attac = new Attached();
						attac.setFilename(resultAttached.getString(1));
						attacheds.add(attac);
					}
				}
				request1.setId_request_i(result.getInt(1));
				request1.setTheme(result.getString(2));
				request1.setAttached(attacheds);
				request1.setUserFullName(result.getString(3)+"+"+result.getString(4));
				request1.setType(result.getString(5));
				request1.setState(result.getString(6));
				requests.add(request1);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return requests;

	}

	/**
	 * Questa funzione permette di cambiare lo stato di una richiesta
	 * 
	 * @param idRequest
	 * @param newStatus
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */
	public boolean setStatus(int idRequest, String newStatus)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String updateS = "UPDATE request_internship\r\n" + 
				"SET STATE = ?\r\n" + 
				"WHERE id_request_i = ?;";
		try {
			statement = con.prepareStatement(updateS);
			statement.setString(1, newStatus);
			statement.setInt(2, idRequest);
			if(statement.executeUpdate()>0)
			{
				con.commit();
				return true;
			}
			else
			{
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Questa funzione permette a docente ed azienda di accettare una richiesta
	 * @param idRequest
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */ 
	public boolean acceptRequestByProf_Company(int idRequest)
	{
		final String newStatus = "[SEGRETERIA] In attesa di accettazione";
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
		final String newStatus = "[CONCLUSA] Accettata";
		return this.setStatus(idRequest, newStatus);
	}

	/**
	 * Questa funzione permette a docente, azienda, segreteria ed admin di rifiutare una richiesta
	 * @param idRequest
	 * @return true se lo stato della richiesta viene cambiato, false altrimenti
	 */
	public boolean rejectRequest(int idRequest)
	{
		final String newStatus = "[CONCLUSA] Rifiutata";
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
	public String getRequestTypeById(int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String sql = "select type from request_internship where id_request_i = ?";

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
}
