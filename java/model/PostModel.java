/*
 * 
 * Author: Nischhal Shrestha
 * email: np01ai4s230009@islingtoncollege.edu.np
 * Project Name: Besto Friendo
 * Islington College, KamalPokhari
 * LondonMet ID: 22085857
 * Section: AI-3 
 * */
package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

public class PostModel  implements Serializable{
	private String post;
	private int userID;
	private int postID;
	private String imageString;
	private String userName;
	private String postImageString;
	private Timestamp time; 

	public PostModel(String post, int userID, int postID, String imageString,
			String userName, String postImageString, Timestamp time) {
		super();
		this.post = post;
		this.userID = userID;
		this.postID = postID;
		this.imageString = imageString;
		this.userName = userName;
		this.postImageString = postImageString;
		this.time = time;	
	}
	public String getPost() {
		return post;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPostImageString() {
		return postImageString;
	}
	public void setPostImageString(String postImageString) {
		this.postImageString = postImageString;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	
}
