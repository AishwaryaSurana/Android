package com.example.sqlitemvc;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends Activity 
{
   ListView listView1;
   ArrayList<Employee> listEmp=new ArrayList<Employee>();
   ArrayAdapter<Employee> adapter;
   
   @Override
	protected void onCreate(Bundle savedInstanceState) 
   {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		listView1=(ListView)findViewById(R.id.listView1);
		
		listView1.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, 
					View arg1, final int arg2,long arg3) 
			{
				AlertDialog.Builder builder=
				 new AlertDialog.Builder(HomeActivity.this);
				String fn=listView1.getItemAtPosition(arg2).toString();/*sets the value of list in string*/
			
				builder.setTitle("Action");
				builder.setMessage(fn);
				builder.setIcon(R.drawable.ic_launcher);
				
				builder.setNegativeButton("Delete", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface arg0, int arg1) 
					{
						Employee e=listEmp.get(arg2);
						DatabaseHelper db= new DatabaseHelper(HomeActivity.this, DatabaseHelper.DBName,
								null, DatabaseHelper.Version);
						db.deleteEmployee(e);
						loadEmployee();
						
					}
				});
				
				builder.setPositiveButton("Update", new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) 
					{
						Employee e=listEmp.get(arg2);
						Intent in=new Intent(HomeActivity.this,UpdateEmployeeActivity.class);
						in.putExtra("employee", e);
						startActivity(in);
					}
				});
				
				AlertDialog dialog=builder.create();
				dialog.show();
				
			}
		});
		
	}//eof oncreate
   
   @Override
	protected void onStart() 
   {
		super.onStart();
		loadEmployee();
		
	}
   void loadEmployee()
   {
	 //fetch data from database
	 		listEmp.clear();
	 		DatabaseHelper dbHelper=new DatabaseHelper(HomeActivity.this,DatabaseHelper.DBName,null, DatabaseHelper.Version);
	 				
	 				//open db
	 				SQLiteDatabase db=dbHelper.getWritableDatabase();
	 				
	 				//exe select query
	 				String q="select * from employee";
	 				
	 				Cursor cursor=db.rawQuery(q, null);
	 				
	 				while( cursor.moveToNext() )
	 				{
	 					//get data from current row
	 					int eid=cursor.getInt(0);
	 					String name=cursor.getString(1);
	 					float salary=cursor.getFloat(2);
	 					String dept=cursor.getString(3);
	 					
	 					//String str=eid+","+name+","+salary;
	 					Employee e=new Employee(eid, name, dept, salary);
	 					listEmp.add(e);
	 				}
	 				cursor.close();
	 				db.close();
	 				
	 				//create and set adapter;
	 				adapter=new ArrayAdapter<Employee>
	 				(HomeActivity.this, 
	 				android.R.layout.simple_list_item_1,
	 				listEmp);
	 				
	 				listView1.setAdapter(adapter);
	 				
	   
   }
   @Override
	public boolean onCreateOptionsMenu(Menu menu) 
   {
		//load menu items from homemenu.xml
	   MenuInflater inflater=getMenuInflater();
	   inflater.inflate(R.menu.homemenu, menu);
	   
		return super.onCreateOptionsMenu(menu);
	}
   //handle menu item click
   @Override
	public boolean onOptionsItemSelected(MenuItem item) 
   {
		
	   if(item.getItemId()==R.id.menuExit)
	   {
		   finish();
	   }
	   if(item.getItemId()==R.id.menuAddEmp)
	   {
		   Intent in=new Intent(HomeActivity.this,
				   AddEmployeeActivity.class);
		   startActivity(in);
	   }
	   if(item.getItemId()==R.id.menuTotal)
	   {
		   double tot=0;
		   for(int i=0;i<listEmp.size();i++)
		   {
			   Employee e=listEmp.get(i);
			   tot+=e.getSalary();
			   
		   }
		   String total=Double.toString(tot);
		   Toast.makeText(HomeActivity.this, total,Toast.LENGTH_LONG ).show();
	   }
	   if(item.getItemId()==R.id.menuDeleteAll)
	   {
		  listEmp.removeAll(listEmp);
		  adapter.notifyDataSetChanged();
	   }
	   
	   
	   
		return super.onOptionsItemSelected(item);
	}
   
}
