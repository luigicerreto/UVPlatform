package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.cj.protocol.Resultset;

import controller.DbConnection;
import model_uvp.DAOInternship;
import model_uvp.ExternalInternship;
import model_uvp.InternalInternship;
import model_uvp.Internship;
import model_uvp.User;

class uvp_DAOInternshipTest {
	DAOInternship app;
	@BeforeEach
	void setup()
	{
		app = new DAOInternship();
	}

	@Test
	void testViewInternalInternshipsFail() {
		app.viewInternalInternships();
	}
	@Test
	void testViewInternalInternshipsPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addUser= "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternship);
		statement.executeUpdate();
		con.commit();

		@SuppressWarnings("unchecked")
		ArrayList<InternalInternship> tirocinio =  (ArrayList<InternalInternship>) ((ArrayList<?>) app.viewInternalInternships());

		for(InternalInternship a : tirocinio)
		{
			if(a.getId()==111)
			{
				assertEquals(111, a.getId());
				assertEquals("Salvatore la torre", a.getTutor_name());
				assertEquals("JAVA", a.getTheme());
				assertEquals(20,a.getAvailability());
				assertEquals("risorse",a.getResources());
				assertEquals("usare poo",a.getGoals());

			}
		}
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser= "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(DeleteInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();


	}

	@Test
	void testViewExternalInternshipsFail() {
		app.viewExternalInternships();
	}
	@Test
	void testViewExternalInternshipsPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addUser = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";

		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		con.commit();
		ExternalInternship result;
		result = (ExternalInternship) app.getInternship(111, 1);
		assertEquals(111, result.getId());
		assertEquals("Salvatore la torre", result.getName());
		assertEquals(3, result.getDuration_convention());
		assertEquals("2019-01-01",result.getDate_convention().toString());
		assertEquals(30,result.getAvailability());
		assertEquals("usare poo" ,result.getInfo());
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(DeleteInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}
	@Test
	void testGetInternshipDataFail()
	{
		app.getInternship(0, 0);
	}
	@Test
	void testGetInternshipDataInternal() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addUser = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternship);
		statement.executeUpdate();
		con.commit();
		InternalInternship result;
		result = (InternalInternship) app.getInternship(111, 0);
		assertEquals(111, result.getId());
		assertEquals("Salvatore la torre", result.getTutor_name());
		assertEquals("JAVA", result.getTheme());
		assertEquals(20,result.getAvailability());
		assertEquals("risorse",result.getResources());
		assertEquals("usare poo",result.getGoals());
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(DeleteInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}
	@Test
	void testGetInternshipDataExternal() throws SQLException {
		//TODO
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		String addUser = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";

		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		con.commit();
		ExternalInternship result;
		result = (ExternalInternship) app.getInternship(111, 1);
		assertEquals(111, result.getId());
		assertEquals("Salvatore la torre", result.getName());
		assertEquals(3, result.getDuration_convention());
		assertEquals("2019-01-01",result.getDate_convention().toString());
		assertEquals(30,result.getAvailability());
		assertEquals("usare poo" ,result.getInfo());
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(DeleteInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
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
		ResultSet result;
		String addUser = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		con.commit();
		tirocinio.setTheme("test");
		tirocinio.setTutor_name("Salvatore La torre");
		tirocinio.setAvailability(10);
		tirocinio.setResources("nessuna come sempre");
		tirocinio.setGoals("sclero");
		tirocinio.setFk_tutor("slatorre@unisa.it");

		app.addInternship(tirocinio, 0);
		String select="SELECT I.ID_II, I.TUTOR_NAME, I.THEME, I.AVAILABILITY, I.RESOURCES, I.GOALS, U.OFFICE " + 
				"FROM INTERNSHIP_I AS I INNER JOIN USER AS U ON I.FK_TUTOR = U.EMAIL WHERE RESOURCES = 'nessuna come sempre'";

		statement = con.prepareStatement(select);
		result = statement.executeQuery();
		while(result.next())
		{
			tirocinio.setId(result.getInt(1));
			tirocinio.setTutor_name(result.getString(2));
			tirocinio.setTheme(result.getString(3));
			tirocinio.setAvailability(result.getInt(4));
			tirocinio.setResources(result.getString(5));
			tirocinio.setGoals(result.getString(6));
		}

		assertEquals("Salvatore La torre", tirocinio.getTutor_name());
		assertEquals("test", tirocinio.getTheme());
		assertEquals(10,tirocinio.getAvailability());
		assertEquals("nessuna come sempre",tirocinio.getResources());
		assertEquals("sclero",tirocinio.getGoals());

		String deleteInternship = "DELETE FROM internship_i WHERE RESOURCES = 'nessuna come sempre'";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";

		statement = con.prepareStatement(deleteInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();


	}

	@Test
	void testaddInternshipPassExternal() throws SQLException
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ResultSet result;
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 4, '92372', 'f2', '8233923932')";
		ExternalInternship tirocinio = new ExternalInternship();
		tirocinio.setName("niente");
		tirocinio.setDuration_convention(3);
		tirocinio.setDate_convention(new Date(119,0,01));
		tirocinio.setAvailability(10);
		tirocinio.setInfo("niente");
		tirocinio.setFk_tutor("slatorre@unisa.it");
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		con.commit();
		app.addInternship(tirocinio, 1);

		
		String select="SELECT E.ID_IE, E.NAME, E.DURATION_CONVENTION, E.DATE_CONVENTION, E.AVAILABILITY, E.INFO, U.EMAIL, U.OFFICE FROM INTERNSHIP_E AS E INNER JOIN USER AS U ON E.FK_TUTOR = U.EMAIL WHERE U.EMAIL = ?";
		statement = con.prepareStatement(select);
		statement.setString(1, "slatorre@unisa.it");
		result = statement.executeQuery();
		while(result.next())
		{
			tirocinio.setId(result.getInt(1));
			tirocinio.setName(result.getString(2));
			tirocinio.setDuration_convention(result.getInt(3));
			tirocinio.setDate_convention(result.getDate(4));
			tirocinio.setAvailability(result.getInt(5));
			tirocinio.setInfo(result.getString(6));
		}
		
		assertEquals("niente", tirocinio.getName());
		assertEquals(3, tirocinio.getDuration_convention());
		assertEquals("2019-01-01",tirocinio.getDate_convention().toString());
		assertEquals(10,tirocinio.getAvailability());
		assertEquals("niente" ,tirocinio.getInfo());
		
		String deleteInternship = "DELETE FROM internship_e WHERE INFO = 'niente'";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(deleteInternship);
		statement.executeUpdate();
		con.commit();
	}
	
	@Test
	void testgetInternshipByEmailFail() throws SQLException
	{
		app.getInternshipByEmail("", 5);
	}
	
	@Test
	void testgetInternshipByEmailExternalPass() throws SQLException
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		ExternalInternship tirocinio;
		String addUser= "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		con.commit();
		

		tirocinio =  (ExternalInternship) app.getInternshipByEmail("slatorre@unisa.it", 1);

		assertEquals(111, tirocinio.getId());
		assertEquals("Salvatore la torre", tirocinio.getName());
		assertEquals(3, tirocinio.getDuration_convention());
		assertEquals("2019-01-01",tirocinio.getDate_convention().toString());
		assertEquals("usare poo" ,tirocinio.getInfo());

		String delete = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(delete);
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		statement.executeUpdate();
		con.commit();
	}
	@Test
	void testgetInternshipByEmailInternalPass() throws SQLException
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		InternalInternship tirocinio;
		String addUser= "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternship);
		statement.executeUpdate();
		con.commit();
		

		tirocinio =  (InternalInternship) app.getInternshipByEmail("slatorre@unisa.it", 0);

		assertEquals(111, tirocinio.getId());
		assertEquals("Salvatore la torre", tirocinio.getTutor_name());
		assertEquals("JAVA", tirocinio.getTheme());
		assertEquals(20,tirocinio.getAvailability());
		assertEquals("risorse",tirocinio.getResources());
		assertEquals("usare poo",tirocinio.getGoals());

		String delete = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(delete);
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		statement.executeUpdate();
		con.commit();
	}
	
	@Test
	void testgetTutorFail() throws SQLException
	{
		app.getTutor(0, 4);
	}
	
	@Test
	void testgetTutorPassInternal() throws SQLException
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		User result;
		String addUser= "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternship);
		statement.executeUpdate();
		con.commit();
		
		result = app.getTutor(111, 0);
		
		assertEquals("slatorre@unisa.it",result.getEmail());
		assertEquals("Salvatore",result.getName());
		assertEquals("La torre",result.getSurname());
		assertEquals('M',result.getSex());
		assertEquals("password",result.getPassword());
		assertEquals(3,result.getUserType());
		assertEquals("92372",result.getSerial());
		
		
		
		
		String delete = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(delete);
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		statement.executeUpdate();
		con.commit();
		
	}
	
	@Test
	void testgetTutorPassExternal() throws SQLException
	{
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement = null;
		User result;
		String addUser= "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternshipEx = "INSERT INTO internship_e VALUES ('111', 'Salvatore la torre', 3 , '2019-01-01', 30, 'usare poo', 'slatorre@unisa.it')";
		statement= con.prepareStatement(addUser);
		statement.executeUpdate();
		statement= con.prepareStatement(addInternshipEx);
		statement.executeUpdate();
		con.commit();
		
		result = app.getTutor(111, 1);
		
		assertEquals("slatorre@unisa.it",result.getEmail());
		assertEquals("Salvatore",result.getName());
		assertEquals("La torre",result.getSurname());
		assertEquals('M',result.getSex());
		assertEquals("password",result.getPassword());
		assertEquals(3,result.getUserType());
		assertEquals("92372",result.getSerial());
		
		
		
		
		String delete = "DELETE FROM internship_e WHERE ID_IE = 111";
		String DeleteUser = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		statement = con.prepareStatement(delete);
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		statement.executeUpdate();
		con.commit();
		
	}



}
