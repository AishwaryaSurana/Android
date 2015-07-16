package com.example.imageadapter;

import java.io.Serializable;

public class User implements Serializable
{
	String Name,dob,email;
	int imageRes;
	public User(String name, String dob, String email, int imageRes) {
		super();
		Name = name;
		this.dob = dob;
		this.email = email;
		this.imageRes = imageRes;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getImageRes() {
		return imageRes;
	}
	public void setImageRes(int imageRes) {
		this.imageRes = imageRes;
	}
	

}
