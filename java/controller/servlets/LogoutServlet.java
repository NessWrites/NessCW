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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;


@WebServlet(asyncSupported = true, urlPatterns = StringUtil.SERVLET_URL_LOGOUT)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method handles POST requests to the logout servlet.
	 * It clears all existing cookies, invalidates the user session, and redirects the user to the login page.
	 *
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the POST could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the POST request.
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Handle logout request (assuming this is a logout servlet)

		// 1. Clear existing cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// Set max age to 0 to effectively delete the cookie
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}

		// 2. Invalidate user session (if it exists)
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 3. Redirect to login page
		response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_LOGIN);
	}

}
