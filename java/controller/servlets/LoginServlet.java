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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DataBaseController;
import model.LoginModel;
import model.PostModel;
import model.UserPostModel;
import model.UserModel;
import util.StringUtil;

@WebServlet(urlPatterns = "/login", asyncSupported = true)

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String name;
	DataBaseController dbController = new DataBaseController();
	
	/**
	 * This is the constructor of the LoginServlet class.
	 * It initializes a new instance of the DataBaseController
	 *  class and assigns it to the dbController field.
	 */
	public LoginServlet() {
		this.dbController = new DataBaseController();

	}

	/**
	 * This method handles POST requests to the login servlet.
	 * It retrieves the username and password from the request, 
	 * validates them against the database, and sets various 
	 * session attributes based on the result.
	 * If the login is successful, it redirects the user to 
	 * the home page. If the login fails, it forwards the 
	 * request and response to the login page with an appropriate error message.
	 *
	 * @param request The HttpServletRequest object that contains 
	 * the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains
	 *  the response the servlet sends to the client.
	 * @throws ServletException If the request for the POST could not be handled.
	 * @throws IOException If an input or output error is detected when 
	 * the servlet handles the POST request.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		// Create a LoginModel object to hold user credentials
		LoginModel loginModel = new LoginModel(userName, password);

		// Call DBController to validate login credentials
		int loginResult = dbController.getUserLoginInfo(loginModel);//returns userx

		if (loginResult > 0) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			String imageResult = dbController.getUserProfileInfo(loginResult);
			ArrayList<PostModel> PostLists = dbController.getAllUsersPosts();
			ArrayList<UserModel> userLists = dbController.getUserDetails(loginResult);//user details from userID
			
			
			
			for( UserModel displayName: userLists)
			{
			
			String name = displayName.getFirstName();
			
			session.setAttribute("firstName", name);
			}
			
			session.setAttribute("displayName", userName);
			session.setAttribute("userID", loginResult);// loginResult is login userID
			session.setAttribute("profileImage", imageResult);
			session.setAttribute("PostList", PostLists);
			session.setAttribute("profileDetail", userLists);
			
			
			Cookie userCookie = new Cookie("username", userName);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			

			ArrayList<PostModel> userPosts = dbController.getAllUsersPosts();
			if (userPosts != null && !userPosts.isEmpty()) {
				session.setAttribute("postLists", userPosts);

			} else {
				System.out.println("Post has no posts");
			}
			request.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_LOGIN);
			response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
		} else if (loginResult == 0) {
			// Username or password mismatch
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_LOGIN);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
		} else if (loginResult == -1) {
			// Username not found
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_CREATE_ACCOUNT);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);

		} else {
			request.setAttribute("errorMessage", StringUtil.LOGIN_INFO);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");

			dispatcher.forward(request, response);

		}
	}

}
