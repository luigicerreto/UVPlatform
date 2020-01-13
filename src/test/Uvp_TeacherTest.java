package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model_uvp.Teacher;

class Uvp_TeacherTest {
	static Teacher teac;
	@BeforeAll
	static void testTeacherConstruct() {
		teac= new Teacher("rdeprisco@unisa.it", "Roberto", "De Prisco", 'M', "password", 3);
	}

	@Test
	void testTeacher() {
		Teacher teac2 = new Teacher();
	}

	@Test
	void testGetEmail() {
		assertEquals("rdeprisco@unisa.it", teac.getEmail());
	}

	@Test
	void testSetEmail() {
		teac.setEmail("111rdeprisco@unisa.it");
		assertEquals("111rdeprisco@unisa.it", teac.getEmail());
	}

	@Test
	void testGetName() {
		assertEquals("Roberto", teac.getName());
	}

	@Test
	void testSetName() {
		teac.setName("Roberto1");
		assertEquals("Roberto1", teac.getName());
	}

	@Test
	void testGetSurname() {
		assertEquals("De Prisco", teac.getSurname());

	}

	@Test
	void testSetSurname() {
		teac.setSurname("De Prisco2");
		assertEquals("De Prisco2", teac.getSurname());

	}

	@Test
	void testGetSex() {
		assertEquals('M', teac.getSex());
	}

	@Test
	void testSetSex() {
		teac.setSex('F');
		assertEquals('F', teac.getSex());
	}

	@Test
	void atestGetPassword() {
		teac.setPassword("password3");
		assertEquals("password3", teac.getPassword());

	}

	@Test
	void testSetPassword() {
		teac.setPassword("password2");
		assertEquals("password2", teac.getPassword());
	}

	@Test
	void btestGetUserType() {
		assertEquals(3, teac.getUserType());

	}

	@Test
	void testSetUserType() {
		teac.setUserType(5);	
		assertEquals(5, teac.getUserType());

	}

	@Test
	void testGetSerial() {
		teac.setSerial("1234567890");	
		assertEquals("1234567890", teac.getSerial());
	}

	@Test
	void testSetSerial() {
		teac.setSerial("12345678920");	
		assertEquals("12345678920", teac.getSerial());

	}

	@Test
	void ctestGetOffice() {
		teac.getOffice();	
		assertEquals(null, teac.getOffice());	
	}

	@Test
	void testSetOffice() {
		teac.setOffice("edificio f7");	}

	@Test
	void testGetPhone() {
		teac.getPhone();	}

	@Test
	void testSetPhone() {
		teac.setPhone(1234567890);	}

	@Test
	void testValidate() {
		teac.validate();
	}

}
