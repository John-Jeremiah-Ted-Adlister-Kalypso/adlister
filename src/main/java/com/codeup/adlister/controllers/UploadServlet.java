package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;


import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import static java.lang.Integer.parseInt;

@WebServlet(name = "controllers.UploadServlet", urlPatterns = "/upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "img/uploadFiles";
    SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY HH-mm-ss");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            response.sendRedirect("/login");
        request.getRequestDispatcher("/WEB-INF/upload.jsp").forward(request, response);
    }

    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
//        InputStream theInputStream = new FileInputStream("D:\\video.mp4");
//        try (InputStream is = theInputStream;
//             BufferedInputStream bis = new BufferedInputStream(is);) {
//            AutoDetectParser parser = new AutoDetectParser();
//            Detector detector = parser.getDetector();
//            Metadata md = new Metadata();
//            MediaType mediaType = detector.detect(bis, md);
//            if (mediaType.getType().equals("image")) {
//I think all this code can be used but after the file is on the server i.e. it is not possible to parse prior to below.
        // gets absolute path of the web application
        long ad_id = parseInt(request.getParameter("ad_id"));
        String appPath = request.getServletContext().getRealPath("");
        User user = (User) request.getSession().getAttribute("user");

        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
        String filepath = null;
        String name = null;
//                File file = new File(request.getParameter("file"));

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
//                fileSaveDir
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

//                for (Part part : request.getParts()) {
//                   String fileName = extractFileName(part);
//                    // refines the fileName in case it is an absolute path
//                    fileName = new File(fileName).getName();
////                    fileName.
////                    System.out.println("fileName = " + fileName);
////                    name = fileName;
//                    part.write(savePath + File.separator + fileName);
//                }
//                filepath = savePath + File.separator + fileName;
//                DaoFactory.getImagesDao().uploadImage(filepath, ad_id);

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
            request.setAttribute("message", "Upload has been done successfully!");
            getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(
                    request, response);
        }
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}