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
import java.time.LocalDate;
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
import model.UserPostModel;
import util.StringUtil;
//@WebServlet(asyncSupported = true, urlPatterns = { StringUtil.SERVLET_URL_POST })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, // 3MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
@WebServlet(urlPatterns = { StringUtil.SERVLET_URL_POST }, asyncSupported = true)
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataBaseController dbController = new DataBaseController();

	public PostServlet() {
		super();

	}

	@Override
	/**
	 * This method handles POST requests. It retrieves the posting content and image part from the request.
	 * It then creates a UserPostModel object with the posting content, user ID, and image part.
	 * The method saves the image part to a specified path if it's not empty.
	 * It then calls the userPosts method of the dbController to save the post to the database.
	 * If the post is successfully saved, it retrieves all user posts, sets them as session attributes, 
	 * and redirects the user to the home page.
	 * If the post is not saved, it sends a failure response to the user.
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the POST could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the POST request.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws
			ServletException, IOException {
		String postingContent = request.getParameter("posting");
		//System.out.println("posting from JSP " + postingContent);
		//String contentImageString = request.getParameter("contentImageString");
		HttpSession session = request.getSession(false);
		int userID = (Integer) session.getAttribute("userID");
		Part imagePart = request.getPart("contentImageString");//name in jsp file

		UserPostModel userPostModel = new UserPostModel(postingContent, userID, imagePart);//
		String savePath = StringUtil.IMAGE_DIR_POST_CONTENTS;
	    String fileName = userPostModel.getImageUrlFromPart();
	    
	    if(!fileName.isEmpty() && fileName != null) {	
	    	imagePart.write(savePath + fileName);
	    }
		int result = dbController.userPosts(userPostModel);
		if (result > 0) {
			//call  method here
			ArrayList<PostModel> userPosts = dbController.getAllUsersPosts();
			if (userPosts != null && !userPosts.isEmpty()) {
				session.setAttribute("postLists", userPosts);
				
				//resp.sendRedirect(req.getContextPath() + "/pages/home.jsp");
			}
			response.getWriter().println("Post creation successful!");
			response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
		}
		else {
			// Send failure response
			response.getWriter().println("Failed to post. Server Error! Try again.");
		}

	}
	/**
	 * This method handles GET requests. It retrieves all user posts from the database.
	 * If there are no posts, it prints a message to the console.
	 * If there are posts, it sets them as request attributes.
	 *
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the GET could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the GET request.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws

			ServletException, IOException {
		ArrayList<PostModel> userPosts = dbController.getAllUsersPosts();
		if (userPosts.isEmpty()) {
			System.out.println("Follow users to get feed");
		} else {
			System.out.println("Post Servlet: Post has posts");
			request.setAttribute("postLists", userPosts);
		}
	}

}
