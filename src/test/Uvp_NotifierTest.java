package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import controller.DbConnection;
import model_uvp.User;
import util.Mailer;
import util.Notifier;

class Uvp_NotifierTest {

	@Test
	void testNotifyStudentFail()
	{
		
		assertThrows(NullPointerException.class, () -> Notifier.notifyStudent("", 0));

	}
	@Test
	void testNotifyStudentPass() throws SQLException {
		Connection con = new DbConnection().getInstance().getConn();
		PreparedStatement statement;
		String addUser = "INSERT INTO user VALUES ('a.baldi20@studenti.unisa.it', 'Antonio', 'Baldi', 'M', 'password', 0, '0512105521', 'null', 'null' )";
		String addUser2 = "INSERT INTO user VALUES ('slatorre@unisa.it', 'Salvatore', 'La torre', 'M', 'password', 3, '92372', 'f2', '8233923932')";
		String addInternship = "INSERT INTO internship_i VALUES ('111', 'Salvatore la torre', 'JAVA' , 20, 'risorse', 'usare poo', 'slatorre@unisa.it')";
		String addRequest = "INSERT INTO request_internship VALUES ('111', 0, 'In attesa', 'a.baldi20@studenti.unisa.it', 'slatorre@unisa.it', null, 111)";
		String addAttached = "INSERT INTO attached VALUES (111, 'prova', null, 'a.baldi20@studenti.unisa.it', 111)";
		statement = con.prepareStatement(addUser);
		statement.executeUpdate();
		statement = con.prepareStatement(addUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(addInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(addRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(addAttached);
		statement.executeUpdate();
		con.commit();
		assertThrows(RuntimeException.class, () -> Notifier.notifyStudent("a.baldi20@studenti.unisa.it", 111));

		
		
		String DeleteAttached = "DELETE FROM attached WHERE ID_ATTACHED = 111";
		String DeleteRequest = "DELETE FROM request_internship WHERE ID_REQUEST_I = 111";
		String DeleteInternship = "DELETE FROM internship_i WHERE ID_II = 111";
		String DeleteUser2 = "DELETE FROM user WHERE email = 'slatorre@unisa.it'";
		String DeleteUser = "DELETE FROM user WHERE email = 'a.baldi20@studenti.unisa.it'";
		statement = con.prepareStatement(DeleteAttached);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteRequest);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteInternship);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser2);
		statement.executeUpdate();
		statement = con.prepareStatement(DeleteUser);
		statement.executeUpdate();
		con.commit();
	}

	@Test
	void testNotifyNewUserFail() {
		
		assertThrows(NullPointerException.class, () -> Notifier.notifyNewUser(new User()));
	}
	@Test
	void testNotifyNewUserPass() {
		Notifier.notifyNewUser(new User("r.dipalma22@studenti.unisa.it", "Rosario", "Di Palma", 'M', "password", 0, "0512105653", "333michele"));
	}

}
