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

import javax.servlet.http.Part;

import util.StringUtil;

public class ProfilePicModel implements Serializable {
	private int userID;
	private String imageUrlFromPart;
	public ProfilePicModel(int userID, Part imagePart) {
		super();
		this.userID = userID;
		this.imageUrlFromPart = getImageUrl(imagePart);
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
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
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}
	

}
