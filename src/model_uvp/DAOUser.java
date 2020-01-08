package model_uvp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import controller.DbConnection;
import controller.Utils;

/**
 * 
 * @author Antonio Baldi
 * @author Rosario Di Palma
 * @author Carmine
 */
@SuppressWarnings("static-access")
public class DAOUser {
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
	 * e' presente nel database.
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
	 * @param email
	 * @return
	 */
	public User getUser(String email){
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement stmt;
		ResultSet result;

		String sql = "SELECT email, name, surname, sex, password, user_type, serial, COALESCE(phone,'') as phone "
				+ "FROM user WHERE email = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			result = stmt.executeQuery();

			int size = result.last() ? result.getRow() : 0;

			if(size>0)
				return new User(
						result.getString(1), 			// email
						result.getString(2),			// name
						result.getString(3),			// surname
						result.getString(4).charAt(0),	// sex
						result.getString(5),			// password
						result.getInt(6),				// user type
						result.getString(7),			// serial
						result.getString(8)				// phone
						);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 
	 * @param idRequest
	 * @return
	 */
	public User getUserByRequestInternship(int idRequest){
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement stmt;
		ResultSet result;

		String sql = "SELECT email, name, surname, sex, password, user_type, serial, COALESCE(phone,'') as phone "
				+ "FROM user INNER JOIN request_internship ON user.email = request_internship.fk_user1 "
				+ "WHERE id_request_i = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idRequest);
			result = stmt.executeQuery();

			int size = result.last() ? result.getRow() : 0;

			if(size>0)
				return new User(
						result.getString(1), 			// email
						result.getString(2),			// name
						result.getString(3),			// surname
						result.getString(4).charAt(0),	// sex
						result.getString(5),			// password
						result.getInt(6),				// user type
						result.getString(7),			// serial
						result.getString(8)				// phone
						);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	public User getUserByRequestEV(int idRequest){
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement stmt;
		ResultSet result;

		String sql = "SELECT user.email, user.name, user.surname, user.sex, user.password, user.user_type, user.serial, COALESCE(phone,'') as phone "
				+ "FROM user INNER JOIN request ON user.email = request.fk_user "
				+ "WHERE id_request = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idRequest);
			result = stmt.executeQuery();

			int size = result.last() ? result.getRow() : 0;

			if(size>0)
				return new User(
						result.getString(1), 			// email
						result.getString(2),			// name
						result.getString(3),			// surname
						result.getString(4).charAt(0),	// sex
						result.getString(5),			// password
						result.getInt(6),				// user type
						result.getString(7),			// serial
						result.getString(8)				// phone
						);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<User> viewUsers(){
		Connection con = new DbConnection().getInstance().getConn();
		ArrayList<User> users = new ArrayList<>();
		PreparedStatement stmt;
		ResultSet result;

		String sql = "SELECT email, name, surname, sex, password, user_type, serial, COALESCE(phone,'') as phone "
				+ "FROM user WHERE user_type = \"0\"";

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next())
				users.add(new User(
						result.getString(1), 			// email
						result.getString(2),			// name
						result.getString(3),			// surname
						result.getString(4).charAt(0),	// sex
						result.getString(5),			// password
						result.getInt(6),				// user type
						result.getString(7),			// serial
						result.getString(8)				// phone
						));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<User> viewTeachers(){
		Connection con = new DbConnection().getInstance().getConn();
		ArrayList<User> users = new ArrayList<>();
		User u = null;
		PreparedStatement stmt;
		ResultSet result;

		String sql = "SELECT email, name, surname, sex, password, user_type, office, COALESCE(phone,'') as phone "
				+ "FROM user WHERE user_type = \"3\"";

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()) {
				u = new User();
				u.setEmail(result.getString(1));
				u.setName(result.getString(2));
				u.setSurname(result.getString(3));
				u.setSex(result.getString(4).charAt(0));
				u.setPassword(result.getString(5));
				u.setUserType(result.getInt(6));
				u.setOffice(result.getString(7));
				u.setPhone(result.getString(8));
				users.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<User> viewCompanies(){
		Connection con = new DbConnection().getInstance().getConn();
		ArrayList<User> users = new ArrayList<>();
		PreparedStatement stmt;
		ResultSet result;
		User u = null;

		String sql = "SELECT email, name, password, user_type, office, COALESCE(phone,'') as phone "
				+ "FROM user WHERE user_type = \"4\"";

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()) {
				u = new User();
				u.setEmail(result.getString(1));
				u.setName(result.getString(2));
				u.setPassword(result.getString(3));
				u.setUserType(result.getInt(4));
				u.setOffice(result.getString(5));
				u.setPhone(result.getString(6));
				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * 
	 */
	public boolean editUser(String email, String parameter, String value){
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement stmt;

		String sql = "UPDATE user SET " + parameter + " = ? WHERE email = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, value);
			stmt.setString(2, email);

			if(stmt.executeUpdate()==1) {
				con.commit();
				return true;
			} else {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addTeacher(User u){
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String sql = "INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE ) VALUES (?, ?, ?, ?, ?, ?, ?) ";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, u.getEmail());
			statement.setString(2, u.getName());
			statement.setString(3, u.getSurname());
			statement.setString(4, String.valueOf(u.getSex()));
			statement.setString(5, new Utils().generatePwd(u.getPassword()));
			statement.setInt(6, 3);
			statement.setString(7, u.getOffice());

			if(statement.executeUpdate()==1){
				con.commit();
				return true;
			} else {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addCompany(User u){
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String sql = "INSERT INTO user (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE ) VALUES (?, ?, ?, ?, ?) ";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, u.getEmail());
			statement.setString(2, u.getName());
			statement.setString(3, new Utils().generatePwd(u.getPassword()));
			statement.setInt(4, 4);
			statement.setString(5, u.getOffice());

			if(statement.executeUpdate()==1){
				con.commit();
				return true;
			} else {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeUser(String email) {
		PreparedStatement statement = null;
		Connection con = new DbConnection().getInstance().getConn();
		String sql= "DELETE FROM USER WHERE EMAIL = ?";
		
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			
			if(statement.executeUpdate()==1){
				con.commit();
				return true;
			} else {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
