package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Attached;
import model_uvp.RequestInternship;

class Uvp_RequestInternshipTest {
static RequestInternship app;
	@Test
	void testRequestInternship() {
		RequestInternship app2 = new RequestInternship();
	}

	@BeforeAll
	static void testConstructorRequestInternship() {
		 List<Attached> attached = new ArrayList<>();
		app = new RequestInternship(1, "In attesa di accettazione", "Interno", "0512105521", "Antonio+Baldi", 1, 0, attached );
			}

	@Test
	void testGetId_request_i() {
		app.getId_request_i();
	}

	@Test
	void testSetId_request_i() {
		app.setId_request_i(2);
	}

	@Test
	void testGetType() {
		app.getType();
	}

	@Test
	void testSetType() {
		app.setType("esterno");
	}

	@Test
	void testGetUserName() {
		app.getUserName();
	}

	@Test
	void testGetUserSurname() {
		app.getUserSurname();
	}

	@Test
	void testGetUserFullName() {
		app.getUserFullName();
	}

	@Test
	void testSetUserFullName() {
		app.setUserFullName("michele+pirro");
	}

	@Test
	void testGetId_ie() {
		app.getId_ie();
	}

	@Test
	void testSetId_ie() {
		app.setId_ie(0);
	}

	@Test
	void testGetId_ii() {
		app.getId_ii();
	}

	@Test
	void testSetId_ii() {
		app.setId_ii(2);
	}

	@Test
	void testGetStatus() {
		app.getStatus();
	}

	@Test
	void testSetStatus() {
	app.setStatus("conclusa");	
	}

	@Test
	void testGetAttached() {
		app.getAttached();
	}

	@Test
	void testSetAttached() {
		 List<Attached> attached = new ArrayList<>();
		app.setAttached(attached);
	}

	@Test
	void testGetUserEmail() {
		app.getUserEmail();
	}

	@Test
	void testSetUserEmail() {
		app.setUserEmail("a.baldi20@studenti.unisa.it");
	}

	@Test
	void testGetUserSerial() {
		app.getUserSerial();
	}

	@Test
	void testSetUserSerial() {
		app.setUserSerial("2378932239");
	}

}
