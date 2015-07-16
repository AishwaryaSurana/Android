package com.example.sqlitemvc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddEmployeeActivity extends Activity 
{
 EditText edit1,edit2,edit3;
 Button button1;
 
 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_employee);
		edit1=(EditText)findViewById(R.id.editText1);
		edit2=(EditText)findViewById(R.id.editText2);
		edit3=(EditText)findViewById(R.id.editText3);
		
		button1=(Button)findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name=edit1.getText().toString();
				String str=edit2.getText().toString();
				float salary=Float.parseFloat(str);
				String dept=edit3.getText().toString();
				
				Employee e=new Employee(0,
						name, dept, salary);
				
				DatabaseHelper dbHelper=
				new DatabaseHelper(
					AddEmployeeActivity.this,
					DatabaseHelper.DBName,null, 
					DatabaseHelper.Version);
				
				dbHelper.addEmployee(e);
				finish();
				
			}
		});
		
		
		
	}

	
}
