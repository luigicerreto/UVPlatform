package test;
import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller_uvp.getInternships;


public class GetInternshipsTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private getInternships servlet;

	
	@BeforeEach
	public void setUp() {
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new getInternships();
	}
	
	@Test
	public void getInternalInternshipTest() throws ServletException, IOException {
		request.setParameter("flag", "0");
		servlet.doPost(request, response);
		
	}
	
	@Test
	public void getExternalInternshipTest() throws ServletException, IOException {
		request.setParameter("flag", "1");
		servlet.doPost(request, response);
		
	}
}
