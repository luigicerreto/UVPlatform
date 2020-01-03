package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
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
import model.SystemAttribute;
import model_uvp.DAORequest;
import model_uvp.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import interfacce.UserInterface;


/**
 * Servlet implementation class Downloader.
 */
@WebServlet("/Downloader")
public class Downloader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private File file;

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
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OutputStream outputStream = null;
		InputStream in = null;
		DAORequest queryobj = new DAORequest();
		List<String> filenames = null;

		int idRequest = Integer.parseInt(request.getParameter("idRequest"));
		
		String basePath = System.getProperty("user.home") + "/" + "git" + "/UVPlatform/uploads/" + idRequest + "/";
		//String basePath = new SystemAttribute().getValueByKey("request-upload-path") + "\\" + idRequest + "\\";
		
		String filename = request.getParameter("filename");
		
		if(filename == null || filename.equals("null")) { // download come zip
			filenames = queryobj.retrieveAttached(idRequest);
			UserInterface u = queryobj.getUserByRequest(idRequest);
			
			response.setContentType("Content-type: text/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename="+u.getName().toUpperCase()+ "_"+u.getSurname().toUpperCase()+"_REQUEST_"+idRequest+".zip");

			List<File> files = new ArrayList<>();

			// Aggiungi allegati
			for(String f : filenames) {
				files.add(new File(basePath+f));
			}

			ServletOutputStream out = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

			for (File file : files) {
				zos.putNextEntry(new ZipEntry(file.getName()));

				// Get the file
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


	/**
	 * method doPost.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({"unchecked", "unused", "rawtypes"})
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
