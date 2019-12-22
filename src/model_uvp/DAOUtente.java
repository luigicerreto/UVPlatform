package model_uvp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import controller.DbConnection;
import controller.Utils;

/**
 * 
 * @author Antonio Baldi
 * @author Rosario Di Palma
 */
public class DAOUtente {
	/**
	 * 
	 * Questo metodo serve per aggiornare la password nel database dato
	 * la mail dell'utente e la nuova password.
	 * 
	 * 
	 * @author Antonio Baldi
	 * @author Rosario Di Palma
	 * @param email
	 * @param nuovaPsw
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean updatePassword(String email, String nuovaPsw) throws SQLException 
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
	/**
	 * 
	 * Questo metodo serve per controllare se la mail passata come paramentro
	 * è presente nel database.
	 * 
	 * @author Antonio Baldi
	 * @author Rosario Di Palma
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public boolean checkMail(String email) throws SQLException 
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
	
	/**
	 * 
	 * Questo funzione restitutisce un Utente compelto di tutti i suoi campi
	 * per visualizzarli sull'area utente del sistema
	 * 
	 * @param email
	 * @return User
	 */
	public User showUser(String email)
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		User userDate = new User();
		String checkUser = "SELECT NAME, SURNAME, phone "
				+ "FROM user where EMAIL = ?;";
		try {
			statement = con.prepareStatement(checkUser);
			statement.setString(1, email);
			result = statement.executeQuery();
			if(result.next())
			{
				userDate.setEmail(email);
				userDate.setName(result.getString(1));
				userDate.setSurname(result.getString(2));
				userDate.setPhone(result.getString(3));
				return userDate;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
