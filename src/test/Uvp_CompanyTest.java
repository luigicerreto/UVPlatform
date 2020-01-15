package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model_uvp.Company;

class Uvp_CompanyTest {
	 static Company app;
	@BeforeAll
	static void testConstructorCompany() {
	 app = new Company("info@microsoft.it", "", "", 'M', "passowrd",4);
	}

	@Test
	void testCompany() {
		Company app2 = new Company();
	}

	@Test
	void testGetEmail() {
		assertEquals("info@microsoft.it", app.getEmail());

	}

	@Test
	void testSetEmail() {
		app.setEmail("111info@microsoft.it");
		assertEquals("111info@microsoft.it", app.getEmail());
	}

	@Test
	void testGetName() {
		assertEquals("", app.getName());
	}

	@Test
	void testSetName() {
		app.setName("ciao");
		assertEquals("ciao", app.getName());
	}

	@Test
	void testGetSurname() {
		assertEquals("", app.getSurname());
	}

	@Test
	void testSetSurname() {
		app.setSurname("Buongiorno2");
		assertEquals("Buongiorno2", app.getSurname());
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
	void atestGetPassword() {
		app.setPassword("password3");
		assertEquals("password3", app.getPassword());
	}

	@Test
	void testSetPassword() {
		app.setPassword("password2");
		assertEquals("password2", app.getPassword());
	}

	@Test
	void btestGetUserType() {
		assertEquals(4, app.getUserType());
	}

	@Test
	void testSetUserType() {
		app.setUserType(5);	
		assertEquals(5, app.getUserType());
	}

	@Test
	void testGetSerial() {
		app.setSerial("1234567890");	
		assertEquals("1234567890", app.getSerial());
		}

	@Test
	void testSetSerial() {
		app.setSerial("12345678920");	
		assertEquals("12345678920", app.getSerial());
	}

	@Test
	void ctestGetOffice() {
		app.getOffice();	
		assertEquals(null, app.getOffice());
	}

	@Test
	void testSetOffice() {
		app.setOffice("edificio f7");
	}

	@Test
	void testGetPhone() {
		app.getPhone();
	}

	@Test
	void testSetPhone() {
		app.setPhone(329323293);
	}

	@Test
	void testValidate() {
app.validate();
	}

}
