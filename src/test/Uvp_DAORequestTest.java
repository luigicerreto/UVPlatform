package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import controller.DbConnection;
import model.Attached;
import model_uvp.DAORequest;
import model_uvp.RequestInternship;

class Uvp_DAORequestTest {
	DAORequest app;
	List<Attached> allegati;
	@BeforeEach
	void setup()
	{
		app = new DAORequest();
	}
	@Test
	void testViewRequestsFail() throws SQLException {
		app.viewRequests("");
	}
	@Test
	void testViewRequestsPassNoAttch() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'In attesa', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', null, 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		con.commit();
		app.viewRequests("a.baldi20@studenti.unisa.it");


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
	void testViewRequestsPassAttch() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'In attesa', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', null, 111)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();
		app.viewRequests("a.baldi20@studenti.unisa.it");

		String DeleteAttached = "DELETE FROM attached WHERE ID_ATTACHED = 111";
		String DeleteRequest = "DELETE FROM request_internship WHERE ID_REQUEST_I = 111";
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
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
	void testRetrieveLatestAttachedFail() {
		app.retrieveAttached(0);
	}
	@Test
	void testRetrieveLatestAttachedPass() throws SQLException {

		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'In attesa', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', null, 111)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();
		app.retrieveLatestAttached(111);

		String DeleteAttached = "DELETE FROM attached WHERE ID_ATTACHED = 111";
		String DeleteRequest = "DELETE FROM request_internship WHERE ID_REQUEST_I = 111";
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
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
	void testAddRequestFail() {
		app.addRequest(new RequestInternship());
	}
	
	@Test
	void testAddRequestFailWithWrongField() {
		allegati = new ArrayList<Attached>();
		RequestInternship prova = new RequestInternship(111, "", "INTERNO", "", "", 101, 100, allegati);
		prova.setUserEmail("d.capuano3@studenti.unisa.it");
		prova.setUserFullName("h.jkd@info.it");
		app.addRequest(prova);
	}
	@Test
	void testAddRequestPassInternal() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		con.commit();
		RequestInternship Request1 = new RequestInternship();

		Request1.setUserFullName("slatorre@unisa.it");
		Request1.setType("Tirocinio Interno");
		Request1.setId_ii(111);
		Request1.setStatus("Parzialmente completata");
		Request1.setUserEmail("a.baldi20@studenti.unisa.it");

		app.addRequest(Request1);
		String DeleteRequest = "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
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
	void testAddRequestPassExternal() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		con.commit();
		RequestInternship Request1 = new RequestInternship();

		Request1.setUserFullName("slatorre@unisa.it");
		Request1.setType("Tirocinio Esterno");
		Request1.setId_ie(111);
		Request1.setStatus("Parzialmente completata");
		Request1.setUserEmail("a.baldi20@studenti.unisa.it");

		app.addRequest(Request1);
		String DeleteRequest = "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";

		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testCheckLastPartialRequestFail() {
		app.checkLastPartialRequest("m.pirro2@studenti.unisa.it");
	}

	@Test
	void testCheckLastPartialRequestPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		con.commit();


		app.checkLastPartialRequest("a.baldi20@studenti.unisa.it");

		String DeleteRequest = "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";

		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testGetStatusFail() {
		app.getStatus(0);
	}
	@Test
	void testGetStatusPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		con.commit();


		app.getStatus(111);

		String DeleteRequest = "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";

		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testSetStatusFail() {
		app.setStatus(0, "newStatus");
	}
	@Test
	void testSetStatusPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		con.commit();


		app.setStatus(111, "test");

		String DeleteRequest = "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";

		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testAddAttachedFail() {
		app.addAttached(" ", " ", 0);
	}
	@Test
	void testAddAttachedPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		con.commit();


		app.addAttached("test", "a.baldi20@studenti.unisa.it", 111);

		String DeleteRequest = "DELETE FROM request_internship WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteAttached = "DELETE FROM attached WHERE FK_REQUEST_I = 111";

		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testUpdateAttachedFail() {
		app.updateAttached(" ", 0);
	}
	@Test
	void testUpdateAttachedPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();


		app.updateAttached("testing", 111);

		String DeleteRequest = "DELETE FROM request_internship WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteAttached = "DELETE FROM attached WHERE FK_REQUEST_I = 111";

		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testRetrieveAttachedFail() {
		app.retrieveAttached(-1);
	}
	@Test
	void testRetrieveAttachedPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();


		app.retrieveAttached(111);

		String DeleteRequest = "DELETE FROM request_internship WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteAttached = "DELETE FROM attached WHERE FK_REQUEST_I = 111";

		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testViewRequestsTeacherFail() throws SQLException {
		app.viewRequestsTeacher("");
	}
	@Test
	void testViewRequestsTeacherPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'In attesa', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', null, 111)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();

		app.viewRequestsTeacher("slatorre@unisa.it");

		String DeleteAttached = "DELETE FROM attached WHERE ID_ATTACHED = 111";
		String DeleteRequest = "DELETE FROM request_internship WHERE ID_REQUEST_I = 111";
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
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
	void testViewRequestsCompanyFail() throws SQLException {
		app.viewRequestsCompany("");
	}

	@Test
	void testViewRequestsCompanyPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();


		app.viewRequestsCompany("slatorre@unisa.it");

		String DeleteRequest = "DELETE FROM request_internship WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteAttached = "DELETE FROM attached WHERE FK_REQUEST_I = 111";

		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testViewRequestsSecretaryFail() throws SQLException {
		app.viewAllRequests();
	}
	@Test
	void testViewRequestsSecretaryPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();


		app.viewAllRequests();
		
		String DeleteRequest = "DELETE FROM request_internship WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteAttached = "DELETE FROM attached WHERE FK_REQUEST_I = 111";

		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testAcceptRequestByProf_Company() {
		app.acceptRequestByTutor(0);
	}

	@Test
	void testAcceptRequestBySecretary() {
		app.acceptRequestBySecretary(0);
	}

	@Test
	void testAcceptRequestByAdmin() {
		app.acceptRequestByAdmin(0);
	}

	@Test
	void testRejectRequest() {
		app.rejectRequest(0);
	}

	@Test
	void testGetEmailByRequestFail() {
		app.getEmailByRequest(0);
	}
	@Test
	void testGetEmailByRequestPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();


		app.getEmailByRequest(111);
		
		String DeleteRequest = "DELETE FROM request_internship WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteAttached = "DELETE FROM attached WHERE FK_REQUEST_I = 111";

		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testGetRequestTypeByIdFail() {
		app.getRequestTypeById(0);
	}
	
	@Test
	void testGetRequestTypeByIdPass() throws SQLException {
		allegati = new ArrayList<Attached>();
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 'Interno', 'Parzialmente completata', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', 111, null)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();


		app.getRequestTypeById(111);
		
		String DeleteRequest = "DELETE FROM request_internship WHERE FK_USER1 = 'a.baldi20@studenti.unisa.it'";
		String DeleteInternshipEx = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteAttached = "DELETE FROM attached WHERE FK_REQUEST_I = 111";

		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternshipEx);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

}
