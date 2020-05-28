package com.radsyn.jmerchdao.model;

import javax.persistence.*;

@Entity
@Table
public class User {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userID;
	
	@Column
	private String userName;
	
	@Column
	private String password;

	public void populateWithObjectValues(User valueObj) {
		//System.out.println("user populate userid=" + valueObj.getUserID() + " uname=" + valueObj.getUserName() + " pw=" + valueObj.getPassword());
		 this.setUserID(valueObj.getUserID());
		 this.setUserName(valueObj.getUserName());
		 this.setPassword(valueObj.getPassword());
	 }
	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
