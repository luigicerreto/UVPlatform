package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import controller.DbConnection;
import controller.ServletSignup;
import controller_uvp.updateAttached;
import interfacce.UserInterface;
import model_uvp.DAOUser;
import model_uvp.User;

public class UpdateAttachedTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private static MockHttpSession session;
	private updateAttached servlet;
	private JSONObject res;

	@BeforeAll
	public static void setUp() throws ServletException, IOException, SQLException {
		ServletSignup signup = new ServletSignup();
		MockHttpServletRequest signup_req = new MockHttpServletRequest();
		MockHttpServletResponse signup_res = new MockHttpServletResponse();
		// nuovo studente test
		signup_req.addParameter("name", "TESTER");
		signup_req.addParameter("surname", "TESTER");
		signup_req.addParameter("email", "t.tester@studenti.unisa.it");
		signup_req.addParameter("sex", "M");
		signup_req.addParameter("password", "password");
		signup_req.addParameter("flag", "1");
		signup.doPost(signup_req, signup_res);

		// aggiungi studente alla sessione
		UserInterface user = (UserInterface) new User("t.tester@studenti.unisa.it", "TESTER", "TESTER", 
				'M', "password", 0, "0000000000", "");
		session = new MockHttpSession();
		session.setAttribute("user", user);

		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		// nuova richiesta tirocinio
		String req_int = "INSERT INTO REQUEST_INTERNSHIP (ID_REQUEST_I, TYPE, STATE, FK_USER1, FK_USER2, FK_II, FK_IE) "
				+ "VALUES (1000, 0, \"TESTING\", \"t.tester@studenti.unisa.it\", \"rdeprisco@unisa.it\", 1, null)";
		statement = con.prepareStatement(req_int);

		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();

		// nuovo allegato richiesta tirocinio 
		String req_att = "INSERT INTO ATTACHED (FILENAME, FK_USER, FK_REQUEST_I) "
				+ "VALUES (\"TESTFILE.pdf\", \"t.tester@studenti.unisa.it\" , 1000)";
		statement = con.prepareStatement(req_att);

		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();

	}

	@AfterAll
	public static void tearDown() throws SQLException {
		PreparedStatement statement = null;
		Connection con = new DbConnection().getInstance().getConn();
		// elimina allegati studente
		String sql= "DELETE FROM ATTACHED WHERE FK_USER = \"t.tester@studenti.unisa.it\"";
		statement = con.prepareStatement(sql);

		if(statement.executeUpdate()>0)
			con.commit();
		else
			con.rollback();

		// elimina studente test
		new DAOUser().removeUser("t.tester@studenti.unisa.it");

		// elimina la richiesta effettuata
		String sql_req= "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = \"t.tester@studenti.unisa.it\"";
		statement = con.prepareStatement(sql_req);

		if(statement.executeUpdate()>0)
			con.commit();
		else
			con.rollback();
	}

	@BeforeEach
	public void init() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new updateAttached();
	}

	@Test
	public void testUpdateAttached_RequestInternal() throws ServletException, IOException, ParseException {
		String[] filenames = new String[] {"file1.pdf"};
		request.addParameter("id_request", "1000");
		request.addParameter("flag", "0");
		request.addParameter("filenames[]", filenames);
		request.setSession(session);
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	public void testUpdateAttached_RequestExternal() throws ServletException, IOException, ParseException {
		String[] filenames = new String[] {"file1.pdf"};
		request.addParameter("id_request", "1000");
		request.addParameter("flag", "1");
		request.addParameter("filenames[]", filenames);
		request.setSession(session);
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}

	@Test
	public void testUpdateAttached_NumFilesExceeded() throws ServletException, IOException, ParseException {
		String[] filenames = new String[] {"file1.pdf", "file2.pdf"};
		request.addParameter("id_request", "1000");
		request.addParameter("filenames[]", filenames);
		request.setSession(session);
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	public void testAddAttached_NoUserInSession() throws ServletException, IOException, ParseException {
		String[] filenames = new String[] {"file1.pdf"};
		request.addParameter("id_request", "1000");
		request.addParameter("flag", "0");
		request.addParameter("filenames[]", filenames);
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
}