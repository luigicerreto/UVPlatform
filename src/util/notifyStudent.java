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

public class notifyStudent {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private Downloader servlet;
	final private String object = "Stato della richiesta";
	private String text = "La tua richiesta è ";

	public void notify(String mail, int idRequest) throws IOException, ServletException
	{
		String id = String.valueOf(idRequest);
		Attached file = new Attached();
		DAORichiesta queryobj = new DAORichiesta();
		
		text+=queryobj.checkStatus(idRequest);
		file = queryobj.retriveAttached(idRequest);
		String basePath = 
		        new SystemAttribute().getValueByKey("request-upload-path") + "\\" + idRequest + "\\";
		System.out.println("Il percorso è "+basePath+file.getFilename());
		Mailer.send(mail,object,text,basePath+file.getFilename());  
		
		
	}

}
