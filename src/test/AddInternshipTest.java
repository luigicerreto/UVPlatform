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

import controller_uvp.acceptRequest;
import controller_uvp.addInternship;
import model_uvp.DAOUser;

public class AddInternshipTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private addInternship servlet;
	private JSONObject res;

	@BeforeEach
	public void setUp() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new addInternship();
		res = new JSONObject();
	}
	
	@AfterEach
	public void tearDown() {
		new DAOUser().removeUser("grossi@unisa.it");
	}

	@Test
	void testAddInternalInternship_pass() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "Giacomo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "Sicurezza");
		request.addParameter("availability", "23");
		request.addParameter("resources", "reti di sistema");
		request.addParameter("goals", "programmazione python");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	void testAddInternalInternship_name_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "G");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "Sicurezza");
		request.addParameter("availability", "23");
		request.addParameter("resources", "reti di sistema");
		request.addParameter("goals", "programmazione python");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddInternalInternship_surname_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "Giacomo");
		request.addParameter("surname", "R");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "Sicurezza");
		request.addParameter("availability", "23");
		request.addParameter("resources", "reti di sistema");
		request.addParameter("goals", "programmazione python");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	@Test
	void testAddInternalInternship_email_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "Giacomo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "gr@gmail.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "Sicurezza");
		request.addParameter("availability", "23");
		request.addParameter("resources", "reti di sistema");
		request.addParameter("goals", "programmazione python");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddInternalInternship_theme_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "Giacomo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "S");
		request.addParameter("availability", "23");
		request.addParameter("resources", "reti di sistema");
		request.addParameter("goals", "programmazione python");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddInternalInternship_availability_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "Giacomo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "Sicurezza");
		request.addParameter("availability", "-1");
		request.addParameter("resources", "reti di sistema");
		request.addParameter("goals", "programmazione python");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddInternalInternship_resources_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "Giacomo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "Sicurezza");
		request.addParameter("availability", "0");
		request.addParameter("resources", "a");
		request.addParameter("goals", "programmazione python");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddInternalInternship_goals_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "0");
		request.addParameter("name", "Giacomo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("sex", "M");
		request.addParameter("theme", "Sicurezza");
		request.addParameter("availability", "1");
		request.addParameter("resources", "programmazione");
		request.addParameter("goals", "a");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddExternalInternship_pass() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "1");
		request.addParameter("name", "Chime");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("availability", "15");
		request.addParameter("duration", "20");
		request.addParameter("info", "programmazione python");
		request.addParameter("date", "2020-11-10");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "1");
	}
	
	@Test
	void testAddExternalInternship_name_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "1");
		request.addParameter("name", "G");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("availability", "15");
		request.addParameter("duration", "20");
		request.addParameter("info", "programmazione python");
		request.addParameter("date", "2020-11-10");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddExternalInternship_email_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "1");
		request.addParameter("name", "Chime");
		request.addParameter("email", "cd@gmail.com");
		request.addParameter("office", "salerno");
		request.addParameter("availability", "15");
		request.addParameter("duration", "20");
		request.addParameter("info", "programmazione python");
		request.addParameter("date", "2020-11-10");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddExternalInternship_availability_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "1");
		request.addParameter("name", "Chime");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("availability", "-1");
		request.addParameter("duration", "20");
		request.addParameter("info", "programmazione python");
		request.addParameter("date", "2020-11-10");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddExternalInternship_duration_convention_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "1");
		request.addParameter("name", "Chime");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("availability", "2");
		request.addParameter("duration", "-1");
		request.addParameter("info", "programmazione python");
		request.addParameter("date", "2020-11-10");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
	
	@Test
	void testAddExternalInternship_info_fail() throws ServletException, IOException, ParseException {
		request.addParameter("flag", "1");
		request.addParameter("name", "Chime");
		request.addParameter("email", "grossi@unisa.it");
		request.addParameter("office", "salerno");
		request.addParameter("availability", "15");
		request.addParameter("duration", "20");
		request.addParameter("info", "a");
		request.addParameter("date", "2020-11-10");

		servlet.doPost(request, response);
		res = (JSONObject) new JSONParser().parse(response.getContentAsString());
		assertEquals(res.get("result").toString(), "0");
	}
}
