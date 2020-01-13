package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Attached;
import model_uvp.RequestInternship;

class Uvp_RequestInternshipTest {
static RequestInternship app;
	@Test
	void testRequestInternship() {
		RequestInternship app2 = new RequestInternship();
	}

	@BeforeEach
	 void testConstructorRequestInternship() {
		 List<Attached> attached = new ArrayList<>();
		app = new RequestInternship(1, "In attesa di accettazione", 0, 1, attached );
			
	}

	@Test
	void atestGetId_request_i() {
		assertEquals(1, app.getId_request_i());
	}

	@Test
	void testSetId_request_i() {
		app.setId_request_i(3);
		assertEquals(3, app.getId_request_i());
	}

	@Test
	void testGetType() {
		assertEquals(0, app.getType());
	}
	
	@Test
	void testSetType() {
		app.setType(1);
		assertEquals(1, app.getType());
	}


	@Test
	void btestGetStatus() {
		assertEquals("In attesa di accettazione", app.getStatus());

	}

	@Test
	void testSetStatus() {
		app.setStatus("Conclusa");
		assertEquals("Conclusa", app.getStatus());
	}

	@Test
	void testGetAttached() {
		 List<Attached> attached = new ArrayList<>();
		assertEquals(attached, app.getAttached());
	}

	@Test
	void testSetAttached() {
		 List<Attached> attached = new ArrayList<>();
		 attached.add(new Attached(99, "rosariolove"));
		 app.setAttached(attached);
			assertEquals(attached, app.getAttached());

	}
}
