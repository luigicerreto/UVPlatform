package util;

import java.io.IOException;

import javax.servlet.ServletException;

import model.Attached;
import model_uvp.DAORequest;

/**
 * Questa classe gestisce la notifica allo studente.
 * 
 * @author Antonio Baldi
 *
 */

public class notifyStudent {
	
	
	private String object = "Aggiornamento della richiesta ";
	private String text = "È cambiato lo stato della tua richiesta: ";

	/**
	 * 
	 * Questa funzione gestisce la notifica allo studente dato la mail 
	 * e l'id della richiesta come parametro fa si che allo studente arrivi una mail
	 * con il nuovo stato della richiesta e l'allegato della richiesta.
	 * 
	 * @param mail
	 * @param idRequest
	 * @throws IOException
	 * @throws ServletException
	 */
	public void notify(String mail, int idRequest) throws IOException, ServletException
	{
		Attached file = new Attached();
		DAORequest queryobj = new DAORequest();
		
		object += "n." + idRequest;
		text += queryobj.getStatus(idRequest)+ "\n";
		file = queryobj.retrieveLatestAttached(idRequest);
		String basePath = System.getProperty("user.home") + "/" + "Desktop" + "/uploads/" + idRequest + "/";
		//String basePath = new SystemAttribute().getValueByKey("request-upload-path") + "\\" + idRequest + "\\";
		Mailer.send(mail, object, text, basePath+file.getFilename());
	}
}
