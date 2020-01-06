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
 * @author Carmine
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
		String viewRequestInternal = "SELECT I.ID_II, I.TUTOR_NAME, I.THEME, I.AVAILABILITY, I.RESOURCES, I.GOALS, U.OFFICE " + 
				"FROM INTERNSHIP_I AS I INNER JOIN USER AS U ON I.FK_TUTOR = U.EMAIL";
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
		String viewRequestExternal = "SELECT E.ID_IE, E.NAME, E.DURATION_CONVENTION, E.DATE_CONVENTION, E.AVAILABILITY, E.INFO, U.EMAIL, U.OFFICE " + 
				"FROM INTERNSHIP_E AS E INNER JOIN USER AS U ON E.FK_TUTOR = U.EMAIL";
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

	/**
	 * Questa funzione restituisce i dati di un particolare tirocinio
	 * @return Internship
	 */
	public Internship  getInternshipData(int idInternship, int typeInternship)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		int resultSize;

		String external = "SELECT E.ID_IE, E.NAME, E.DURATION_CONVENTION, E.DATE_CONVENTION, E.AVAILABILITY, E.INFO, U.EMAIL, U.OFFICE " + 
				"FROM INTERNSHIP_E AS E INNER JOIN USER AS U ON E.FK_TUTOR = U.EMAIL WHERE E.ID_IE = ?";

		String internal = "SELECT I.ID_II, I.TUTOR_NAME, I.THEME, I.AVAILABILITY, I.RESOURCES, I.GOALS, U.OFFICE " + 
				"FROM INTERNSHIP_I AS I INNER JOIN USER AS U ON I.FK_TUTOR = U.EMAIL WHERE I.ID_II = ?";

		try {
			if(typeInternship == 0) { // internal
				statement = con.prepareStatement(internal);
				statement.setInt(1, idInternship);
				result = statement.executeQuery();
				resultSize = result.last() ? result.getRow() : 0;
				if(resultSize == 1) {
					return new InternalInternship(
							result.getInt(1),		// id
							result.getString(2),	// tutor name
							result.getString(3), 	// theme
							result.getInt(4), 		// availability
							result.getString(5),	// resources
							result.getString(6),	// goals
							result.getString(7));	// place
				}
			} else if (typeInternship == 1) { // external
				statement = con.prepareStatement(external);
				statement.setInt(1, idInternship);
				result = statement.executeQuery();
				resultSize = result.last() ? result.getRow() : 0;
				if(resultSize == 1) {
					return new ExternalInternship(
							result.getInt(1),		// id
							result.getString(2),	// name
							result.getInt(3), 		// duration convention
							result.getDate(4), 		// date convention
							result.getInt(5),		// availability
							result.getString(6));	// info
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Questa funzione restituisce la mail dell'azienda dato l'id del tirocinio esterno.
	 * 
	 * @param id_internship
	 * @return String
	 */
	public String getCompanyEmailByExternal(int id_internship)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String email_azienda = null;
		String retriveInternship = "SELECT FK_TUTOR FROM INTERNSHIP_E WHERE ID_IE = ?";
		try {
			statement = con.prepareStatement(retriveInternship);
			statement.setInt(1, id_internship);
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
	 * Questa funzione restituisce la mail del docente dato l'id del tirocinio interno.
	 * 
	 * @param id_internship
	 * @return String
	 */
	public String getTeacherEmailByInternal(int id_internship)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String email_docente = null;
		String sql = "SELECT FK_TUTOR FROM INTERNSHIP_I WHERE ID_II = ?";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, id_internship);
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
