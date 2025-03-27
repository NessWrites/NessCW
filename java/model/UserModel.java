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
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtil;

public class UserModel implements Serializable {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String displayName;
	private String gender;
	private String password;
	private String phoneNumber;
	private String email;
	private String userDescription;
	private String imageUrlFromPart;
	
	
	
	public UserModel(String firstName, String lastName, 
			LocalDate dateOfBirth, String displayName, 
			String gender,String password, 
			String phoneNumber, String email, 
			String userDescription, 
			Part imagePart) 
	
	{
		super();
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.displayName = displayName;
		this.gender = gender;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userDescription = userDescription;
		this.imageUrlFromPart =  getImageUrl(imagePart);
	}
	
	public  UserModel() {}
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
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
