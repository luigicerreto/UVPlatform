package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import model_uvp.DAOInternship;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.Internship;

class uvp_DAOInternshipTest {
	DAOInternship app;
	@BeforeEach
	void setup()
	{
		app = new DAOInternship();
	}

	@Test
	void testViewInternalInternships() {
		app.viewInternalInternships();
	}

	@Test
	void testViewExternalInternships() {
		app.viewExternalInternships();
	}
	@Test
	void testGetInternshipDataFail()
	{
		app.getInternshipData(0, 0);
	}
	@Test
	void testGetInternshipDataInternal() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String insert = "INSERT INTO internship_i VALUES (111, 'ROBERTO DE PRISCO', 'PROGETTAZIONE ALGORITMI', 10, 'POO', 'IMPARARE GLI ALGORITMI', 'rdeprisco@unisa.it')";
		statement= con.prepareStatement(insert);
		statement.executeUpdate();
		Internship result;
		result = app.getInternshipData(111, 0);
		assertEquals(111, result.getId());
		assertEquals(10, result.getAvailability());
		String delete = "DELETE FROM internship_i WHERE ID_II = 111";
		statement = con.prepareStatement(delete);
		statement.executeUpdate();
		con.commit();
	}
	@Test
	void testGetInternshipDataExternal() throws SQLException {
		//TODO
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String insert = "INSERT INTO internship_e VALUES (111, 'kineton', 3, '2019-01-12', 20, 'IMPARARE GLI ALGORITMI', 'info@kineton.it')";
		statement= con.prepareStatement(insert);
		statement.executeUpdate();
		Internship result;
		result = app.getInternshipData(111, 1);
		assertEquals(111, result.getId());
		assertEquals(20, result.getAvailability());
		String delete = "DELETE FROM internship_e WHERE ID_IE = 111";
		statement = con.prepareStatement(delete);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testGetCompanyEmailByExternal() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String insert = "INSERT INTO internship_e VALUES (111, 'kineton', 3, '2019-01-12', 20, 'IMPARARE GLI ALGORITMI', 'info@kineton.it')";
		statement= con.prepareStatement(insert);
		statement.executeUpdate();
		String result=app.getCompanyEmailByExternal(111);
		assertEquals("info@kineton.it",result);
		String delete = "DELETE FROM internship_e WHERE ID_IE = 111";
		statement = con.prepareStatement(delete);
		statement.executeUpdate();	
		con.commit();
		}

	@Test
	void testGetTeacherEmailByInternal() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String insert = "INSERT INTO internship_i VALUES (111, 'ROBERTO DE PRISCO', 'PROGETTAZIONE ALGORITMI', 10, 'POO', 'IMPARARE GLI ALGORITMI', 'rdeprisco@unisa.it')";
		statement= con.prepareStatement(insert);
		statement.executeUpdate();
		String result=app.getTeacherEmailByInternal(111);
		assertEquals("rdeprisco@unisa.it", result);
		String delete = "DELETE FROM internship_i WHERE ID_II = 111";
		statement = con.prepareStatement(delete);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testaddInternshipFail()
	{
		app.addInternship(new Internship(), 0);
	}
	@Test
	void testaddInternshipPassInternal() throws SQLException
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		InternalInternship tirocinio = new InternalInternship();
		tirocinio.setTheme("test");
		tirocinio.setTutor_name("Roberto de prisco");
		tirocinio.setAvailability(10);
		tirocinio.setResources("nessuna come sempre");
		tirocinio.setGoals("sclero");
		tirocinio.setFk_tutor("rdeprisco@unisa.it");

		app.addInternship(tirocinio, 0);

		String deleteInternship = "DELETE FROM internship_i WHERE RESOURCES = 'nessuna come sempre'";
		statement = con.prepareStatement(deleteInternship);
		statement.executeUpdate();
		con.commit();




	}
	@Test
	void testaddInternshipPassExternal() throws SQLException
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ExternalInternship tirocinio = new ExternalInternship();
		tirocinio.setName("niente");
		tirocinio.setDuration_convention(3);
		tirocinio.setDate_convention(new java.util.Date(2019, 01, 01));
		tirocinio.setAvailability(10);
		tirocinio.setInfo("niente");
		tirocinio.setFk_tutor("rdeprisco@unisa.it");
		app.addInternship(tirocinio, 1);

		String deleteInternship = "DELETE FROM internship_e WHERE INFO = 'niente'";
		statement = con.prepareStatement(deleteInternship);
		statement.executeUpdate();
		con.commit();




	}



}
