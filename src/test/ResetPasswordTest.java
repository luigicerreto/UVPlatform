package test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.ServletSignup;
import controller_uvp.resetPassword;
import model_uvp.DAOUser;


public class ResetPasswordTest {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private resetPassword servlet;
	private JSONObject res;
	
	@BeforeEach
	public void setUp() throws ServletException, IOException {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new resetPassword();
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
		signup_req.addParameter("flag", "3");
		signup.doPost(signup_req, signup_res);
	}
	
	@AfterEach
	public void tearDown() {
		new DAOUser().removeUser("t.tester@studenti.unisa.it");
	}
	
	@Test
	void testResetPassword_pass() throws ServletException, IOException, ParseException {
		request.addParameter("email", "t.tester@studenti.unisa.it");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	void testResetPassword_fail() throws ServletException, IOException, ParseException {
		request.addParameter("email", "null@studenti.unisa.it");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
}
