/*
 * 
 * Author: Nischhal Shrestha
 * email: np01ai4s230009@islingtoncollege.edu.np
 * Project Name: Besto Friendo
 * Islington College, KamalPokhari
 * LondonMet ID: 22085857
 * Section: AI-3 
 * */


package controller.filters;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;

public class AuthenticationFilter implements Filter {
	private ServletContext context;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

	    // Cast request and response objects to HttpServletRequest and HttpServletResponse for type safety
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;

	    // Get the requested URI
	    String uri = req.getRequestURI();

	    // Allow access to static resources (CSS) and the index page without checking login
	    if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(StringUtil.URL_INDEX) || uri.endsWith("/")) {
	        chain.doFilter(request, response);
	        return;
	    }
	    
	    if(uri.endsWith(StringUtil.URL_HOME) || uri.endsWith("/")) {
			request.getRequestDispatcher(StringUtil.SERVLET_URL_HOME).forward(request, response);
	        //res.sendRedirect(req.getContextPath() + "/pages/home.jsp");
	        return;
    	}

	    // Separate flags for login, login/logout servlets, and register page/servlet for better readability
	    boolean isLogin = uri.endsWith(StringUtil.PAGE_URL_LOGIN);
	    boolean isLoginServlet = uri.endsWith(StringUtil.SERVLET_URL_LOGIN);
	    boolean isLogoutServlet = uri.endsWith(StringUtil.SERVLET_URL_LOGOUT);
	    boolean isHomeServlet = uri.endsWith(StringUtil.SERVLET_URL_HOME);
	 
	    boolean isRegisterPage = uri.endsWith(StringUtil.PAGE_URL_REGISTER);
	    boolean isRegisterServlet = uri.endsWith(StringUtil.SERVLET_URL_REGISTER);

	    // Check if a session exists and if the username attribute is set to determine login status
	    HttpSession session = req.getSession(false); // Don't create a new session if one doesn't exist
	    System.out.println("session"+session);
	    
	    
	    boolean isLoggedIn = session != null && session.getAttribute(StringUtil.DISPLAYNAME) != null;

	    // Redirect to login if user is not logged in and trying to access a protected resource
	    if (!isLoggedIn && !(isLogin || isLoginServlet || isRegisterPage || isRegisterServlet)) {
	        res.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_LOGIN);
	        System.out.println("am i here not logged");
	    } else if (isLoggedIn && !isHomeServlet && !(!isLogin || isLogoutServlet)) { // Redirect logged-in users to the index page if trying to access login page again
	        res.sendRedirect(req.getContextPath() + StringUtil.URL_HOME);
	        System.out.println("am i here home");
	    } else {
	        // Allow access to the requested resource if user is logged in or accessing unprotected resources
	    	System.out.println("no condition ");
	        chain.doFilter(request, response);
	    }

	}

	@Override
	public void destroy() {
	}
}
