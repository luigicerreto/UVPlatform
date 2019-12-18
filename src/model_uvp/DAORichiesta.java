package model_uvp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import controller.DbConnection;
import controller.Utils;
import model.Attached;


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




	public static boolean addRequest(RequestInternship richiesta)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
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
				return true;
			}
			else
			{
				con.rollback();
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;


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

}
