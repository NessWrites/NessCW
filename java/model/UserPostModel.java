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

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtil;

public class UserPostModel implements Serializable {
	
	private String post;
	private int userID;

	private LocalDate time;
	//private String imageString;
	private String userName;
	private String imageUrlFromPart;

	public UserPostModel(String post, int userID, Part imagePart) {
		super();
		this.post = post;
		this.userID = userID;

		this.imageUrlFromPart =  getImageUrl(imagePart);

	}
	
//	public UserPostModel() {}
	public String getPost() {
		return post;
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
	public LocalDate getTime() {
		return time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setTime(LocalDate time) {
		this.time = time;
	}
	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}
	public void setImageString(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
	}
	
	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}
	
	private String getImageUrl(Part part) {
		String savePath = StringUtil.IMAGE_DIR_POST_CONTENTS;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}

		return imageUrlFromPart;
	}
	



	
}
