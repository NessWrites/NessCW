/*
 * 
 * Author: Nischhal Shrestha
 * email: np01ai4s230009@islingtoncollege.edu.np
 * Project Name: Besto Friendo
 * Islington College, KamalPokhari
 * LondonMet ID: 22085857
 * Section: AI-3 
 * */

package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.database.DataBaseController;
import model.PostModel;
import model.ProfilePicModel;
import model.UserPostModel;
import util.StringUtil;

/**
 * Servlet implementation class ProfilePicUpdateServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtil.SERVLET_URL_UPDATE_IMAGE })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, // 3MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class ProfilePicUpdateServlet extends HttpServlet {
	DataBaseController dbController = new DataBaseController();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public ProfilePicUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * This method handles GET requests. It appends the context path of the request to a response message.
	 *
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the GET could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the GET request.
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	/**
	 * This method handles POST requests. It retrieves the user ID from the session
	 *  and the profile picture from the request.
	 * It then creates a ProfilePicModel object with the user ID and profile picture.
	 * The method saves the profile picture to a specified path if it's not empty.
	 * It then calls the userImage method of the dbController to save the profile picture to the database.
	 * If the profile picture is successfully saved, it sets the profile picture
	 *  as a session attribute and redirects the user to the profile page.
	 * If the profile picture is not saved, it sends a failure response to the user.
	 *
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the POST could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the POST request.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userID = (Integer) session.getAttribute("userID");
		System.out.println("userID"+ userID);
		
		Part profilePic = request.getPart("profilePic");//name in jsp file
		ProfilePicModel profilePicModel = new ProfilePicModel(userID, profilePic);//

		String savePath = StringUtil.IMAGE_DIR_USER;
	    String fileName = profilePicModel.getImageUrlFromPart();
	    System.out.println("filepath:"+ fileName.toString());

	    if(!fileName.isEmpty() && fileName != null) {
    		
	    	profilePic.write(savePath + fileName);
	    }
		String result = dbController.userImage(profilePicModel);

		if (result  != null && !result.isEmpty() && result!= "-1") {	
			session.setAttribute("profileImage", fileName);
			response.getWriter().println("Profile Picture Updated successfully!");
			response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
			
		}
		else {
			// Send failure response
			response.getWriter().println("Failed to post. Server Error! Try again.");
		}

	}

}
