package com.example.expensemanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
	public static final String DBName="OfficeDB.sqlite";
	public static final int Version=1;
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		//exe create table query
				String q="create table Item(id integer primary key autoincrement," +
						" name text, amount real,date text )";
				db.execSQL(q);
				
				String q1="create table Income(id integer primary key autoincrement," +
						" source text, money real,date text )";
				db.execSQL(q1);
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) 
	{
		
	}

	public void addItem(Item i)
	{
		String q="insert into item (name,amount,date) values ('"+ i.getName()
				+"',"+ i.getAmt()+",'"+i.getDate()+"')";
		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
	}
	public void addIncomeItem(IncomeItem i) 
	{
		String q="insert into Income (source,money,date) values ('"+ i.getName()
				+"',"+ i.getAmt()+",'"+i.getDate()+"')";
		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
		
	}
	public double totali()
	{
		SQLiteDatabase db=getWritableDatabase();
		String q="select sum(money) from income";
		Cursor cursor=db.rawQuery(q, null);
		double sum=0;
		while( cursor.moveToNext() )
			{
			float amount=cursor.getFloat(0);
			 sum+=amount;
			
			}
		cursor.close();
		db.close();
		return sum;
	}
	
	public double total()
	{
		SQLiteDatabase db=getWritableDatabase();
		String q="select sum(amount) from Item";
		Cursor cursor=db.rawQuery(q, null);
		double sum=0;
		while( cursor.moveToNext() )
			{
			float amount=cursor.getFloat(0);
			sum+=amount;
			}
		cursor.close();
		db.close();
		return sum;
	}

	public void deleteItem(Item i)
	{
		String q="delete from item where id="+i.getId();

		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
	}
	public void updateItem(Item inew) 
	{
		String q="update Item set name='"+inew.getName()+"'," +
				"amount="+inew.getAmt()+",date='"+inew.getDate()+"" +
						"' where id="+inew.getId();

		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
	}
	public void deleteIncomeItem(IncomeItem i)
	{
		String q="delete from Income where id="+i.getId();

		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
	}
	public void updateIncomeItem(IncomeItem inew) 
	{
		String q="update Income set source='"+inew.getName()+"'," +
				"money="+inew.getAmt()+",date='"+inew.getDate()+"" +
						"' where id="+inew.getId();

		SQLiteDatabase db=getWritableDatabase();
		db.execSQL(q);
		db.close();
	}
	
	

}
