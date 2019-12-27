package model_uvp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DbConnection;
/**
 * 
 * @author Antonio
 *
 */
@SuppressWarnings("static-access")
public class DAOInternship {

	/**
	 * 
	 * Questa funzione restituisce tutti i tirocini interni presenti nel database
	 * 
	 * @return ArrayList<Internship>
	 */
	public ArrayList<Internship>  viewInternalInternships()
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		ArrayList<Internship> internships = new ArrayList<>();
		String viewRequestInternal = "SELECT i.id_ii, i.tutor_name, i.theme, i.availability, i.resources, i.goals, u.office\r\n" + 
				"from internship_i as i  inner join perform as p on i.id_ii = p.id_ii\r\n" + 
				"inner join user as u on p.FK_USER = u.EMAIL";
		try {
			statement = con.prepareStatement(viewRequestInternal);
			result = statement.executeQuery();
			while(result.next())
			{
				internships.add(new InternalInternship(
						result.getInt(1),		// id
						result.getString(2),	// tutor name
						result.getString(3), 	// theme
						result.getInt(4), 		// availability
						result.getString(5),	// resources
						result.getString(6),	// goals
						result.getString(7)));	// place
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return internships;
	}


	/**
	 * Questa funzione restituisce tutti i tirocini esterni presenti nel database
	 * @return ArrayList<Internship>
	 */
	public ArrayList<Internship>  viewExternalInternships()
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		ArrayList<Internship> internships = new ArrayList<>();
		String viewRequestExternal = "SELECT e.id_ie, e.name, e.duration_convention, e.date_convention, e.availability, e.info, u.EMAIL, u.OFFICE \r\n" + 
				"FROM internship_e as e inner join do on e.id_ie = do.id_ie \r\n" + 
				"inner join user as u on do.FK_USER = u.EMAIL";
		try {
			statement = con.prepareStatement(viewRequestExternal);
			result = statement.executeQuery();
			while(result.next())
			{
				internships.add(new ExternalInternship(
						result.getInt(1),		// id
						result.getString(2),	// name
						result.getInt(3), 		// duration convention
						result.getDate(4), 		// date convention
						result.getInt(5),		// availability
						result.getString(6)));	// info
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return internships;
	}
}
