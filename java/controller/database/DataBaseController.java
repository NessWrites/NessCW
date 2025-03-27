/*
 * 
 * Author: Nischhal Shrestha
 * email: np01ai4s230009@islingtoncollege.edu.np
 * Project Name: Besto Friendo
 * Islington College, KamalPokhari
 * LondonMet ID: 22085857
 * Section: AI-3 
 * */

package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ChangePasswordModel;
import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.PostModel;
import model.ProfilePicModel;
import model.ProfileUpdateModel;
import model.UserModel;
import model.UserPostModel;
import util.StringUtil;

public class DataBaseController {
	/**
	 * Establishes a connection to the database using pre-defined credentials and
	 * driver information.
	 * 
	 * @return A `Connection` object representing the established connection to the
	 *         database.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtil.DRIVER_NAME); // Load the MySQL JDBC driver

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtil.LOCALHOST_URL, StringUtil.LOCALHOST_USERNAME,
				StringUtil.LOCALHOST_PASSWORD);
	}

	/**
	 * This method attempts to register a new student in the database.
	 * 
	 * @param student A `StudentModel` object containing the student's information.
	 * @return An integer value indicating the registration status: - 1:
	 *         Registration successful - 0: Registration failed (no rows affected) -
	 *         -1: Internal error (e.g., ClassNotFound or SQLException)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public int registerUser(UserModel user) {
		try {
			// Prepare a statement using the predefined query for student registration
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_REGISTER_USER);
			stt.setString(1, user.getFirstName());
			stt.setString(2, user.getLastName());

			stt.setDate(3, Date.valueOf(user.getDateOfBirth()));
			stt.setString(4, user.getDisplayName());
			stt.setString(5, user.getGender());
			stt.setString(6, PasswordEncryptionWithAes.encrypt(user.getDisplayName(), user.getPassword()));
			stt.setString(7, user.getPhoneNumber());
			stt.setString(8, user.getEmail());

			stt.setString(9, user.getUserDescription());

			stt.setString(10, user.getImageUrlFromPart());
			// Execute the update statement and store the number of affected rows
			int result = stt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful

			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error

		}
	}

	/**
	 * This method attempts to validate a student login by checking the username and
	 * password against a database.
	 * 
	 * @param username The username provided by the user attempting to log in.
	 * @param password The password provided by the user attempting to log in.
	 * @return An integer value indicating the login status: - 1: Login successful -
	 *         0: Username or password mismatch - -1: Username not found in the
	 *         database - -2: Internal error (e.g., SQL or ClassNotFound exceptions)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */

	public String getUserProfileInfo(int userID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.GET_ALL_STUDENT_INFO_BY_ID);
			// Set the userID in the first parameter of the prepared statement
			st.setInt(1, userID);
			// st.setString(2, loginModel.getPassword());
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {

				// User name and password match in the database
				String userPhoto = result.getString("imageString");

				return userPhoto;// success

			}

