package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import model_uvp.DAOInternship;
import model_uvp.DAOUser;
import model_uvp.User;

class Uvp_DAOUserTest {
	DAOUser app;
	@BeforeEach
	void setup()
	{
		app = new DAOUser();
	}
	
	@Test
	void testUpdatePasswordFail() throws SQLException {
		app.updatePassword("", "testing");
	}
	
	@Test
	void testUpdatePasswordPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.updatePassword("a.baldi20@studenti.unisa.it", "password123");
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}
	
	

	@Test
	void testCheckMailFail() throws SQLException {
		app.checkMail("");
	}
	
	@Test
	void testCheckMailPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.checkMail("a.baldi20@studenti.unisa.it");
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testGetUserFail() {
		app.getUser("");
	}
	@Test
	void testGetUserPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.getUser("a.baldi20@studenti.unisa.it");
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testGetUserByRequestInternshipFail() {
		app.getUserByRequestInternship(0);
	}
	
	@Test
	void testGetUserByRequestInternshipPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 1,  'In attesa', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', null, 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		con.commit();
		app.getUserByRequestInternship(111);


		String DeleteRequest = "DELETE FROM request_internship WHERE ID_REQUEST_I = 111";
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testGetUserByRequestEVFail() {
		app.getUserByRequestEV(0);
	}
	@Test
	void testGetUserByRequestEVPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 1)\r\n";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		con.commit();
		
		app.getUserByRequestEV(111);
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testViewUsersFail() {
		app.viewUsers();
	}
	@Test
	void testViewUsersPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.viewUsers();
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
		
	}

	@Test
	void testViewTeachersFail() {
		app.viewTeachers();
	}
	
	@Test
	void testViewTeachersPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 3, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.viewTeachers();
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testViewCompaniesFail() {
		app.viewCompanies();
	}
	
	@Test
	void testViewCompaniesPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 4, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.viewCompanies();
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testEditUserFail() {
		app.editUser("", "", "");
	}
	@Test
	void testEditUserPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.editUser("a.baldi20@studenti.unisa.it", "PHONE", "3922412988");
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testAddTeacherFail() {
		User prova = new User();
		prova.setPassword("testing");
		app.addTeacher(prova);
	}
	@Test
	void testAddTeacherPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		User prova = new User();
		prova.setEmail("cgravino@unisa.it");
		prova.setName("Carmine");
		prova.setSurname("Gravino");
		prova.setSex('M');
		prova.setPassword("testing123");
		prova.setOffice("f2");
		app.addTeacher(prova);
		
		String DeleteUser = "DELETE FROM user WHERE email = 'cgravino@unisa.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
		
	}

	@Test
	void testAddCompanyFail() {
		User prova = new User();
		prova.setPassword("testing");
		app.addCompany(prova);
	}
	
	@Test
	void testAddCompanyPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		User prova = new User();
		prova.setEmail("info@david.it");
		prova.setName("david");
		prova.setPassword("testing123");
		prova.setOffice("napoli");
		app.addCompany(prova);
		
		String DeleteUser = "DELETE FROM user WHERE email = 'info@david.it'";
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testRemoveUserFail() {
		app.removeUser("");
	}
	
	@Test
	void testRemoveUserPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		
		app.removeUser("a.baldi20@studenti.unisa.it");
		
		
	}

}
