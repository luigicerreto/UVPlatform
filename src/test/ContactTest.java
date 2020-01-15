package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import controller.ServletSignup;
import controller_uvp.contact;
import interfacce.UserInterface;
import model_uvp.DAOUser;
import model_uvp.User;



public class ContactTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private contact servlet;
	private JSONObject res;
	private static MockHttpSession session;
	
	
	@BeforeEach
	public void setUp() throws ServletException, IOException {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new contact();
		res = new JSONObject();
		
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
		
		UserInterface user = (UserInterface) new User("t.tester@studenti.unisa.it", "TESTER", "TESTER", 
				'M', "password", 0, "0000000000", "");
		session = new MockHttpSession();
		session.setAttribute("user", user);
	}
	
	@AfterEach
	public void tearDown() throws SQLException {
		// elimina studente per il test
		new DAOUser().removeUser("t.tester@studenti.unisa.it");
	}
	
	@Test
	public void testContact_pass() throws ServletException, IOException, ParseException {
		request.addParameter("email", "t.tester@studenti.unisa.it");
		request.addParameter("field", "password");
		request.addParameter("value", "password");
		request.addParameter("current_pwd", "password");
		request.setSession(session);
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
}
