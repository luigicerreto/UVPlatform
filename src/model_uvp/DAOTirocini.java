package model_uvp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DbConnection;

public class DAOTirocini {

	public static ArrayList<InternalInternship>  viewTraineeInternal()
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

}
