package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model_uvp.DAORequest;
import model_uvp.DAOUser;
import model_uvp.User;
import interfacce.UserInterface;


/**
 * Servlet implementation class Downloader.
 */
@WebServlet("/Downloader")
public class Downloader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public Downloader() {
		super();
	}

	/**
	 * method doGet.
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
		

	/**
	 * method doPost.
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OutputStream outputStream = null;
		InputStream in = null;
		DAORequest daoreq = new DAORequest();
		DAOUser daouser = new DAOUser();
		
		User user = null;
		String user_dir = null;
		String basePath = null;
		List<String> filenames = null;

		String flag = request.getParameter("flag");
		Integer id_request = Integer.parseInt(request.getParameter("idRequest"));
		String filename = request.getParameter("filename");
		
		if(flag != null && flag.equals("1")) 
		{
			user = daouser.getUserByRequestInternship(id_request);
			user_dir = user.getName().toUpperCase() + "_" + user.getSurname().toUpperCase() + "_" + user.getSerial();
			basePath = System.getProperty("user.home") 
					+ "/git/UVPlatform/uploads/internship/" 
					+ user_dir + "/" + id_request + "/";
		} else {
			user = daouser.getUserByRequestEV(id_request);
			user_dir = user.getName().toUpperCase() + "_" + user.getSurname().toUpperCase() + "_" + user.getSerial();
			basePath = System.getProperty("user.home") 
					+ "/git/UVPlatform/uploads/english_validation/" 
					+ user_dir + "/" + id_request + "/";
		}
		
		if(filename == null || filename.equals("null")) { // download come zip
			filenames = daoreq.retrieveAttached(id_request);
			
			response.setContentType("Content-type: text/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + user_dir + "_IDREQ_" + id_request + ".zip");

			List<File> files = new ArrayList<>();

			// Aggiungi allegati
			for(String f : filenames) {
				files.add(new File(basePath+f));
			}

			ServletOutputStream out = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

			for (File file : files) {
				zos.putNextEntry(new ZipEntry(file.getName()));

				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);

				} catch (FileNotFoundException fnfe) {
					zos.write(("ERRORld not find file " + file.getName()).getBytes());
					zos.closeEntry();
					continue;
				}

				BufferedInputStream fif = new BufferedInputStream(fis);

				// Write the contents of the file
				int data = 0;
				while ((data = fif.read()) != -1) {
					zos.write(data);
				}
				fif.close();
				zos.closeEntry();
			}
			zos.close();
		}
		else { // download diretto
			try {
				in = new FileInputStream(basePath + filename);
				byte[] buffer = new byte[1024];
				int bytesRead = 0;
				response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
				outputStream = response.getOutputStream();
				while (0 < (bytesRead = in.read(buffer))) {
					outputStream.write(buffer, 0, bytesRead);
				}
			} finally {
				if (null != in) {
					in.close();
				}
			}
		}
	}
}
