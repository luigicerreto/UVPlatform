package integrationTesting;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller_uvp.acceptRequest;
import controller_uvp.resetPassword;


public class ResetPasswordTest {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private resetPassword servlet;
	private JSONObject res;
	
	@BeforeEach
	public void setUp() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new resetPassword();
		res = new JSONObject();
	}
	
	

	
	@Test
	void testResetPassword() throws ServletException, IOException, ParseException {
		request.addParameter("email", "a.riccelli1@studenti.unisa.it");
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
