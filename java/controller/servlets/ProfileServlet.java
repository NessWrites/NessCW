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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.database.DataBaseController;
import model.PostModel;
import model.UserModel;
import model.UserPostModel;
import util.StringUtil;

@WebServlet(urlPatterns = { StringUtil.SERVLET_PROFILE }, asyncSupported = true)
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataBaseController dbController = new DataBaseController();

	public ProfileServlet() {
		super();

	}
	/**
	 * This method handles POST requests. It retrieves the user ID and display name from the session.
	 * It then retrieves the user's posts and details from the database and sets them as session attributes.
	 * The method also prints a success message to the console for each post retrieved.
	 * Finally, it sends a success message to the client.
	 *
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the POST could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the POST request.
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws

			ServletException, IOException {

		HttpSession sessionHttpSession = request.getSession(false);
		int userID = (Integer) sessionHttpSession.getAttribute("userID");
		String userName = (String) sessionHttpSession.getAttribute("displayName");

		ArrayList<PostModel> userOnlyPosts = dbController.getUsersOnlyPosts(userName);
		ArrayList<UserModel> users = dbController.getUserDetails(userID);
for(PostModel x:userOnlyPosts)
{
	System.out.println("Success before sending:"+x.getPostID());
}

System.out.println("size"+userOnlyPosts.size());

	if (userOnlyPosts.size() < 1 ) {
		//response.getWriter().println("No Post Yet... Create your first post!");
		
		ArrayList <String> emptyArray = new ArrayList<String>();
		
		sessionHttpSession.setAttribute("userOnlyPosts", emptyArray);
//		response.getWriter().append("No Post Yet... Create your first post!").append(request.getContextPath());
	}
	
	else {
		sessionHttpSession.setAttribute("userOnlyPosts", userOnlyPosts);
	}


		
		// resp.sendRedirect(req.getContextPath() + "/pages/home.jsp");
sessionHttpSession.setAttribute("userLists", users);
		//response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
		response.getWriter().println("Post creation successful!");
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws

			ServletException, IOException {
		
		HttpSession sessionHttpSession = request.getSession(false);
		int userID = (Integer) sessionHttpSession.getAttribute("userID");
		String userName = (String) sessionHttpSession.getAttribute("displayName");
		ArrayList<PostModel> userOnlyPosts = dbController.getUsersOnlyPosts(userName);
		ArrayList<UserModel> users = dbController.getUserDetails(userID);
		
		if (userOnlyPosts.isEmpty()) {
			System.out.println("Empty Posts!");
		} else {
			
			sessionHttpSession.setAttribute("userOnlyPosts", userOnlyPosts);
			sessionHttpSession.setAttribute("userLists", users);
			//request.setAttribute("usersOnlyPosts", userOnlyPosts);
			//request.setAttribute("Lists", users);
	response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
			
			
			
		}
	}

}