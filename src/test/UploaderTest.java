package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import controller.DbConnection;
import controller.Uploader;
import interfacce.UserInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import model.Request;
import model.Student;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class UploaderTest extends Mockito {
	private Uploader servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servlet = new Uploader();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	

	@Test
	public void testDoPostInternship() throws ServletException, IOException, SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 0,  'In attesa', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', null, 111)";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		con.commit();

		request.addParameter("id_request", "111");
		request.getSession().setAttribute("idRequest", null);
		servlet.doPost(request, response);
		assertEquals("text/html", response.getContentType());
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
	public void testDoPostEV() throws ServletException, IOException, SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 4)\r\n";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		con.commit();

		request.addParameter("id_request", "111");
		request.getSession().setAttribute("idRequest", 111);
		servlet.doPost(request, response);
		assertEquals("text/html", response.getContentType());
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	public void testDoPostFail() throws ServletException, IOException, SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 4)\r\n";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		con.commit();
		
		request.addParameter("id_request", "389");
		request.getSession().setAttribute("idRequest", null);
		assertThrows(NullPointerException.class, ()-> {
			servlet.doPost(request, response);
		}
				);
		
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}
	@Test
	public void testDoPostPass2() throws ServletException, IOException, SQLException {
		
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 4)\r\n";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		con.commit();
		
		request.addParameter("id_request", "387");
		request.getSession().setAttribute("idRequest", 1111111);
		assertThrows(NullPointerException.class, ()-> {
			servlet.doPost(request, response);
		}
				);
		
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}
	
	@Test
	public void testDoPostPass() throws ServletException, IOException, SQLException {
		
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 4)\r\n";

		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		con.commit();
		
		request.addParameter("id_request", "null");
		request.getSession().setAttribute("idRequest", "000");
		assertThrows(NullPointerException.class, ()-> {
			servlet.doPost(request, response);
		}
				);
		
		
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}
}