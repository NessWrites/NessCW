package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DataBaseController;
import model.ChangePasswordModel;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataBaseController dbController = new DataBaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        
        this.dbController = new DataBaseController();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//get parameter from .jsp file
		HttpSession session = request.getSession();
		int userID = (Integer)(session.getAttribute("userID"));
		
		System.out.println("C.p servlet: "+ userID);
		
//		int userID = Integer.parseInt(request.getParameter("userID"));//we can get this from session userID
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String retypePassword = request.getParameter("retypePassword");
		
		System.out.println(oldPassword + newPassword + retypePassword);
		
		if (newPassword.equals(retypePassword)) {
			
			ChangePasswordModel passwordModal = new ChangePasswordModel(userID, oldPassword, newPassword);
			dbController.changePassword(passwordModal);
			request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
		}
		
		else {
			request.setAttribute("errorMessage", "Passwords don't match!");
			System.out.println("Passwords don't match!");
		    request.getRequestDispatcher("/pages/profile.jsp").forward(request, response); // how to redirect to the page from where request was made
		}		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
