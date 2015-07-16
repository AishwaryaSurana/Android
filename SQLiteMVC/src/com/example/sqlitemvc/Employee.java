package com.example.sqlitemvc;

import java.io.Serializable;

public class Employee implements Serializable 
{
   int eid;
   String name;
   String dept;
   float salary;
public Employee(int eid, String name, String dept, float salary) {
	super();
	this.eid = eid;
	this.name = name;
	this.dept = dept;
	this.salary = salary;
}
public int getEid() {
	return eid;
}
public void setEid(int eid) {
	this.eid = eid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}
public float getSalary() {
	return salary;
}
public void setSalary(float salary) {
	this.salary = salary;
}
  @Override
	public String toString() {
		// TODO Auto-generated method stub
	  String str=eid+","+name+","+salary;
	  return str;
	}
   //constructor
   //getttersetter
   //toString
   
   
   
}
