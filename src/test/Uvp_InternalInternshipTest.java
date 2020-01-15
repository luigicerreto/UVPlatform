package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model_uvp.InternalInternship;

class Uvp_InternalInternshipTest {
	static InternalInternship internalinternship;
	static InternalInternship internalinternship2;

	@BeforeAll
	static void testInternalInternshipConstruct() {
		internalinternship= new InternalInternship(1, "Roberto De Prisco", "android", 5,"Programmazione Object Oriented", "Padroneggiare Kotlin","edificio f2");
	}

	@Test
	void testInternalInternship() {
		internalinternship2= new InternalInternship();
	}

	@Test
	void testGetTutorn_name() {
		internalinternship.getTutor_name();
	}

	@Test
	void testSetTutorn_name() {
		internalinternship.setTutor_name("Roberto De Prisco");
	}

	@Test
	void testGetTheme() {
		internalinternship.getTheme();
	}

	@Test
	void testSetTheme() {
		internalinternship.setTheme("android");	}

	@Test
	void testGetResources() {

		internalinternship.getResources();}

	@Test
	void testSetResources() {
		internalinternship.setResources("Programmazione object oriented");
	}

	@Test
	void testGetGoals() {
		internalinternship.getGoals();
	}

	@Test
	void testSetGoals() {
		internalinternship.setGoals("Padroneggiare Kotlin");
	}

	@Test
	void testGetPlace() {
		internalinternship.getPlace();
	}

	@Test
	void testSetPlace() {
		internalinternship.setPlace("edificio f2");
	}

}
