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

/**
 * Questa funzione consente di recuperare dal database tutte le informazioni relative
 * a tutte le richieste di un dato utente
 * 
 * @author Antonio Baldi
 * @param email
 */
public class DAORichiesta {
	public static ArrayList<RequestInternship> viewRequests(String email) throws SQLException 
	{
		ArrayList<RequestInternship> requests = new ArrayList<RequestInternship>();
		RequestInternship request1 = new RequestInternship();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet resultAttached;
		ResultSet result;
		String viewRequest = "use uvplatform;\r\n" + 
				"Select richiesta.id_request_i, user.SERIAL, richiesta.type, richiesta.STATE\r\n" + 
				"from request_internship as richiesta inner join internship_i as interno on richiesta.FK_II = interno.id_ii\r\n" + 
				"inner join user on richiesta.FK_USER1 = user.EMAIL\r\n" + 
				"where richiesta.FK_USER1 = ?";
		try 
		{

			statement = con.prepareStatement(viewRequest);
			statement.setString(1, email);
			result = statement.executeQuery();
			String attachedQuery="";
			List<Attached> attacheds;
			Attached attac;
			while(result.next())
			{
				
				statement=con.prepareStatement(attachedQuery);
				resultAttached = statement.executeQuery();
				attacheds = new ArrayList<Attached>();
				attac=null;
				while(resultAttached.next())
				{
				attac.setFilename(resultAttached.getString(1));
				attacheds.add(attac);
				}
				request1.setId_request_i(result.getInt(1));
				request1.setUser1(result.getString(2));
				request1.setAttached(attacheds);
				request1.setType(result.getString(3));
				request1.setState(result.getString(4));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return requests;
	}

}
