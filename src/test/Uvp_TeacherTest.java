package test;

import static org.junit.jupiter.api.Assertions.*;

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
		teac.getEmail();
	}

	@Test
	void testSetEmail() {
		teac.setEmail("rdeprisco@unisa.it");
	}

	@Test
	void testGetName() {
		teac.getName();
	}

	@Test
	void testSetName() {
		teac.setName("Roberto");
	}

	@Test
	void testGetSurname() {
		teac.getSurname();
	}

	@Test
	void testSetSurname() {
		teac.setSurname("De Prisco");
	}

	@Test
	void testGetSex() {

		teac.getSex();
	}

	@Test
	void testSetSex() {
		teac.setSex('M');
	}

	@Test
	void testGetPassword() {
		teac.getPassword();
	}

	@Test
	void testSetPassword() {
		teac.setPassword("password");
	}

	@Test
	void testGetUserType() {
		teac.getUserType();	}

	@Test
	void testSetUserType() {
		teac.setUserType(3);	
	}

	@Test
	void testGetSerial() {
		teac.getSerial();	}

	@Test
	void testSetSerial() {
		teac.setSerial("1234567890");	}

	@Test
	void testGetOffice() {
		teac.getOffice();	}

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
