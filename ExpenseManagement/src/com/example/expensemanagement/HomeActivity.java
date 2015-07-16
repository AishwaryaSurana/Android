package com.example.expensemanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnFocusChangeListener
{
	EditText e1,e2,e3;
	
	Button b;
	//for calendar
	DatePickerDialog datePickerDialog;
	SimpleDateFormat dateFormat;
	DatabaseHelper dbhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);//date
		e3=(EditText)findViewById(R.id.editText3);
		//t1=(TextView)findViewById(R.id.textView1);
		b=(Button)findViewById(R.id.button1);
		
		dateFormat=new SimpleDateFormat("dd/mm/yy",Locale.US);
		setDateTimeField();
		
		b.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				   String name,date,a;
				   float amount;
				   name=e1.getText().toString();
				   date=e2.getText().toString();
				   a=e3.getText().toString();
				   amount=Float.parseFloat(a);
				   e1.setText("");
				   e2.setText("");
				   e3.setText("");
				   Item i=new Item(name, amount, date);
				   
				   DatabaseHelper dbHelper=
							new DatabaseHelper(
								HomeActivity.this,
								DatabaseHelper.DBName,null, 
								DatabaseHelper.Version);
							
							dbHelper.addItem(i);
							Toast.makeText(HomeActivity.this, "expended on"+name,
											Toast.LENGTH_LONG).show();
							
				
			}
		});
		
	}
	
	private void setDateTimeField()
	{
		e2.setOnFocusChangeListener(this);
		
		Calendar newCalendar = Calendar.getInstance();
		datePickerDialog = new DatePickerDialog(this, new OnDateSetListener()
		{

	        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
	        {
	            Calendar newDate = Calendar.getInstance();
	            //Log.e("Date",newDate+"");
	            newDate.set(year, monthOfYear+1, dayOfMonth);
	            String d=(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
	            //Log.e("Date:",newDate+"");
	            e2.setText(d);
	        }

	    },newCalendar.get(Calendar.YEAR),(newCalendar.get(Calendar.MONTH)),
	    newCalendar.get(Calendar.DAY_OF_MONTH));
		
		
		
	}
	@Override
	public void onFocusChange(View arg0, boolean arg1) {
		if(arg0 == e2)
		{
			datePickerDialog.show();
		} 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return super.onCreateOptionsMenu(menu);
		
	}

	   //handle menu item click
	   @Override
		public boolean onOptionsItemSelected(MenuItem item) 
	   {
			
		  
		   if(item.getItemId()==R.id.item1)
		   {
			   //Expense History
			   
			  Intent intent=new Intent(HomeActivity.this,ExpenseListActivity.class);
			   //intent.putExtra("Item",i);
			   startActivity(intent);
			   
		   }
		   if(item.getItemId()==R.id.item2)
		   {   
			   //income
			   Intent in=new Intent(HomeActivity.this,
					   IncomeActivity.class);
			   startActivity(in);
		   }
		   if(item.getItemId()==R.id.item3)
		   {
			   //balance
			   DatabaseHelper dbh=new DatabaseHelper(HomeActivity.this,DatabaseHelper.DBName
					   				, null, DatabaseHelper.Version);
			  
			   double exp=dbh.total();
			   double inc=dbh.totali();
			   double bal=inc-exp;
			   Toast.makeText(HomeActivity.this, "Balance="+bal, Toast.LENGTH_LONG).show();
		   }
		   if(item.getItemId()==R.id.item4)
		   {
			   finish();
		   }
		   return super.onOptionsItemSelected(item);
		}

	
}
