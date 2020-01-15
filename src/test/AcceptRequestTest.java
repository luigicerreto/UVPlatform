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
import controller_uvp.acceptRequest;
import model_uvp.DAOUser;

public class AcceptRequestTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private acceptRequest servlet;
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
		signup_req.addParameter("flag", "0");
		signup.doPost(signup_req, signup_res);

		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		// nuova richiesta tirocinio interno
		String req_int = "INSERT INTO REQUEST_INTERNSHIP (ID_REQUEST_I, TYPE, STATE, FK_USER1, FK_USER2, FK_II, FK_IE) "
				+ "VALUES (1000, 0, \"TEST\", \"t.tester@studenti.unisa.it\", \"rdeprisco@unisa.it\", 1, null)";
		statement = con.prepareStatement(req_int);
		if(statement.executeUpdate()==1) 
			con.commit();
		else 
			con.rollback();
	}

	@AfterAll
	public static void tearDown() throws SQLException {
		new DAOUser().removeUser("t.tester@studenti.unisa.it");

		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
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
		servlet = new acceptRequest();
		res = new JSONObject();
	}

	@Test
	void testAcceptRequestByTutor() throws ServletException, IOException, ParseException {
		request.setParameter("flag", "1");
		request.addParameter("id_request", "1000");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	void testAcceptRequestBySecretary() throws ServletException, IOException, ParseException {
		request.setParameter("flag", "2");
		request.addParameter("id_request", "1000");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	void testAcceptRequestByAdmin()throws ServletException, IOException, ParseException {
		request.setParameter("flag", "3");
		request.addParameter("id_request", "1000");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}

	@Test
	void testAcceptRequest_fail() throws ServletException, IOException, ParseException {
		request.setParameter("flag", "4");
		request.addParameter("id_request", "1000");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
}
