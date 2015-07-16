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
import android.widget.TextView;

public class UpdateIncomeItemActivity extends Activity implements OnFocusChangeListener

{
	 EditText edit1,edit2,edit3;
	 Button button1;
	 TextView t1;
	//for calendar
		DatePickerDialog datePickerDialog;
		SimpleDateFormat dateFormat;
		DatabaseHelper dbhelper;
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) 
	 {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.update);
			t1=(TextView)findViewById(R.id.textView1);
			edit1=(EditText)findViewById(R.id.editText1);
			edit2=(EditText)findViewById(R.id.editText2);
			edit3=(EditText)findViewById(R.id.editText3);
			button1=(Button)findViewById(R.id.button1);
			dateFormat=new SimpleDateFormat("dd/mm/yy",Locale.getDefault());
			setDateTimeField();
			
			Intent in=getIntent();
			final IncomeItem e=(IncomeItem)in.getSerializableExtra("IncomeItem");
			t1.setText(e.getId()+"");
			edit1.setText(e.getName());
			edit2.setText(e.getAmt()+"");
			edit3.setText(e.getDate());
			button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v)
				{
					
					String name=edit1.getText().toString();
					String str=edit2.getText().toString();
					float amount=Float.parseFloat(str);
					String date=edit3.getText().toString();
					int id=e.getId();
					IncomeItem enew=new IncomeItem(name, amount, date,id);
					
					DatabaseHelper dbHelper=
					new DatabaseHelper(
						UpdateIncomeItemActivity.this,
						DatabaseHelper.DBName,null, 
						DatabaseHelper.Version);
					
					dbHelper.updateIncomeItem(enew);
					finish();
					
				}
			});
			
			
			
		}
	 private void setDateTimeField()
		{
			edit3.setOnFocusChangeListener(this);
			
			Calendar newCalendar = Calendar.getInstance();
			datePickerDialog = new DatePickerDialog(this, new OnDateSetListener()
			{

		        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
		        {
		            Calendar newDate = Calendar.getInstance();
		            newDate.set(year, monthOfYear, dayOfMonth); 
		            String d=(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
		            //Log.e("Date:",newDate+"");
		            edit3.setText(d);
		        }

		    },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
			
			
			
		}
		@Override
		public void onFocusChange(View arg0, boolean arg1) {
			if(arg0 == edit3)
			{
				datePickerDialog.show();
			} 
			
		}


}