			else {
				return "Some error has occured";
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return "Check SQL Statements";

		}

	}

	/**
	 * This method attempts to create a new post for a user in the database.
	 * 
	 * @param post - The UserPostModel object containing the details of the post. It
	 *             includes the user's ID, the post content, and the URL of the
	 *             image associated with the post.
	 * 
	 * @return An integer value indicating the status of the post creation: - 1:
	 *         Post creation successful - 0: Post creation failed (no rows affected)
	 *         - -1: Internal error (e.g., SQL or ClassNotFoundException exceptions)
	 */
	public int userPosts(UserPostModel post) {
		try {
			// Prepare a statement using the predefined query for student registration
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_INSERT_POST,
					Statement.RETURN_GENERATED_KEYS);
			stt.setInt(1, post.getUserID());
			stt.setString(2, post.getPost());
			stt.setString(3, post.getImageUrlFromPart());

			// stt.setString(3, post.getContentImageString());

			// Execute the update statement and store the number of affected rows
			int result = stt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				ResultSet set = stt.getGeneratedKeys();
				if (set.next()) {
					int id = set.getInt(1);
					// PostModel postModel=getPostByPostId(id);
				}
				return 1; // Post successful

			} else {

				return 0; // Post failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			System.out.println("Our fucking head SQL error");
			ex.printStackTrace();
			return -1; // Internal error

		}
	}

	/**
	 * This method attempts to validate a user's login credentials by checking the
	 * username and password against a database.
	 * 
	 * @param loginModel - The LoginModel object containing the username and
	 *                   password provided by the user attempting to log in.
	 * 
	 * @return An integer value indicating the login status: - userID: Login
	 *         successful (userID is the unique identifier of the user in the
	 *         database) - 0: Username or password mismatch - -1: Username not found
	 *         in the database - -2: Internal error (e.g., SQL or
	 *         ClassNotFoundException exceptions)
	 */
	public int getUserLoginInfo(LoginModel loginModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.GET_ALL_STUDENT_INFO);
			// Set the username in the first parameter of the prepared statement
			st.setString(1, loginModel.getUsername());
			// st.setString(2, loginModel.getPassword());
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {

				// User name and password match in the database
				String userDb = result.getString("displayName");
				int userID = result.getInt("userID");

				String passwordDb = result.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, loginModel.getUsername());

				if (userDb.equals(loginModel.getUsername()) && decryptedPwd != null
						&& decryptedPwd.equals(loginModel.getPassword()))

					return userID;// success

				else
					// Username or password mismatch, return 0
					return 0;
			}

			else {
				// Username not found in the database, return -1
				return -1;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -2;
		}

	}

	/**
	 * This method checks if a given username exists in the database.
	 * 
	 * @param username - The username to be checked.
	 * 
	 * @return A Boolean value indicating the existence of the username: - true: The
	 *         username exists in the database. - false: The username does not exist
	 *         in the database or an error occurred (e.g., SQL or
	 *         ClassNotFoundException exceptions).
	 */

	public Boolean checkUsernameIfExists(String username) {
		try {
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_LOGIN_USER_CHECK);

			stt.setString(1, username); // get user info from database oops
			ResultSet result = stt.executeQuery(); // it makes sure , returns null or value
			if (result.next()) {
				// User name exists
				String userDb = result.getString("userName");

				if (userDb.equals(username))
					return true;

				else

					return false;

			}
			return false;
		}

		catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}

	}

	/**
	 * This method checks if a given username exists in the database for a specific
	 * user ID.
	 * 
	 * @param username - The username to be checked.
	 * @param userID   - The unique identifier of the user in the database.
	 * 
	 * @return A Boolean value indicating the existence of the username for the
	 *         specific user ID: - true: The username exists in the database for the
	 *         given user ID. - false: The username does not exist in the database
	 *         for the given user ID or an error occurred (e.g., SQL or
	 *         ClassNotFoundException exceptions).
	 */

	public Boolean checkUsernameIfExists(String username, int userID) {
		try {
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_UPDATE_USER_CHECK_DISPLAY_NAME);

			stt.setString(1, username); // get user info from database oops
			stt.setInt(2, userID); // get user info from database oops
			ResultSet result = stt.executeQuery(); // it makes sure , returns null or value

			if (result.next()) {
				// User name exists
				String userDb = result.getString("displayName");

				if (userDb.equals(username))
					return true;

				else

					return false;

			}
			return false;
		}

		catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}

		// TODO: Implement logic to check if the provided username exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the username exists, false otherwise.

	}

	/**
	 * This method checks if a given email address exists in the database.
	 * 
	 * @param email - The email address to be checked.
	 * 
	 * @return A Boolean value indicating the existence of the email address: -
	 *         true: The email address exists in the database. - false: The email
	 *         address does not exist in the database or an error occurred (e.g.,
	 *         SQL or ClassNotFoundException exceptions).
	 */

	public Boolean checkEmailIfExists(String email) {
		// TODO: Implement logic to check if the provided email address exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the email exists, false otherwise.
		try {
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_EMAIL_CHECK);
			stt.setString(1, email);
			ResultSet result = stt.executeQuery();
			if (result.next()) {
				String userDb = result.getString("Email");
				if (userDb.equals(email))
					return true;
				else
					return false;

			}
			return false;
		} catch (SQLException | ClassNotFoundException ex) {

			ex.printStackTrace(); // Log the exception for debugging
			return false;

		}
	}

	/**
	 * This method checks if a given email address exists in the database for a
	 * specific user ID.
	 * 
	 * @param email  - The email address to be checked.
	 * @param userID - The unique identifier of the user in the database.
	 * 
	 * @return A Boolean value indicating the existence of the email address for the
	 *         specific user ID: - true: The email address exists in the database
	 *         for the given user ID. - false: The email address does not exist in
	 *         the database for the given user ID or an error occurred (e.g., SQL or
	 *         ClassNotFoundException exceptions).
	 */

	public Boolean checkEmailIfExists(String email, int userID) {
		try {
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_UPDATE_USER_CHECK_EMAIL);
			stt.setString(1, email); // get user info from database
			stt.setInt(2, userID); // get user info from database
			ResultSet result = stt.executeQuery(); // it makes sure , returns null or value
			if (result.next()) {
				// Email exists
				String userDb = result.getString("email");

				if (userDb.equals(email))
					return true;
				else
					return false;
			}
			return false;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}
	}

	/**
	 * This method checks if a given phone number exists in the database.
	 * 
	 * @param number - The phone number to be checked.
	 * 
	 * @return A Boolean value indicating the existence of the phone number: - true:
	 *         The phone number exists in the database. - false: The phone number
	 *         does not exist in the database or an error occurred (e.g., SQL or
	 *         ClassNotFoundException exceptions).
	 */

	public Boolean checkNumberIfExists(String number) {
		// TODO: Implement logic to check if the provided phone number exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the phone number exists, false otherwise.
		try {
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_PHONE_CHECK);
			stt.setString(1, number);
			ResultSet result = stt.executeQuery();

			if (result.next()) {
				String userDb = result.getString("PhoneNumber");
				if (userDb.equals(number))
					return true;
				else
					return false;

			}
			return false;
		} catch (SQLException | ClassNotFoundException ex) {

			ex.printStackTrace(); // Log the exception for debugging
			return false;

		}

	}

	/**
	 * This method checks if a given phone number exists in the database for a
	 * specific user ID.
	 * 
	 * @param phoneNumber - The phone number to be checked.
	 * @param userID      - The unique identifier of the user in the database.
	 * 
	 * @return A Boolean value indicating the existence of the phone number for the
	 *         specific user ID: - true: The phone number exists in the database for
	 *         the given user ID. - false: The phone number does not exist in the
	 *         database for the given user ID or an error occurred (e.g., SQL or
	 *         ClassNotFoundException exceptions).
	 */

	public Boolean checkNumberIfExists(String phoneNumber, int userID) {
		try {
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_UPDATE_USER_CHECK_PHONE_NUMBER);
			stt.setString(1, phoneNumber); // get user info from database
			stt.setInt(2, userID); // get user info from database
			ResultSet result = stt.executeQuery(); // it makes sure , returns null or value
			if (result.next()) {
				// Phone number exists
				String userDb = result.getString("phoneNumber");

				if (userDb.equals(phoneNumber))
					return true;
				else
					return false;
			}
			return false;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}
	}

	/**
	 * This method retrieves all posts from a specific user in the database.
	 * 
	 * @param userID - The unique identifier of the user in the database.
	 * 
	 * @return An ArrayList of PostModel objects, each representing a user's post.
	 *         Each PostModel includes the post content, user ID, post ID, display
	 *         name, image string, post image string, and timestamp. If an error
	 *         occurs (e.g., SQL or ClassNotFoundException exceptions), it returns
	 *         null.
	 */

	public ArrayList<PostModel> getAllUsersPosts() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtil.GET_ALL_POSTS);
			ResultSet result = stmt.executeQuery();

			ArrayList<PostModel> models = new ArrayList<PostModel>();

			while (result.next()) {
				String post = result.getString("post");
				int userID = result.getInt("userID");
				int postID = result.getInt("postID");
				String userName = result.getString("displayName");
				String imageString = result.getString("imageString");
				String postImageString = result.getString("contentImageString"); // String contentImageString =
																					// result.getString("image");

				Timestamp timestamp = result.getTimestamp("postTimestamp");
				// LocalDate datePost = timestamp.toLocalDateTime().toLocalDate();

				PostModel model = new PostModel(post, userID, postID, imageString, userName, postImageString,
						timestamp);

				model.setUserID(userID);
				model.setUserName(userName);
				model.setPostID(postID);
				model.setImageString(imageString);

				model.setPost(post);
				model.setPostImageString(postImageString);
				model.setTime(timestamp);

				models.add(model);

			}
			return models;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Your head");
			return null;

		}
	}

	/**
	 * This method retrieves the details of a user from the database using the
	 * user's ID. It fetches the user's first name, last name, date of birth,
	 * display name, gender, password, phone number, email, user description, and
	 * image URL. The method returns an ArrayList of UserModel objects, each
	 * representing a user with the specified ID.
	 * 
	 * @param userID The ID of the user whose details are to be fetched.
	 * @return An ArrayList of UserModel objects representing the user with the
	 *         specified ID. Returns null if an exception occurs.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */

	public ArrayList<UserModel> getUserDetails(int userID) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtil.GET_ALL_STUDENT_INFO_BY_ID);
			stmt.setInt(1, userID);

			ResultSet result = stmt.executeQuery();
			ArrayList<UserModel> users = new ArrayList<UserModel>();

			while (result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				Date sqlDate = result.getDate("dateOfBirth");
				LocalDate dateOfBirth = sqlDate.toLocalDate();
				String displayName = result.getString("displayName");
				String gender = result.getString("gender");
				String password = result.getString("password");
				String phoneNumber = result.getString("phoneNumber");
				String email = result.getString("email");
				String userDescription = result.getString("userDescription");
				String imageUrlFromPart = result.getString("imageString");
				UserModel user = new UserModel();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setDateOfBirth(dateOfBirth);
				user.setDisplayName(displayName);
				user.setGender(gender);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				user.setEmail(email);
				user.setUserDescription(userDescription);
				user.setImageUrlFromDB(imageUrlFromPart);
				users.add(user);

			}
			return users;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Your head");
			return null;

		}
	}

	/**
	 * This method retrieves the details of a user from the database using the
	 * user's ID. It fetches the user's first name, last name, date of birth,
	 * display name, gender, password, phone number, email, user description, and
	 * image URL. The method returns an ArrayList of UserModel objects, each
	 * representing a user with the specified ID.
	 * 
	 * @param userID The ID of the user whose details are to be fetched.
	 * @return An ArrayList of UserModel objects representing the user with the
	 *         specified ID. Returns null if an exception occurs.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */
	public ArrayList<PostModel> getUsersOnlyPosts(String displayName) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtil.GET_ONLY_USER_POSTS);

			stmt.setString(1, displayName);

			ResultSet result = stmt.executeQuery();

			ArrayList<PostModel> models = new ArrayList<PostModel>();

			while (result.next()) {
				String post = result.getString("post");
				int userID = result.getInt("userID");
				int postID = result.getInt("postID");
				String userName = result.getString("displayName");
				String imageString = result.getString("imageString");
				String postImageString = result.getString("contentImageString"); // String contentImageString =
																					// result.getString("image");
				Timestamp timestamp = result.getTimestamp("postTimestamp");
				// LocalDate datePost = timestamp.toLocalDateTime().toLocalDate();
				PostModel model = new PostModel(post, userID, postID, imageString, userName, postImageString,
						timestamp);

				model.setUserID(userID);
				model.setUserName(userName);
				model.setPostID(postID);
				model.setImageString(imageString);
				model.setPost(post);
				model.setPostImageString(postImageString);
				model.setTime(timestamp);
				models.add(model);

			}
			return models;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Your head");
			return null;

		}
	}

	/**
	 * This method deletes a post from the database using the post's ID. It prepares
	 * a SQL statement to delete the post and executes the update.
	 *
	 * @param postID The ID of the post to be deleted.
	 * @return An integer indicating the status of the operation. Returns the number
	 *         of rows affected if the operation is successful , and -1 if an
	 *         exception occurs.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */
	public int deletePostInfo(String postID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.QUERY_DELETE_POST);
			st.setString(1, postID);

			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	/**
	 * This method retrieves a post from the database using the post's ID. It
	 * prepares a SQL statement to get the post and executes the update.
	 *
	 * @param updateID The ID of the post to be retrieved.
	 * @return An integer indicating the status of the operation. Returns the number
	 *         of rows affected if the operation is successful, and -1 if an
	 *         exception occurs.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */
	public int getPostByPostId(int updateID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.QUERY_GET_POST_UPDATE);
			st.setInt(1, updateID);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	/**
	 * This method updates the content of a post in the database using the post's ID
	 * and the new content. It prepares a SQL statement to update the post and
	 * executes the update.
	 *
	 * @param updateID    The ID of the post to be updated.
	 * @param postContent The new content for the post.
	 * @return An integer indicating the status of the operation. Returns the number
	 *         of rows affected if the operation is successful, and -1 if an
	 *         exception occurs.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */

	public int updatePostInfo(String updateID, String postContent) {
		try (Connection con = getConnection()) {

			// getparameter from popup
			// set parameter

			PreparedStatement st = con.prepareStatement(StringUtil.QUERY_UPDATE_POST);

			st.setString(1, postContent);
			st.setString(2, updateID);
			return st.executeUpdate();
		}

		catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method updates the profile information of a user in the database using
	 * the user's ID and a ProfileUpdateModel object. It prepares a SQL statement to
	 * update the user's first name, last name, date of birth, display name, phone
	 * number, email, and user description.
	 * 
	 * @param userID The ID of the user whose profile information is to be updated.
	 * @param user   A ProfileUpdateModel object containing the new profile
	 *               information for the user.
	 * @return An integer indicating the status of the operation. Returns the number
	 *         of rows affected if the operation is successful, and -1 if an
	 *         exception occurs.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */

	public int updateProfileInfo(int userID, ProfileUpdateModel user) {
		try (Connection con = getConnection()) {

			// getparameter from popup
			// set parameter

			PreparedStatement st = con.prepareStatement(StringUtil.QUERY_UPDATE_USER);

			st.setString(1, user.getFirstName());
			st.setString(2, user.getLastName());
			st.setDate(3, java.sql.Date.valueOf(user.getDateOfBirth()));

			st.setString(4, user.getDisplayName());
			st.setString(5, user.getPhoneNumber());
			st.setString(6, user.getEmail());
			st.setString(7, user.getUserDescription());
			// Assuming userID is an integer
			st.setInt(8, userID);
			return st.executeUpdate();
		}

		catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method updates the profile image of a user in the database using a
	 * ProfilePicModel object. It prepares a SQL statement to update the user's
	 * image URL and executes the update.
	 *
	 * @param user A ProfilePicModel object containing the user's ID and the new
	 *             image URL.
	 * @return A string indicating the status of the operation. Returns the new
	 *         image URL if the operation is successful, null if no rows were
	 *         affected, and "-1" if an exception occurs.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */
	public String userImage(ProfilePicModel user) {

		try {
			// Prepare a statement using the predefined query for student registration
			PreparedStatement stt = getConnection().prepareStatement(StringUtil.QUERY_UPDATE_USER_IMAGE);

			stt.setInt(2, user.getUserID());

			stt.setString(1, user.getImageUrlFromPart());
			// Execute the update statement and store the number of affected rows
			int result = stt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return user.getImageUrlFromPart(); // Registration successful

			} else {
				return null; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return "-1"; // Internal error

		}

	}

	public int changePassword(ChangePasswordModel passwordsParam) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.GET_ALL_STUDENT_INFO_BY_ID);
			// Set the id in the first parameter of the prepared statement
			st.setInt(1, passwordsParam.getUserID());
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {

				String oldPassword = passwordsParam.getOldPassword();
				String newPassword = passwordsParam.getNewPassword();

				// User name and password match in the database
				String userNameDb = result.getString("displayName");
				int userID = result.getInt("userID");

				String passwordDb = result.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, userNameDb);
				System.out.println("decrypted: "+ decryptedPwd + "newPassword: " + newPassword);

				if (decryptedPwd != null && decryptedPwd.equals(oldPassword)) {

					PreparedStatement stt = con.prepareStatement(StringUtil.QUERY_CHANGE_PASSWORD);
					// Set the id in the second parameter of the prepared statement
					stt.setInt(2, userID);

					// Set new password as first parameter
					stt.setString(1, PasswordEncryptionWithAes.encrypt(userNameDb, newPassword));
					stt.executeUpdate();

					return userID;// success
				}

				else {
					// ERROR IN CHANGE PASSWORD QUERY, return 0
					return 0;
				}
			}

			else {
				// User not found in the database, return -1
				return -1;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -2;
		}

	}
	

}