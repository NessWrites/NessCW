package model;

public class ChangePasswordModel {
private int userID;
private String oldPassword;
private String newPassword;
public ChangePasswordModel(int userID, String oldPassword, String newPassword) {
	super();
	this.userID = userID;
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
	
	
}
public String getNewPassword() {
	return newPassword;
}
public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}
public int getUserID() {
	return userID;
}
public void setUserID(int userID) {
	this.userID = userID;
}
public String getOldPassword() {
	return oldPassword;
}
public void setOldPassword(String oldPassword) {
	this.oldPassword = oldPassword;
}

}
