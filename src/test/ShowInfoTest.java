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

import controller.DbConnection;
import controller.ServletSignup;
import controller_uvp.showInfo;
import model_uvp.DAOUser;

public class ShowInfoTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private showInfo servlet;
	private JSONObject res;

	@BeforeAll
	public static void setUp() throws ServletException, IOException, SQLException {
		ServletSignup signup = new ServletSignup();
		MockHttpServletRequest signup_req = new MockHttpServletRequest();
		MockHttpServletResponse signup_res = new MockHttpServletResponse();
		// nuovo studente per il test
		signup_req.addParameter("name", "TESTER");
		signup_req.addParameter("surname", "TESTER");
		signup_req.addParameter("email", "t.tester@studenti.unisa.it");
		signup_req.addParameter("sex", "M");
		signup_req.addParameter("password", "password");
		signup_req.addParameter("flag", "1");
		signup.doPost(signup_req, signup_res);

		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;

		// nuova richiesta tirocinio interno
		String req_int = "INSERT INTO REQUEST_INTERNSHIP (ID_REQUEST_I, TYPE, STATE, FK_USER1, FK_USER2, FK_II, FK_IE) "
				+ "VALUES (1000, 0, \"TESTING\", \"t.tester@studenti.unisa.it\", \"rdeprisco@unisa.it\", 1, null)";
		statement = con.prepareStatement(req_int);

		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();

		// nuovo allegato richiesta tirocinio 
		String att_int = "INSERT INTO ATTACHED (FILENAME, FK_USER, FK_REQUEST_I) "
				+ "VALUES (\"TESTFILE.pdf\", \"t.tester@studenti.unisa.it\" , 1000)";
		statement = con.prepareStatement(att_int);

		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();

		// nuova richiesta tirocinio esterno
		String req_ext = "INSERT INTO REQUEST_INTERNSHIP (ID_REQUEST_I, TYPE, STATE, FK_USER1, FK_USER2, FK_II, FK_IE) "
				+ "VALUES (1001, 1, \"TESTING\", \"t.tester@studenti.unisa.it\", \"info@kineton.it\", null, 1)";
		statement = con.prepareStatement(req_ext);

		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();

		// nuovo allegato richiesta tirocinio 
		String att_ext = "INSERT INTO ATTACHED (FILENAME, FK_USER, FK_REQUEST_I) "
				+ "VALUES (\"TESTFILE.pdf\", \"t.tester@studenti.unisa.it\" , 1001)";
		statement = con.prepareStatement(att_ext);

		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();

		// nuova richiesta tirocinio interno senza allegati
		String req_int_no_att = "INSERT INTO REQUEST_INTERNSHIP (ID_REQUEST_I, TYPE, STATE, FK_USER1, FK_USER2, FK_II, FK_IE) "
				+ "VALUES (1002, 0, \"TESTING\", \"t.tester@studenti.unisa.it\", \"rdeprisco@unisa.it\", 1, null)";
		statement = con.prepareStatement(req_int_no_att);

		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();

		// nuova richiesta tirocinio esterno senza allegati
		String req_ext_no_att = "INSERT INTO REQUEST_INTERNSHIP (ID_REQUEST_I, TYPE, STATE, FK_USER1, FK_USER2, FK_II, FK_IE) "
				+ "VALUES (1003, 1, \"TESTING\", \"t.tester@studenti.unisa.it\", \"info@kineton.it\", null, 1)";
		statement = con.prepareStatement(req_ext_no_att);

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

		// elimina studente per il test
		new DAOUser().removeUser("t.tester@studenti.unisa.it");

		// elimina le richieste effettuate
		String sql1= "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = \"t.tester@studenti.unisa.it\"";
		statement = con.prepareStatement(sql1);

		if(statement.executeUpdate()>0)
			con.commit();
		else
			con.rollback();
	}

	@BeforeEach
	public void init() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new showInfo();
	}

	@Test
	public void testShowInfo_RequestInternal() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("id", "1000");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("id").toString(), "1000");
	}

	@Test
	public void testShowInfo_RequestExternal() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("id", "1001");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("id").toString(), "1001");
	}
	
	@Test
	public void testShowInfo_RequestInternalNoAttached() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("id", "1002");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("id").toString(), "1002");
	}
	
	@Test
	public void testShowInfo_RequestExternalNoAttached() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("id", "1003");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("id").toString(), "1003");
	}

	@Test
	public void testShowInfo_InternalInternship() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "2");
		request.addParameter("id", "1");
		request.addParameter("type_internship", "0");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("id").toString(), "1");
	}

	@Test
	public void testShowInfo_ExternalInternship() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "2");
		request.addParameter("id", "1");
		request.addParameter("type_internship", "1");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("id").toString(), "1");
	}
	
	@Test
	public void doGetTest() throws ServletException, IOException {
		servlet.doGet(request, response);
	}
}
