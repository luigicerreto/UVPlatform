package util;

import java.io.IOException;

import javax.servlet.ServletException;

import model.Attached;
import model_uvp.DAORequest;
import model_uvp.DAOUser;
import model_uvp.User;

/**
 * Questa classe gestisce la notifica allo studente.
 * 
 * @author Antonio Baldi
 *
 */

public class Notifier {
	/**
	 * 
	 * Questa funzione gestisce la notifica allo studente dato la mail 
	 * e l'id della richiesta come parametro fa si che allo studente arrivi una mail
	 * con il nuovo stato della richiesta e l'allegato della richiesta.
	 * 
	 * @param email
	 * @param idRequest
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void notifyStudent(String email, int idRequest) {
		Attached file = new Attached();
		DAORequest queryobj = new DAORequest();
		DAOUser daouser = new DAOUser();
		
		String object = "Aggiornamento della richiesta ";
		String text = "È cambiato lo stato della tua richiesta: ";
		
		object += "n." + idRequest;
		text += queryobj.getStatus(idRequest)+ "\n";
		file = queryobj.retrieveLatestAttached(idRequest);
		
		User user = daouser.getUser(email);
		String user_dir = user.getName().toUpperCase() + "_" + user.getSurname().toUpperCase() + "_" + user.getSerial();
		String basePath = System.getProperty("user.home") 
				+ "/git/UVPlatform/uploads/internship/" 
				+ user_dir + "/" + idRequest + "/";
		
		Mailer.send(email, object, text, basePath+file.getFilename());
	}
	
	public static void notifyNewUser(User u) {
		String object = "Benvenuto su UVP!";
		String text = "<strong>" + u.getName() +", ti diamo il benvenuto su <em>Unisa Validation Platform</em>!</strong><br><br>"
				+ "<strong>Le tue credenziali di accesso:</strong><br>"
				+ "<strong>Email: </strong><em>" + u.getEmail() + "</em><br>"
				+ "<strong>Password: </strong><em>" + u.getPassword() + "</em><br><br>";
		Mailer.send(u.getEmail(), object, text);
	}
}
