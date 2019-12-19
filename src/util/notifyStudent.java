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
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest();
		servlet = new Downloader();
		String id = String.valueOf(idRequest);
		request.addParameter("idRequest", id);
		
		
		Attached file = new Attached();
		DAORichiesta queryobj = new DAORichiesta();
		text+=queryobj.checkStatus(idRequest);
		file = queryobj.retriveAttached(idRequest);
		if(file.equals(null))
		{
			Mailer.send(mail,object,text,"");  
		}
		else
		{
			request.addParameter("filename", file.getFilename());
			System.out.println("il file name è "+file.getFilename());
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString());
			Mailer.send(mail,object,text,file.getFilename());  
		}
	}

}
