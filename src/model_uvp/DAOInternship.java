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
public class DAOInternship {

	/**
	 * 
	 * Questa funzione restituisce tutti i tirocini interni presenti nel database
	 * 
	 * @return ArrayList<InternalInternship>
	 */
	public ArrayList<InternalInternship>  viewTraineeInternal()
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		InternalInternship internship = null;
		ArrayList<InternalInternship> internships = new ArrayList<InternalInternship>();
		String viewRequestInternal = "SELECT i.id_ii, i.tutor_name, i.theme, i.availability, i.resources, i.goals, u.office\r\n" + 
				"from internship_i as i  inner join perform as p on i.id_ii = p.id_ii\r\n" + 
				"inner join user as u on p.FK_USER = u.EMAIL";
		try {
			statement = con.prepareStatement(viewRequestInternal);
			result = statement.executeQuery();
			while(result.next())
			{
				internship = new InternalInternship(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6), result.getString(7));
				internships.add(internship);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return internships;
	}


	/**
	 * Questa funzione restituisce tutti i tirocini esterni presenti nel database
	 * @return ArrayList<ExternalInternship>
	 */
	public ArrayList<ExternalInternship>  viewTraineeExternal()
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		ExternalInternship internship = null;
		ArrayList<ExternalInternship> internships = new ArrayList<ExternalInternship>();
		String viewRequestExternal = "SELECT e.id_ie, e.name, e.duration_convention, e.date_convention, e.availability, e.info, u.EMAIL, u.OFFICE \r\n" + 
				"FROM internship_e as e inner join do on e.id_ie = do.id_ie \r\n" + 
				"inner join user as u on do.FK_USER = u.EMAIL";
		try {
			statement = con.prepareStatement(viewRequestExternal);
			result = statement.executeQuery();
			while(result.next())
			{
				internship = new ExternalInternship(result.getInt(1), result.getString(2), result.getInt(3), result.getDate(4), result.getInt(5), result.getString(6), result.getString(7), result.getString(8));
				internships.add(internship);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return internships;
	}

	


}
