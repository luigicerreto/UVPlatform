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

import controller_uvp.rejectRequest;



public class RejectRequestTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private rejectRequest servlet;
	private JSONObject res;
	
	@BeforeEach
	public void setUp() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new rejectRequest();
		res = new JSONObject();
	}

	@Test
	public void testRejectRequest_pass() throws ServletException, IOException, ParseException {
		request.addParameter("id_request", "1");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	public void testRejectRequest_fail() throws ServletException, IOException, ParseException {
		request.addParameter("id_request", "0");
		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
}
