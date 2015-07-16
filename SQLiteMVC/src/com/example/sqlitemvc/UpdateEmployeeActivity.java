package com.example.sqlitemvc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateEmployeeActivity extends Activity 
{
 EditText edit1,edit2,edit3;
 Button button1;
 TextView t1;
 
 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_employee);
		t1=(TextView)findViewById(R.id.textView1);
		edit1=(EditText)findViewById(R.id.editText1);
		edit2=(EditText)findViewById(R.id.editText2);
		edit3=(EditText)findViewById(R.id.editText3);
		button1=(Button)findViewById(R.id.button1);
		
		Intent in=getIntent();
		final Employee e=(Employee)in.getSerializableExtra("employee");
		t1.setText(e.getEid()+"");
		edit1.setText(e.getName());
		edit2.setText(e.getSalary()+"");
		edit3.setText(e.getDept());
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				
				String name=edit1.getText().toString();
				String str=edit2.getText().toString();
				float salary=Float.parseFloat(str);
				String dept=edit3.getText().toString();
				Employee enew=new Employee(e.getEid(),
						name, dept, salary);
				
				
				DatabaseHelper dbHelper=
				new DatabaseHelper(
					UpdateEmployeeActivity.this,
					DatabaseHelper.DBName,null, 
					DatabaseHelper.Version);
				
				dbHelper.updateEmployee(enew);
				finish();
				
			}
		});
		
		
		
	}

	
}
