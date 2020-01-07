package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model_uvp.DAORequest;
import model_uvp.RequestInternship;

class Uvp_DAORequestTest {
	DAORequest app;
	@BeforeEach
	void setup()
	{
		app = new DAORequest();
	}
	@Test
	void testViewRequests() throws SQLException {
		app.viewRequests("");
	}

	@Test
	void testRetrieveLatestAttached() {
		app.retrieveAttached(0);
	}

	@Test
	void testAddRequest() {
		app.addRequest(new RequestInternship());
	}

	@Test
	void testCheckLastPartialRequest() {
		app.checkLastPartialRequest("");
	}

	@Test
	void testGetStatus() {
		app.getStatus(0);
	}

	@Test
	void testSetStatus() {
		app.setStatus(0, "newStatus");
	}

	@Test
	void testAddAttached() {
		app.addAttached(" ", " ", 0);
	}

	@Test
	void testUpdateAttached() {
		app.updateAttached(" ", 0);
	}

	@Test
	void testRetrieveAttached() {
		app.retrieveAttached(0);
	}

	@Test
	void testViewRequestsTeacher() throws SQLException {
		app.viewRequestsTeacher("");
	}

	@Test
	void testViewRequestsCompany() throws SQLException {
		app.viewRequestsCompany("");
	}

	@Test
	void testViewRequestsSecretary() throws SQLException {
		app.viewRequestsSecretary();
	}

	@Test
	void testAcceptRequestByProf_Company() {
		app.acceptRequestByProf_Company(0);
	}

	@Test
	void testAcceptRequestBySecretary() {
		app.acceptRequestBySecretary(0);
	}

	@Test
	void testAcceptRequestByAdmin() {
		app.acceptRequestByAdmin(0);
	}

	@Test
	void testRejectRequest() {
		app.rejectRequest(0);
	}

	@Test
	void testGetEmailByRequest() {
		app.getEmailByRequest(0);
	}

	@Test
	void testGetRequestTypeById() {
		app.getRequestTypeById(0);
	}

}
