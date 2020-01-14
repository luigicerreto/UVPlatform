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

public class AcceptRequestTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private acceptRequest servlet;
	private JSONObject res;

	@BeforeEach
	public void setUp() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new acceptRequest();
		res = new JSONObject();
	}

	@Test
	void testAcceptRequestByTutor() throws ServletException, IOException, ParseException {
		request.setParameter("flag", "1");
		request.addParameter("id_request", "1");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	void testAcceptRequestBySecretary() throws ServletException, IOException, ParseException {
		request.setParameter("flag", "2");
		request.addParameter("id_request", "1");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	void testAcceptRequestByAdmin()throws ServletException, IOException, ParseException {
		request.setParameter("flag", "3");
		request.addParameter("id_request", "1");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}

	@Test
	void testAcceptRequest_fail() throws ServletException, IOException, ParseException {
		request.setParameter("flag", "4");
		request.addParameter("id_request", "1");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
}
