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
import controller_uvp.addRequest;
import interfacce.UserInterface;
import model_uvp.DAOUser;
import model_uvp.User;


public class AddRequestTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private static MockHttpSession session;
	private addRequest servlet;
	private JSONObject res;

	@BeforeAll
	public static void setUp() throws ServletException, IOException {
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
	}

	@AfterAll
	public static void tearDown() throws SQLException {
		// elimina studente test
		new DAOUser().removeUser("t.tester@studenti.unisa.it");

		// elimina le richieste effettuate
		PreparedStatement statement = null;
		Connection con = new DbConnection().getInstance().getConn();

		String sql= "DELETE FROM REQUEST_INTERNSHIP WHERE FK_USER1 = \"t.tester@studenti.unisa.it\"";
		statement = con.prepareStatement(sql);

		if(statement.executeUpdate()>0)
			con.commit();
		else
			con.rollback();
	}

	@BeforeEach
	public void init() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new addRequest();
		res = new JSONObject();
	}

	@Test
	public void testAddRequest_pass() throws ServletException, IOException, ParseException {
		request.addParameter("type", "0");
		request.addParameter("choice", "1");
		request.setSession(session);
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}

	@Test
	public void testAddRequest_fail1() throws ServletException, IOException, ParseException {
		request.addParameter("type", "0");
		request.addParameter("choice", "1");
		request.setSession(session);
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}

	@Test
	public void testAddRequest_fail2() throws ServletException, IOException, ParseException {
		request.addParameter("type", "0");
		request.addParameter("choice", "1");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
}
