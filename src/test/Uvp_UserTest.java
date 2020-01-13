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
	void atestGetSerial() {
		assertEquals("0512105521", app.getSerial());
	}

	@Test
	void testSetSerial() {
		app.setSerial("0123456789");
		assertEquals("0123456789", app.getSerial());
	}

	@Test
	void testGetPhone() {
		assertEquals("3922412978", app.getPhone());

	}

	@Test
	void testSetPhone() {
		app.setPhone("392241234");
		assertEquals("392241234", app.getPhone());
	}

	@Test
	void testGetEmail() {
		assertEquals("a.baldi20@studenti.unisa.it", app.getEmail());
	}

	@Test
	void testSetEmail() {
		app.setEmail("a.baldi30@studenti.unisa.it");
		assertEquals("a.baldi30@studenti.unisa.it", app.getEmail());
	}

	@Test
	void testGetName() {
		assertEquals("Antonio", app.getName());

	}

	@Test
	void testSetName() {
		app.setName("Roberto");
		assertEquals("Roberto", app.getName());
	}

	@Test
	void testGetSurname() {
		assertEquals("Baldi", app.getSurname());

	}

	@Test
	void testSetSurname() {
		app.setSurname("Pirro");
		assertEquals("Pirro", app.getSurname());
	}

	@Test
	void testGetSex() {
		assertEquals('M', app.getSex());

	}

	@Test
	void testSetSex() {
		app.setSex('F');
		assertEquals('F', app.getSex());
	}

	@Test
	void btestGetPassword() {
		app.setPassword("password");
		assertEquals("password", app.getPassword());
	}

	@Test
	void testSetPassword() {
		app.setPassword("password3");
		assertEquals("password3", app.getPassword());
	}

	@Test
	void testGetUserType() {
		app.setUserType(0);
		assertEquals(0, app.getUserType());
	}

	@Test
	void testSetUserType() {
		app.setUserType(5);	
		assertEquals(5, app.getUserType());
	}

	@Test
	void testValidate() {
		app.validate();
	}

}
