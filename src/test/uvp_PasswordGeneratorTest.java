package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.PasswordGenerator;

class uvp_PasswordGeneratorTest {
	PasswordGenerator generatorePassword;

	@Test
	void testPasswordGenerator() {
		generatorePassword = new PasswordGenerator();
	}

	@Test
	void testGenerate() {
		generatorePassword = new PasswordGenerator();
		String psw=generatorePassword.generate(5);
		if(psw.length()==5)
		{
			assertTrue(true);
		}
	}

}
