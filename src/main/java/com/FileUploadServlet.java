package com;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String UPLOAD_DIR = "uploadedFiles";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*write your code here*/
		String applicationPath = getServletContext().getRealPath("");
		String uploadPath = applicationPath + File.separator + UPLOAD_DIR;

		File fileUploadDirectory = new File(uploadPath);
		if (!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}

		String fileName = "";
		UploadDetail details = null;
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();

		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			details = new UploadDetail();
			details.setFileName(fileName);
			details.setFileSize(part.getSize() / 1024);
			try {
				part.write(uploadPath + File.separator + fileName);
				details.setUploadStatus("Success");
			} catch (IOException ioObj) {
				details.setUploadStatus("Failure : "+ ioObj.getMessage());
			}
			fileList.add(details);
		}

		request.setAttribute(UPLOAD_DIR, fileList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/fileuploadResponse.jsp");
		dispatcher.forward(request, response);
	}

	/***** Helper Method #1 - This Method Is Used To Read The File Names *****/
	private String extractFileName(Part part) {
		String fileName = "";
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf('=') + 2, item.length() - 1);
			}
		}
		return fileName;
	}

	
}