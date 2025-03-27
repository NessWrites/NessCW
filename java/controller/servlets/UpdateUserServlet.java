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
import model.ProfileUpdateModel;
import model.UserModel;
import util.StringUtil;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtil.SERVLET_URL_UPDATE_PROFILE_INFO })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, // 3MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataBaseController dbController = new DataBaseController();

	public UpdateUserServlet() {
		this.dbController = new DataBaseController();

	}
	/**
	 * Handles POST requests for updating user profile information. 
	 * Retrieves user information from the request parameters, validates the input data, 
	 * and updates the user profile if the input is valid. 
	 * If the update is successful, redirects the user to the profile page. 
	 * If the update fails, displays an error message.
	 * @param request The HttpServletRequest object that contains the request made to the servlet.
	 * @param response The HttpServletResponse object that contains the response sent by the servlet.
	 * @throws ServletException If an error occurs while processing the servlet request.
	 * @throws IOException If an error occurs during input or output operations.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
		String displayName = request.getParameter("displayName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String userDescription = request.getParameter("userDescription");
		int userID = Integer.parseInt(request.getParameter("userID"));
//		ArrayList<UserModel> userList = dbController.getUserDetails(userID);

		// Perform validation
		boolean isValid = true;
		StringBuilder errorMessage = new StringBuilder();

		if (!firstName.matches(StringUtil.FIRST_NAME_REGEX)) {
			isValid = false;
			response.getWriter().println(StringUtil.FIRST_NAME);
		}

		if (!lastName.matches(StringUtil.LAST_NAME_REGEX)) {
			isValid = false;
			response.getWriter().println(StringUtil.LAST_NAME);
		}

		try {

			String dobString = dateOfBirth.toString();
			if (dobString == null || dobString.isEmpty()) {
				isValid = false;
				response.getWriter().println(StringUtil.BIRTHDAY);
			} else {

				if (!dobString.matches(StringUtil.BIRTHDAY_REGEX)) {
					isValid = false;
					response.getWriter().println(StringUtil.BIRTHDAY);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!phoneNumber.matches(StringUtil.PHONE_NUMBER_REGEX)) {
			isValid = false;
			response.getWriter().println(StringUtil.PHONE_NUMBER);
		}

		// Clear error message attribute
		request.removeAttribute("errorMessage");

		if (dbController.checkUsernameIfExists(displayName, userID)) {
			isValid = false;
			errorMessage.append("username already exist in database ");
			// Forward the user back to the registration form with an error message
			request.setAttribute("errorMessage", errorMessage.toString());

		}

		if (dbController.checkEmailIfExists(email, userID)) {
			isValid = false;
			errorMessage.append("Email already exists in database");
			// Forward the user back to the registration form with an error message
			request.setAttribute("errorMessage", errorMessage.toString());
			// request.getRequestDispatcher( "/pages/register.jsp").forward(request,
			// response);
		}

		if (dbController.checkNumberIfExists(phoneNumber, userID)) {
			isValid = false;
			errorMessage.append("Phone Number already exists in database");
			// Forward the user back to the registration form with an error message
			request.setAttribute("errorMessage", errorMessage.toString());
		}

		if (!isValid) {
			// Forward the user back to the registration form with error messages
			request.setAttribute("errorMessage", errorMessage.toString());
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
		}
		if (isValid) {
			ProfileUpdateModel profileUpdater = new ProfileUpdateModel(firstName, lastName, dateOfBirth, displayName,
					phoneNumber, email, userDescription);
			dbController.updateProfileInfo(userID, profileUpdater);
			ArrayList<UserModel> userDetails = dbController.getUserDetails(userID);
			HttpSession sessionHttpSession = request.getSession();
			sessionHttpSession.setAttribute("displayName", displayName);
			sessionHttpSession.setAttribute("profileDetail", userDetails);
			response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
		}
	}

	@Override
	
	/**
	 * Handles PUT requests for updating user posts. 
	 * Retrieves the post ID and updated post content from the request parameters. 
	 * Updates the post in the database and redirects the user to the home page.
	 * 
	 * @param req The HttpServletRequest object that contains the request made to the servlet.
	 * @param resp The HttpServletResponse object that contains the response sent by the servlet.
	 * @throws ServletException If an error occurs while processing the servlet request.
	 * @throws IOException If an error occurs during input or output operations.
	 */
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PUT request Update function triggered");
		String postID = req.getParameter(StringUtil.UPDATE_ID);
		String updatedPost = req.getParameter(StringUtil.UPDATED_POST);
		System.out.println("I want this so so so bad" + updatedPost + postID);

		req.setAttribute("update", updatedPost);
		req.setAttribute("updateID", postID);
		// if postID matches postID from data base may be put a condition.
		dbController.updatePostInfo(postID, updatedPost);
		HttpSession session = req.getSession();
		ArrayList<PostModel> userPosts = dbController.getAllUsersPosts();
		if (userPosts != null && !userPosts.isEmpty()) {
			session.setAttribute("postLists", userPosts);
			resp.sendRedirect(req.getContextPath() + "/pages/home.jsp");
		}
	}
}
