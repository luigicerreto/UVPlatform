package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.Downloader;
import model.Attached;
import model.SystemAttribute;
import model_uvp.DAORichiesta;
import model_uvp.RequestInternship;

/**
 * Questa classe gestisce la notifica allo studente.
 * 
 * @author Antonio Baldi
 *
 */

public class notifyStudent {
	
	
	final private String object = "Stato della richiesta";
	private String text = "La tua richiesta è ";

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
		String id = String.valueOf(idRequest);
		Attached file = new Attached();
		DAORichiesta queryobj = new DAORichiesta();
		
		text+=queryobj.checkStatus(idRequest)+"\n";
		file = queryobj.retriveAttached(idRequest);
		String basePath = 
		        new SystemAttribute().getValueByKey("request-upload-path") + "\\" + idRequest + "\\";
		System.out.println("Il percorso è "+basePath+file.getFilename());
		Mailer.send(mail,object,text,basePath+file.getFilename());  
		
		
	}

}
