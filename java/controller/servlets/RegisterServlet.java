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

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.dbcp.dbcp2.Utils;

import controller.database.DataBaseController;
import model.UserModel;
import util.StringUtil;


@WebServlet(asyncSupported = true, urlPatterns = { StringUtil.SERVLET_URL_REGISTER })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, // 3MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)




public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataBaseController dbController = new DataBaseController();

	public RegisterServlet() {
		super();

	}
	
	/**
	 * Handles POST requests for user registration. 
	 * Retrieves user information from the request parameters, validates the input data, 
	 * and performs user registration if the input is valid. 
	 * If the registration is successful, redirects the user to the login page. 
	 * If the registration fails, displays an error message.
	 * 
	 * @param request The HttpServletRequest object that contains the request made to the servlet.
	 * @param response The HttpServletResponse object that contains the response sent by the servlet.
	 * @throws ServletException If an error occurs while processing the servlet request.
	 * @throws IOException If an error occurs during input or output operations.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws

			ServletException, IOException 
{
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		
		LocalDate dob = LocalDate.parse(request.getParameter(StringUtil.BIRTHDAY));

		
		
		String displayName = request.getParameter("username");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		String phoneNumberStr = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String userDescription = request.getParameter("userDescription");
		Part imagePart = request.getPart("image");	
		
  
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

		if (!gender.matches(StringUtil.GENDER_REGEX)) {
			isValid = false;
			response.getWriter().println(StringUtil.GENDER);
		}

		try {

			String dobString = dob.toString();
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
		if (!phoneNumberStr.matches(StringUtil.PHONE_NUMBER_REGEX)) {
			isValid = false;
			response.getWriter().println(StringUtil.PHONE_NUMBER);
		}
		// Clear error message attribute
		request.removeAttribute("errorMessage");
		
		if (dbController.checkUsernameIfExists(displayName)) 
		{
            isValid = false;
            errorMessage.append("username already exist in database ");
         // Forward the user back to the registration form with an error message
            request.setAttribute("errorMessage", errorMessage.toString());
            //request.getRequestDispatcher( "/pages/register.jsp").forward(request, response);
		}
		
		if (dbController.checkEmailIfExists(email))
		{
			isValid = false;
			errorMessage.append("Email already exists in database");
			// Forward the user back to the registration form with an error message
            request.setAttribute("errorMessage", errorMessage.toString());
            //request.getRequestDispatcher( "/pages/register.jsp").forward(request, response);
		}
		
		if (dbController.checkNumberIfExists(phoneNumberStr))
		{
			isValid = false;
			errorMessage.append("Phone Number already exists in database");
			// Forward the user back to the registration form with an error message
            request.setAttribute("errorMessage", errorMessage.toString());
            //request.getRequestDispatcher( "/pages/register.jsp").forward(request, response);
		}
		if (!isValid) {
		    // Forward the user back to the registration form with error messages
		    request.setAttribute("errorMessage", errorMessage.toString());
		    request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
		}			
		if (isValid) {
			UserModel userModel = new UserModel(firstName,
					lastName, dob, displayName,
					gender,password, phoneNumberStr, 
					email, userDescription, imagePart);
			
			String savePath = StringUtil.IMAGE_DIR_USER;
		    String fileName = userModel.getImageUrlFromPart();
		    if(!fileName.isEmpty() && fileName != null)
	    		imagePart.write(savePath + fileName);

			int result = dbController.registerUser(userModel);

			if (result > 0) { // rootpath, parent path, contextPath in a way is webapp...
				response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
			} else {
				System.out.println("maybe here: ");
			}
			response.getWriter().println("Registration successful!");
		} else {
			// Send failure response
			System.out.println("if not there then here: ");
			request.setAttribute("errorMessage", errorMessage.toString());
	        request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			response.getWriter().println("Registration failed. Please check your input.");
		}

	}
}
