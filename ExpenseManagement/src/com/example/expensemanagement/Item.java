package com.example.expensemanagement;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable

{
	@Override
	public String toString() {
		return "Expense Item= " + name + " Cost= " + amt + " Date= " + date
				+ ",Id=" + id;
	}
	String name;
	float amt;
	String date;
	int id;
	public Item(String name, float amt, String date, int id) {
		super();
		this.name = name;
		this.amt = amt;
		this.date = date;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Item(String name, float amt, String date) {
		super();
		this.name = name;
		this.amt = amt;
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmt() {
		return amt;
	}
	public void setAmt(float amt) {
		this.amt = amt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	

}
