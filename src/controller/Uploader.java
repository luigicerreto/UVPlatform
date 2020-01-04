package controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.json.simple.JSONObject;

import model_uvp.DAORequest;
import model_uvp.DAOUser;
import model_uvp.User;


/**
 * Servlet implementation class Uploader.
 */
@WebServlet("/Uploader")
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * constructor.
	 * @see HttpServlet#HttpServlet()
	 */
	public Uploader() {
		super();
	}

	/**
	 * method doGet.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * method doPost.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isMultipart;
		String basePath;
		int maxFileSize = 50 * 102400;
		int maxMemSize = 4 * 1024;

		Integer result = 0;
		String error = "";
		String content = "";

		DAOUser daouser = new DAOUser();
		User user = null;
		String user_dir = null;
		int id_request;
		
		String param = String.valueOf(request.getSession().getAttribute("idRequest"));

		if(param == null || param.equals("null"))
		{
			id_request = Integer.parseInt(request.getParameter("id_request"));
			user = daouser.getUserByRequestInternship(id_request);
			user_dir = user.getName().toUpperCase() + "_" + user.getSurname().toUpperCase() + "_" + user.getSerial();
			basePath = System.getProperty("user.home") 
					+ "/git/UVPlatform/uploads/internship/" 
					+ user_dir + "/" + id_request + "/";
		} else {
			id_request = Integer.parseInt(param);
			user = daouser.getUserByRequestEV(id_request);
			user_dir = user.getName().toUpperCase() + "_" + user.getSurname().toUpperCase() + "_" + user.getSerial();
			basePath = System.getProperty("user.home") 
					+ "/git/UVPlatform/uploads/english_validation/" 
					+ user_dir + "/" + id_request + "/";
		}

		File file = new File(basePath);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				result = 0;
				error = "Errore creazione cartella per l'upload dei file";
			}
		}

		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		if (!isMultipart) {
			result = 0;
			error = "Nessun file caricato";
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);

		try {
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					String fileName = fi.getName().replaceAll("\\s+", "");
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(basePath + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(basePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					content += fileName;
					result = 1;
				}
			}
		} catch (Exception ex) {
			result = 0;
			error = ex.getMessage();
		}

		JSONObject res = new JSONObject();
		res.put("result", result);
		res.put("error", error);
		res.put("content", content);
		PrintWriter out = response.getWriter();
		out.println(res);
	}
}
