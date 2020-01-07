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
		app.getEmail();
	}

	@Test
	void testSetEmail() {
		app.setEmail("microsoft@info.it");
	}

	@Test
	void testGetName() {
		app.getName();
	}

	@Test
	void testSetName() {
		app.setName("Bill");
	}

	@Test
	void testGetSurname() {
		app.getSurname();
	}

	@Test
	void testSetSurname() {
		app.setSurname("Gates");
	}

	@Test
	void testGetSex() {
		app.getSex();
	}

	@Test
	void testSetSex() {
	app.setSex('F');
	}

	@Test
	void testGetPassword() {
		app.getPassword();
	}

	@Test
	void testSetPassword() {
		app.setPassword("password1");
	}

	@Test
	void testGetUserType() {
		app.getUserType();
	}

	@Test
	void testSetUserType() {
		app.setUserType(3);
	}

	@Test
	void testGetSerial() {
		app.getSerial();
	}

	@Test
	void testSetSerial() {
		app.setSerial("2838443232");
	}

	@Test
	void testGetOffice() {
		app.getOffice();
	}

	@Test
	void testSetOffice() {
		app.setOffice("via umberto I ");
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
