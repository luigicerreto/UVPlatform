package controller_uvp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model_uvp.DAORichiesta;

/**
 * Servlet implementation class updateStateToSegreteria
 */
@WebServlet("/forwardToSecretary")
public class forwardToSecretary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forwardToSecretary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer result = 0;
		String error = "";
		String content = "";
		String redirect = "";
		
		int id_request = (Integer.parseInt(request.getParameter("id_request")));
		DAORichiesta queryobj = new DAORichiesta();
		
		if(queryobj.acceptByTeach_Company(id_request))
		{
			result = 1;
			content = "Richiesta accettata";
		}
		else
		{
			result = 0;
			error = "Errore nell'elaborazione della richiesta";
		}
		
		
		JSONObject res = new JSONObject();
		
		res.put("result", result);
		res.put("error", error);
		res.put("content", content);
		res.put("redirect", redirect);
		PrintWriter out = response.getWriter();
		out.println(res);
		response.setContentType("json");
	}

}
