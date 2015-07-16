package com.aish.dailyexpenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity
{
	EditText ed1,ed2;
	TextView t1,t2;
	Button login,register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ed1=(EditText)findViewById(R.id.editText1);
		ed2=(EditText)findViewById(R.id.editText2);
		login=(Button)findViewById(R.id.button1);
		register=(Button)findViewById(R.id.button2);
		
		register.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				Intent in= new Intent(LoginActivity.this,RegistrationActivity.class);
				startActivity(in);
				
			}
		});
		
		login.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String email=ed1.getText().toString();
				String password=ed2.getText().toString();
				
			}
		});
		
		}

}
