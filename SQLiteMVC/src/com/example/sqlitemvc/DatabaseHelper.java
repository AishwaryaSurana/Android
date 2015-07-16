package com.example.sqlitemvc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper 
{

	public DatabaseHelper(Context context,
		String name, CursorFactory factory,
			int version) 
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public static final String DBName="OfficeDB.sqlite";
	public static final int Version=1;
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		//exe create table query
		String q="create table employee(eid integer primary key autoincrement, name text, salary real,dept text )";
		db.execSQL(q);
		
		//exe insert query in created table
		String inq1="insert into employee(name,salary,dept)values( 'xyz',50000, 'IT' )" ;
		db.execSQL(inq1);
		String inq2="insert into employee(name,salary,dept)values( 'pqr',40000, 'HR' )" ;
		db.execSQL(inq2);
		String inq3="insert into employee(name,salary,dept)values( 'abc',35000, 'IT' )" ;
		db.execSQL(inq3);
		
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void addEmployee(Employee e)
	{
		String q="insert into employee"
				+ " (name,salary,dept) "
				+ " values('"+e.getName()
				+"',"+e.getSalary()
				+ ",'"+e.getDept()+"')";
		
		// ? = "+value+"
		

		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
		
	}
	public void deleteEmployee(Employee e) 
	{
		String q="delete from employee where eid="+e.getEid();

		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
	}
	public void updateEmployee(Employee enew) 
	{
		String q="update employee set name='"+enew.getName()+"'," +
				"salary="+enew.getSalary()+",dept='"+enew.getDept()+"" +
						"' where eid="+enew.getEid();

		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
	}
	
	
	

}
