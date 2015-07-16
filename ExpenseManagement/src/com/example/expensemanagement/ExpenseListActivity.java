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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ExpenseListActivity extends Activity
{
	   ListView listView1;
	   Button b1;
	   ArrayList<Item> listItem=new ArrayList<Item>();
	   ArrayAdapter<Item> adapter;
	   
	   @Override
	protected void onCreate(Bundle savedInstanceState) 
	   {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		listView1=(ListView)findViewById(R.id.listView1);
		b1=(Button)findViewById(R.id.button1);
		Intent in=getIntent();
		listView1=(ListView)findViewById(R.id.listView1);
		
		listView1.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, 
					View arg1, final int arg2,long arg3) 
			{
				AlertDialog.Builder builder=
				 new AlertDialog.Builder(ExpenseListActivity.this);
				String fn=listView1.getItemAtPosition(arg2).toString();/*sets the value of list in string*/
				builder.setTitle("Action");
				builder.setMessage("What you want to do with "+fn);
				builder.setIcon(R.drawable.ic_launcher);
				
				builder.setNegativeButton("Delete", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface arg0, int arg1) 
					{
						Item i=listItem.get(arg2);
						DatabaseHelper db= new DatabaseHelper(ExpenseListActivity.this,
										DatabaseHelper.DBName,null, DatabaseHelper.Version);
						db.deleteItem(i);
						loadData();
						
					}
				});
				
				builder.setPositiveButton("Update", new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) 
					{
						Item i=listItem.get(arg2);
						Intent in=new Intent(ExpenseListActivity.this,UpdateItemActivity.class);
						in.putExtra("Item", i);
						startActivity(in);
					}
				});
				
				AlertDialog dialog=builder.create();
				dialog.show();
				
			}
		});
		
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				DatabaseHelper dbHelper=new DatabaseHelper(ExpenseListActivity.this,
										DatabaseHelper.DBName,null, DatabaseHelper.Version);
				double tot=dbHelper.total();
				Toast.makeText(ExpenseListActivity.this, "Expenses="+tot, Toast.LENGTH_LONG).show();
				 
		 		
				
			}
		});
		
	   }
	   protected void onStart() 
	   {
			super.onStart();
			loadData();
			
		}
	   void loadData()
	   {
		 //fetch data from database
		 		listItem.clear();
		 		DatabaseHelper dbHelper=new DatabaseHelper(ExpenseListActivity.this,DatabaseHelper.DBName,null, DatabaseHelper.Version);
		 				
		 				//open db
		 				SQLiteDatabase db=dbHelper.getWritableDatabase();
		 				
		 				//exe select query
		 				String q="select * from item";
		 				
		 				Cursor cursor=db.rawQuery(q, null);
		 				
		 				while( cursor.moveToNext() )
		 				{
		 					//get data from current row
		 					int id=cursor.getInt(0);
		 					String name=cursor.getString(1);
		 					float amount=cursor.getFloat(2);
		 					String date=cursor.getString(3);
		 					
		 					//String str=eid+","+name+","+salary;
		 					Item i=new Item( name, amount, date,id);
		 					listItem.add(i);
		 				}
		 				cursor.close();
		 				db.close();
		 				
		 				//create and set adapter
		 				adapter=new ArrayAdapter<Item>
		 				(ExpenseListActivity.this, 
		 				android.R.layout.simple_list_item_1,
		 				listItem);
		 				
		 				listView1.setAdapter(adapter);
		 				
		   
	   }
	   

}
