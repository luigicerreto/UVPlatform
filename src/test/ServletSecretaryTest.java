package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import controller.DbConnection;
import controller.ServletSecretary;
import interfacce.UserInterface;

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

public class ServletSecretaryTest extends Mockito {
  private ServletSecretary servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  /**
   * Before.
   */
  @Before
  public void setUp() {
    servlet = new ServletSecretary();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }
  
  @Test
  public void testViewRequest() throws ServletException, IOException {
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testViewRequestFailFlag() throws ServletException, IOException {
    request.addParameter("flag", "10");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testSetCfu() throws ServletException, IOException {
    request.addParameter("idRequest", "1");
    request.addParameter("cfu", "6");
    request.addParameter("flag", "2");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testSetCfuFailFlag() throws ServletException, IOException {
    request.addParameter("idRequest", "1");
    request.addParameter("cfu", "6");
    request.addParameter("flag", "20");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testSetCfuEmpty() throws ServletException, IOException {
    request.addParameter("idRequest", "123456");
    request.addParameter("cfu", "6");
    request.addParameter("flag", "2");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testSendAdmin() throws ServletException, IOException {
    request.addParameter("idRequest", "1");
    request.addParameter("flag", "3");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testSendAdminFailFlag() throws ServletException, IOException {
    request.addParameter("idRequest", "1");
    request.addParameter("flag", "30");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testSendAdminEmpty() throws ServletException, IOException {
    request.addParameter("idRequest", "123456");
    request.addParameter("flag", "3");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testDoGet() throws ServletException, IOException {
    request.addParameter("idRequest", "1");
    request.addParameter("flag", "3");
    servlet.doGet(request, response);
    assertEquals("json", response.getContentType());
  }
  @Test
	public void test3() throws ServletException, IOException {
		request.addParameter("idRequest", "1");
		request.addParameter("flag", "3");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void test2() throws ServletException, IOException, SQLException {
		UserInterface user = new Student("a.baldi20@studenti.unisa.it", "Antonio", "Baldi", 'M', "password", 0);
		
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 2)\r\n";
		String addUser2 = "INSERT INTO user VALUES ('a.baldi21@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV2 = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (112,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi21@studenti.unisa.it', 7, 2)\r\n";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV2);
		statement.executeUpdate();
		con.commit();
		
		request.getSession().setAttribute("user", user);
		request.addParameter("idRequest", "111");
		request.addParameter("flag", "1");
		servlet.doPost(request, response);
		
		String DeleteUser2 = "DELETE FROM user WHERE email = 'a.baldi21@studenti.unisa.it'";
		String DeleteRequest2 = "DELETE FROM request WHERE ID_REQUEST = 112";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		con.commit();
		
		
	}
	
	@Test
	public void testUpdateRequestAccept() throws ServletException, IOException, SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 4)\r\n";
		String addUser2 = "INSERT INTO user VALUES ('a.baldi21@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV2 = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (112,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi21@studenti.unisa.it', 7, 5)\r\n";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV2);
		statement.executeUpdate();
		con.commit();
		UserInterface user = new Student("a.baldi20@studenti.unisa.it", "Antonio", "Baldi", 'M', "password", 0);
		request.getSession().setAttribute("user", user);
		request.addParameter("idRequest", "111");
		request.addParameter("flag", "1");
		servlet.doPost(request, response);
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'a.baldi21@studenti.unisa.it'";
		String DeleteRequest2 = "DELETE FROM request WHERE ID_REQUEST = 112";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		con.commit();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		con.commit();
	}
	@Test
	public void test5() throws ServletException, IOException, SQLException {
		UserInterface user = new Student("a.baldi20@studenti.unisa.it", "Antonio", "Baldi", 'M', "password", 0);
		
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 3)\r\n";
		String addUser2 = "INSERT INTO user VALUES ('a.baldi21@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV2 = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (112,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi21@studenti.unisa.it', 7, 3)\r\n";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV2);
		statement.executeUpdate();
		con.commit();
		
		request.getSession().setAttribute("user", user);
		request.addParameter("idRequest", "111");
		request.addParameter("flag", "1");
		servlet.doPost(request, response);
		
		String DeleteUser2 = "DELETE FROM user WHERE email = 'a.baldi21@studenti.unisa.it'";
		String DeleteRequest2 = "DELETE FROM request WHERE ID_REQUEST = 112";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		con.commit();
		
		
	}
}
