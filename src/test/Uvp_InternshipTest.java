package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model_uvp.Internship;

class Uvp_InternshipTest {
	static Internship internship;
	@BeforeAll
	static void testInternshipIntInt() {
		internship = new Internship(1,3);
	}

	@Test
	void testInternship() {
		Internship internship2 = new Internship();
	}

	@Test
	void testGetId() {
		internship.getId();
	}

	@Test
	void testSetId() {
		internship.setId(1);	}

	@Test
	void testGetAvailability() {
		internship.getAvailability();	}

	@Test
	void testSetAvailability() {
		internship.setAvailability(3);	}

}
