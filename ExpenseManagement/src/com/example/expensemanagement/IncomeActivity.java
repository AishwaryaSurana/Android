package com.example.expensemanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class IncomeActivity extends Activity implements OnFocusChangeListener
{
	EditText e1,e2,e3;
	Button b1,b2;
	//for calendar
		DatePickerDialog datePickerDialog;
		SimpleDateFormat dateFormat;
		DatabaseHelper dbhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.income);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		dateFormat=new SimpleDateFormat("dd/mm/yy",Locale.getDefault());
		setDateTimeField();
		
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String source,date,a;
				float money;
				   a=e1.getText().toString();
				   date=e2.getText().toString();
				   source=e3.getText().toString();
				   money=Float.parseFloat(a);
				   e1.setText("");
				   e2.setText("");
				   e3.setText("");
				   Intent in=getIntent();
				   IncomeItem i=new IncomeItem(source, money, date);
				   
				   DatabaseHelper dbHelper=
							new DatabaseHelper(
								IncomeActivity.this,
								DatabaseHelper.DBName,null, 
								DatabaseHelper.Version);
							
							dbHelper.addIncomeItem(i);
				
			}
		});
		
		b2.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				 Intent in=new Intent(IncomeActivity.this,
						   IncomeHistory.class);
				   startActivity(in);
				
				
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
	            newDate.set(year, monthOfYear, dayOfMonth);
	            String d=(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
	            e2.setText(d);
	        }

	    },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
		
		
		
	}
	@Override
	public void onFocusChange(View arg0, boolean arg1) {
		if(arg0 == e2)
		{
			datePickerDialog.show();
		} 
		
	}


}
