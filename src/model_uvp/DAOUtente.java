package model_uvp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import controller.DbConnection;
import controller.Utils;


public class DAOUtente {
	public static boolean updatePassword(String email, String nuovaPsw) throws SQLException 
	{
		String newPassword = new Utils().generatePwd(nuovaPsw);
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String updatepsw = "UPDATE user\r\n" + 
				"SET PASSWORD = ?\r\n" + 
				"WHERE email= ?;";
		try 
		{

			statement = con.prepareStatement(updatepsw);
			statement.setString(1, newPassword);
			statement.setString(2, email);
			if(statement.executeUpdate() > 0)
			{
				con.commit();
				return true;

			}
			else
				con.rollback();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean checkMail(String email) throws SQLException 
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String checkMail = "SELECT EMAIL FROM user where EMAIL = ?;";
		try 
		{

			statement = con.prepareStatement(checkMail);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				if(result.getString(1).equals(email))
					return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
