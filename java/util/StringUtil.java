/*
 * 
 * Author: Nischhal Shrestha
 * email: np01ai4s230009@islingtoncollege.edu.np
 * Project Name: Besto Friendo
 * Islington College, KamalPokhari
 * LondonMet ID: 22085857
 * Section: AI-3 
 * */
package util;

import java.io.File;

public class StringUtil {


	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/Besto";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	
	public static final String IMAGE_DIR_USER = "/Users/ness/Islington/II/Data Structures/CW II/src/main/webapp/resources/images/users/";
	public static final String IMAGE_DIR_POST_CONTENTS = "/Users/ness/Islington/II/Data Structures/CW II/src/main/webapp/resources/images/postContents/";
	//i donot need below .. but how to user
	//public static final String IMAGE_DIR_SAVE_PATH = File.separator + IMAGE_DIR_USER;
	// End: DB Connection

	// Start: Queries
	public static final String QUERY_REGISTER_USER = "INSERT INTO users ("
			+ "firstName, lastName, dateOfBirth, displayName, gender,password,phoneNumber, email, userDescription, imageString) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String GET_ALL_STUDENT_INFO = "SELECT * FROM users WHERE displayName = ?";
	public static final String GET_ALL_STUDENT_INFO_BY_ID = "SELECT * FROM users WHERE userID = ?";
	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM users WHERE displayName = ?";
	public static final String QUERY_UPDATE_USER_CHECK_DISPLAY_NAME = "SELECT * FROM users WHERE displayName = ? AND userID != ?";
	public static final String QUERY_UPDATE_USER_CHECK_PHONE_NUMBER = "SELECT * FROM users WHERE phoneNumber = ? AND userID != ?";
	public static final String QUERY_UPDATE_USER_CHECK_EMAIL = "SELECT * FROM users WHERE email = ? AND userID != ?";

	public static final String QUERY_UPDATE_USER_IMAGE = "UPDATE users SET imageString = ? WHERE userID = ?";
	
	
	public static final String GET_ALL_POSTS = "SELECT * FROM posts INNER JOIN users ON posts.userID = users.userID ORDER BY posts.postTimestamp DESC";
	public static final String GET_ONLY_USER_POSTS = "SELECT * FROM posts INNER JOIN users ON posts.userID = users.userID WHERE users.displayName=? ORDER BY posts.postTimestamp DESC";

	
	
	public static final String GET_ALL_USER_POSTS = "SELECT * FROM posts outer join users on posts.userID = users.userID where userID =?";
	public static final String GET_POSTS_ONLY = "SELECT * FROM posts";
	public static final String GET_POSTS_BY_USERID = "SELECT * FROM posts WHERE displayName = ?";
	
	public static final String QUERY_UPDATE_POST = "UPDATE posts SET post=? WHERE postID=?";
	
	public static final String QUERY_UPDATE_USER = "UPDATE users SET firstName = ?, lastName = ?, "
			+ "dateOfBirth = ?, displayName = ?, phoneNumber = ?, email = ?, userDescription = ? WHERE userID = ?";

	
	public static final String QUERY_GET_ALL_USER = "SELECT * FROM users";
	
	public static final String QUERY_EMAIL_CHECK = "SELECT * FROM users WHERE Email = ?";
	public static final String QUERY_PHONE_CHECK = "SELECT * FROM users WHERE PhoneNumber = ?";
	public static final String DUPLICATE_USER= "username already exist in database ";
	
	public static final String QUERY_DELETE_POST = "DELETE FROM posts WHERE postID = ?";
	public static final String QUERY_GET_POST_UPDATE = "SELECT * FROM  posts WHERE postID = ?";
	
	
	public static final String QUERY_CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE userID=?";
	
	//Post Queries
	public static final String QUERY_INSERT_POST =  "INSERT INTO posts(userID, post, contentImageString) "+" VALUES (?,?,?)";
	
	// End: Queries

	// Start: Parameter names
	
	public static final String USER_NAME = "user_name";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "dateOfBirth";
	public static final String DISPLAYNAME = "displayName";
	public static final String GENDER = "gender";
	public static final String PASSWORD = "password";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String EMAIL = "email";
	public static final String USER_DESCRIPTION = "userDescription";
	
	public static final String IMAGE_STRING = "imageString";
	
	public static final String RETYPE_PASSWORD = "retypePassword";
	// End: Parameter names

	// Start: Validation Messages
	public static final String FIRST_NAME_REGEX = "[A-Za-z]+";
	public static final String LAST_NAME_REGEX = "[A-Za-z]+";
	public static final String BIRTHDAY_REGEX = "\\d{4}-\\d{2}-\\d{2}";
	public static final String GENDER_REGEX = "[MF]";
	public static final String EMAIL_REGEX = "[a-zA-Z0-9_&-.]+[@][.][a-z]{2,3}";
	public static final String PHONE_NUMBER_REGEX ="[89]\\d{9}" ;
	public static final String USER_NAME_REGEX = "\\w"; 
	public static final String PASSWORD_REGEX ="[a-zA-Z0-9_$&*@]+";
	public static final String LOGIN_INFO = "Invalid username or password";
	//End: Validation Messages
	
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the Registration Details.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully Logged In!";
	public static final String MESSAGE_ERROR_LOGIN = "Username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Updated!";
	public static final String MESSAGE_ERROR_UPDATE = "Cannot update the user!";
	
	
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages
	


	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_FOOTER = "pages/footers.jsp";
	public static final String PAGE_URL_HEADER = "pages/headers.jsp";
	public static final String URL_LOGIN = "/login.jsp";
	public static final String URL_INDEX = "/index.jsp";
	public static final String URL_HOME = "/pages/home.jsp";
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/RegisterServlet";
	public static final String SERVLET_URL_MODIFY_PROFILE_INFO = "/modifyProfile";
	public static final String SERVLET_URL_UPDATE_PROFILE_INFO = "/updateProfile";
	public static final String SERVLET_URL_CHANGE_PASSWORD = "/ChangePasswordServlet";
	public static final String SERVLET_URL_POST = "/PostServlet";
	public static final String SERVLET_PROFILE = "/ProfileServlet";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
	public static final String SERVLET_URL_UPDATE_IMAGE = "/ProfilePicUpdateServlet";

	// End: Servlet Route

	// Start: Normal Text
	public static final String USER = "user";
	public static final String USERNAME = "username";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
	public static final String STUDENT_LISTS = "studentLists";
	public static final String SLASH= "/";
	
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";
	public static final String UPDATE_USER= "updateUser";
	public static final String UPDATED_POST="updatedPost";
	// End: Normal Text
	
}
