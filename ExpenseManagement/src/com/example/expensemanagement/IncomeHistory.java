package com.example.expensemanagement;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class IncomeHistory extends Activity
{
	ListView listView1;
	  
	   ArrayList<IncomeItem> listItem=new ArrayList<IncomeItem>();
	   ArrayAdapter<IncomeItem> adapter;
	   
	   @Override
	protected void onCreate(Bundle savedInstanceState) 
	   {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list1);
		listView1=(ListView)findViewById(R.id.listView1);
		Intent in=getIntent();
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
					long arg3) 
			{
			AlertDialog.Builder builder=
			new AlertDialog.Builder(IncomeHistory.this);
			String fn=listView1.getItemAtPosition(arg2).toString();/*sets the value of list in string*/
			builder.setTitle("Action");
			builder.setMessage("What you want to do with "+fn);
			builder.setIcon(R.drawable.ic_launcher);
			
			builder.setNegativeButton("Delete", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface arg0, int arg1) 
				{
					IncomeItem i=listItem.get(arg2);
					DatabaseHelper db= new DatabaseHelper(IncomeHistory.this,
									DatabaseHelper.DBName,null, DatabaseHelper.Version);
					db.deleteIncomeItem(i);
					loadIncome();
					
				}
			});
			
			builder.setPositiveButton("Update", new DialogInterface.OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) 
				{
					IncomeItem i=listItem.get(arg2);
					Intent in=new Intent(IncomeHistory.this,UpdateIncomeItemActivity.class);
					in.putExtra("IncomeItem", i);
					startActivity(in);
				}
			});
			
			AlertDialog dialog=builder.create();
			dialog.show();
			
		}
	});
	
		
	   }
	   protected void onStart() 
	   {
			super.onStart();
			loadIncome();
			
		}
	   void loadIncome()
	   {
		 //fetch data from database
		 		listItem.clear();
		 		DatabaseHelper dbHelper=new DatabaseHelper(IncomeHistory.this,
		 								DatabaseHelper.DBName,null, DatabaseHelper.Version);
		 				
		 				//open db
		 				SQLiteDatabase db=dbHelper.getWritableDatabase();
		 				
		 				//exe select query
		 				String q="select * from Income";
		 				
		 				Cursor cursor=db.rawQuery(q, null);
		 				
		 				while( cursor.moveToNext() )
		 				{
		 					//get data from current row
		 					int id=cursor.getInt(0);
		 					String name=cursor.getString(1);
		 					float amount=cursor.getFloat(2);
		 					String date=cursor.getString(3);
		 					
		 					//String str=eid+","+name+","+salary;
		 					IncomeItem i=new IncomeItem( name, amount, date,id);
		 					listItem.add(i);
		 				}
		 				cursor.close();
		 				db.close();
		 				
		 				//create and set adapter
		 				adapter=new ArrayAdapter<IncomeItem>
		 				(IncomeHistory.this, 
		 				android.R.layout.simple_list_item_1,
		 				listItem);
		 				
		 				listView1.setAdapter(adapter);
		 				
		   
	   }
	   

}
