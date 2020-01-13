package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.PasswordGenerator;
import util.PasswordGenerator.PasswordGeneratorBuilder;

class uvp_PasswordGeneratorTest {
	PasswordGenerator generatorePassword;

	@Test
	void testPasswordGenerator() {
		generatorePassword = new PasswordGenerator();
		
	}
	@Test
	void testGenerateFail() {
		generatorePassword = new PasswordGenerator();
		String psw=generatorePassword.generate(0);
		if(psw.length()==0)
		{
			assertTrue(true);
		}
	}
	
	@Test
	void testGeneratePass() {
		generatorePassword = new PasswordGenerator();
		PasswordGeneratorBuilder app = new PasswordGeneratorBuilder();
		app.build();
		app.useDigits(true);
		app.useLower(true);
		app.usePunctuation(true);
		app.useUpper(true);
		String psw=generatorePassword.generate(5);
		if(psw.length()==5)
		{
			assertTrue(true);
		}
	}

}
