package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model_uvp.User;

class Uvp_UserTest {
	static User app;
	@Test
	void testUser() {
		User app2 = new User();
	}

	@BeforeAll
	static void testUserConstructor() {
		app = new User("a.baldi20@studenti.unisa.it", "Antonio", "Baldi", 'M', "password", 0, "0512105521", "3922412978");
	}

	@Test
	void testGetSerial() {
		app.getSerial();
	}

	@Test
	void testSetSerial() {
		app.setSerial("0512105521");
	}

	@Test
	void testGetPhone() {
		app.getPhone();
	}

	@Test
	void testSetPhone() {
		app.setPhone("3928317821");
	}

	@Test
	void testGetEmail() {
		app.getEmail();
	}

	@Test
	void testGetName() {
		app.getName();
	}

	@Test
	void testGetSurname() {
		app.getSurname();
	}

	@Test
	void testGetSex() {
		app.getSex();
	}

	@Test
	void testGetPassword() {
		app.getPassword();
	}

	@Test
	void testGetUserType() {
		app.getUserType();
	}

	@Test
	void testSetEmail() {
		app.setEmail("a.baldi21@studenti.unisa.it");
	}

	@Test
	void testSetName() {
		app.setName("michele");
	}

	@Test
	void testSetSurname() {
		app.setSurname("pirro");
	}

	@Test
	void testSetSex() {
		app.setSex('F');
	}

	@Test
	void testSetPassword() {
		app.setPassword("password1");
	}

	@Test
	void testSetUserType() {
		app.setUserType(2);
	}

	@Test
	void testGetOffice() {
		app.getOffice();
	}

	@Test
	void testSetOffice() {
		app.setOffice("office");
	}

	@Test
	void testValidate() {
		app.validate();
	}

}
