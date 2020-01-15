package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.DbConnection;
import controller.ServletAdmin;
import interfacce.UserInterface;
import model.Admin;
import model.Student;
import model_uvp.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class ServletAdminTest extends Mockito {
	private ServletAdmin servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servlet = new ServletAdmin();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testGenerateExcel() throws ServletException, IOException {
		UserInterface user = new Admin("fferrucci@unisa.it", "Luigi", "Melchionno", 'M', "password", 2);
		request.getSession().setAttribute("user", user);
		request.addParameter("flag", "5");
		servlet.doGet(request, response);
		assertEquals("application/vnd.ms-excel", response.getContentType());
	}

	@Test
	public void testGenerateExcel2() throws ServletException, IOException {
		UserInterface user = new Admin("fferrucci@unisa.it", "Luigi", "Melchionno", 'M', "password", 2);
		request.getSession().setAttribute("user", user);
		request.addParameter("flag", "6");
		servlet.doGet(request, response);
		assertEquals("application/vnd.ms-excel", response.getContentType());
	}

	@Test
	public void testGenerateExcelFail() throws ServletException, IOException {
		UserInterface user = new Admin("fferrucci@unisa.it", "Luigi", "Melchionno", 'M', "password", 2);
		request.getSession().setAttribute("user", user);
		request.addParameter("flag", "60");
		servlet.doGet(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testGenerateExcelFail2() throws ServletException, IOException {
		UserInterface user = new Admin("fferrucci@unisa.it", "Luigi", "Melchionno", 'M', "password", 0);
		request.getSession().setAttribute("user", user);
		request.addParameter("flag", "5");
		servlet.doGet(request, response);
		assertNull(null, response.getContentType());
	}

	@Test
	public void testViewRequest() throws ServletException, IOException {
		request.addParameter("flag", "1");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequest() throws ServletException, IOException {
		request.addParameter("type", "1");
		request.addParameter("idRequest", "2");
		request.addParameter("flag", "2");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequest1() throws ServletException, IOException {
		request.addParameter("type", "2");
		request.addParameter("idRequest", "3");
		request.addParameter("flag", "2");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequest2() throws ServletException, IOException {
		request.addParameter("type", "3");
		request.addParameter("idRequest", "3");
		request.addParameter("flag", "2");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequestEmpty() throws ServletException, IOException {
		request.addParameter("type", "2");
		request.addParameter("idRequest", "321456");
		request.addParameter("flag", "2");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequestAccepted() throws ServletException, IOException {
		request.addParameter("idRequest", "1");
		request.addParameter("flag", "3");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequestAcceptedEmpty() throws ServletException, IOException {
		request.addParameter("idRequest", "123456");
		request.addParameter("flag", "3");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequestRefused() throws ServletException, IOException {
		request.addParameter("idRequest", "4");
		request.addParameter("flag", "4");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}

	@Test
	public void testUpdateRequestRefusedEmpty() throws ServletException, IOException {
		request.addParameter("idRequest", "456789");
		request.addParameter("flag", "4");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}
	@Test
	public void testUpdateRequestAccept() throws ServletException, IOException {
		UserInterface user = new Admin("fferrucci@unisa.it", "Luigi", "Melchionno", 'M', "password", 0);
		request.getSession().setAttribute("user", user);
		request.addParameter("idRequest", "456789");
		request.addParameter("flag", "5");
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}
	@Test
	public void testUpdateRequestReject() throws ServletException, IOException, SQLException {
		UserInterface user = new Admin("fferrucci@unisa.it", "Luigi", "Melchionno", 'M', "password", 0);
		request.getSession().setAttribute("user", user);
		request.addParameter("idRequest", "456789");
		request.addParameter("flag", "5");
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addRequestEV = "INSERT INTO request (ID_REQUEST, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, FK_CERTIFIER, FK_STATE) VALUES (111,'sdds', 'A1', '2011-08-19', '2018-08-19', 2020, '3', '0512105521', '3', 'a.baldi20@studenti.unisa.it', 7, 4)\r\n";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequestEV);
		statement.executeUpdate();
		con.commit();

		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		String DeleteRequest = "DELETE FROM request WHERE ID_REQUEST = 111";
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
		
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	}
}
