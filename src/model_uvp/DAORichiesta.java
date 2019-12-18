package model_uvp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import controller.DbConnection;
import controller.Utils;
import model.Attached;
import model.SystemAttribute;


public class DAORichiesta {
	/**
	 * Questa funzione consente di recuperare dal database tutte le informazioni relative
	 * a tutte le richieste di un dato utente
	 * 
	 * @author Antonio Baldi
	 * @param email
	 */
	public static ArrayList<RequestInternship> viewRequests(String email) throws SQLException 
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
				request1.setUser1(result.getString(2));
				request1.setAttached(attacheds);
				request1.setType(result.getString(3));
				request1.setState(result.getString(4));
				//System.out.println("Gli allegati sono "+attacheds.get(0).getFilename()+ "    "+attacheds.get(1).getFilename());
				requests.add(request1);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return requests;

	}




	public static int addRequest(RequestInternship richiesta)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String checkRequest;
		int messageResult = 0;

		try {
			checkRequest = "SELECT id_request_i\r\n" + 
					"FROM request_internship WHERE FK_USER1 = ? AND STATE != ? AND STATE != ? ";
			statement = con.prepareStatement(checkRequest);
			statement.setString(1, richiesta.getUser1());
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
					statement.setString(3, richiesta.getUser1());
					statement.setString(4, richiesta.getUser2());
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
			//todo error
			e.printStackTrace();
		}
		return messageResult;


	}


	public static InternalInternship retriveInternship_internal(int id)
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

	public static ExternalInternship retriveInternship_external(int id)
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
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return internship;

	}

	public static String ExternalPerform(int id_request)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String email_azienda = null;
		ExternalInternship internship = null;
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

	public static String InternalPerform(int id_request)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String email_docente = null;
		ExternalInternship internship = null;
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


	public static int CheckLastPartialRequest(String email)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		int id_request;
		String email_docente = null;
		ExternalInternship internship = null;
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
	
	public static boolean addAttachment(String Filename, String mail, int idRequest)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String addAttach;
		
		addAttach = "INSERT INTO attached (FILENAME, FK_USER, FK_REQUEST,I) VALUES (?, ?, ?) ";
		try {
			statement = con.prepareStatement(addAttach);
			statement.setString(1, Filename);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	public static boolean updateState(int idRequest)
	{
		final String newState = "In attesa di accettazione";
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String updateS = "UPDATE request_internship\r\n" + 
				"SET STATE = ?\r\n" + 
				"WHERE id_request_i = ?;";
		try {
			statement = con.prepareStatement(updateS);
			statement.setString(1, newState);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
