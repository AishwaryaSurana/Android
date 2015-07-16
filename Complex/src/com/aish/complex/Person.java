package com.aish.complex;

import java.io.Serializable;

public class Person implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7453141488815424209L;
	String name,mobile;
	int age;
	public Person(String name, String mobile, int age) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
