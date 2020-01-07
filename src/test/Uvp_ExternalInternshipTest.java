package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model_uvp.ExternalInternship;

class Uvp_ExternalInternshipTest {
static ExternalInternship app;
	@SuppressWarnings("deprecation")
	@BeforeAll
	static void testConstructor() {
	app = new ExternalInternship(1, "Programmazione sicura", 6, new Date(2019,10,10), 20, "La programmazione sicura è un ambito importante... ");	
	}

	@Test
	void testExternalInternship() {
		ExternalInternship app2 = new ExternalInternship();
	}

	@Test
	void testGetName() {
		app.getName();
	}

	@Test
	void testSetName() {
		app.setName("Secure programming");
	}

	@Test
	void testGetDuration_convention() {
		app.getDuration_convention();
	}

	@Test
	void testSetDuration_convention() {
		app.setDuration_convention(2);
	}
	@Test
	void testGetDate_convention() {
		app.getDate_convention();
	}

	@SuppressWarnings("deprecation")
	@Test
	void testSetDate_convention() {
		app.setDate_convention(new Date(2018, 10, 10));
	}

	@Test
	void testGetInfo() {
		app.getInfo();
	}

	@Test
	void testSetInfo() {
		app.setInfo(" ");
	}

}
