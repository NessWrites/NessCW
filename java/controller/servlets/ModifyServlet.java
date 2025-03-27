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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DataBaseController;
import model.PostModel;
import model.UserPostModel;
import util.StringUtil;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtil.SERVLET_URL_MODIFY_USER)
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DataBaseController dbController;

	public ModifyServlet() {
		this.dbController = new DataBaseController();
	}

	/**
	 * This method handles POST requests. It checks if the request is for an update or delete operation.
	 * If it's an update operation, it calls the doPut method. If it's a delete operation, it calls the doDelete method.
	 *
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the POST could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the POST request.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter(StringUtil.UPDATE_ID);
		String deleteId = request.getParameter(StringUtil.DELETE_ID);
		System.out.println("am i here: dopost:  updateId: and delete" + updateId+ deleteId );
		if (updateId != null && !updateId.isEmpty()) {
			System.out.println("update is haard not extraction: ");
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
			System.out.println("deleteId extraction: " + deleteId);
		}

	}
	
	/**
	 * This method handles PUT requests. It retrieves the post ID and updated post content from the request.
	 * It then updates the post in the database and redirects the user to the home page.
	 *
	 * @param req The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param resp The HttpServletResponse object that contains the response the servlet sends to the client.
	 * @throws ServletException If the request for the PUT could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet handles the PUT request.
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PUT request triggered");
		String postID = req.getParameter(StringUtil.UPDATE_ID);
		String updatedPost = req.getParameter(StringUtil.UPDATED_POST);
		System.out.println("I want this so so so bad"+updatedPost+ postID);
	
			req.setAttribute("update", updatedPost);
			req.setAttribute("updateID", postID);	
			//if postID matches postID from data base may be put a condition.
			dbController.updatePostInfo(postID, updatedPost);
			HttpSession session = req.getSession();
			ArrayList<PostModel> userPosts = dbController.getAllUsersPosts();
			if (userPosts != null && !userPosts.isEmpty()) {
				session.setAttribute("postLists", userPosts);
				resp.sendRedirect(req.getContextPath() + "/pages/home.jsp");
			}
		}

	
	/**
	 * This method handles DELETE requests. It retrieves the post ID from the request 
	 * and deletes the post from the database.
	 * If the delete operation is successful, it redirects the user to the home page.
	 *  If not, it sets an error message and redirects the user to the home page.
	 *
	 * @param req The HttpServletRequest object that contains the request the 
	 * client made of the servlet.
	 * @param resp The HttpServletResponse object that contains the response 
	 * the servlet sends to the client.
	 * @throws ServletException If the request for the DELETE could not be handled.
	 * @throws IOException If an input or output error is detected when the servlet
	 *  handles the DELETE request.
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbController.deletePostInfo(req.getParameter(StringUtil.DELETE_ID)) == 1) {

			HttpSession session = req.getSession();
			ArrayList<PostModel> userPosts = dbController.getAllUsersPosts();
			if (userPosts != null && !userPosts.isEmpty()) {
				session.setAttribute("postLists", userPosts);
				//resp.sendRedirect(req.getContextPath() + "/pages/home.jsp");
			}
			
			
			
			req.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtil.URL_HOME);
		} else {
			req.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtil.URL_HOME);
		}
	}

}
