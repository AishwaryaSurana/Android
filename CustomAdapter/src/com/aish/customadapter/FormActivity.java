package com.aish.customadapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormActivity  extends Activity
{
	EditText t1,t2,t3;
	TextView v1,v2,v3;
	Button b1;
	
@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		t1=(EditText)findViewById(R.id.neditText1);
		t2=(EditText)findViewById(R.id.aeditText2);
		t3=(EditText)findViewById(R.id.ceditText3);
		v1=(TextView)findViewById(R.id.textView1);
		v2=(TextView)findViewById(R.id.textView2);
		v3=(TextView)findViewById(R.id.textView3);
		b1=(Button)findViewById(R.id.button1);
		final Intent in=getIntent();
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String name,contact;
				int age;
				name=t1.getText().toString();
				String n=t2.getText().toString();
				contact=t3.getText().toString();
				age=Integer.parseInt(n);
				//Person p=new Person(name, contact, age);
				
				in.putExtra("userName", name);
				in.putExtra("userMobile", contact);
				in.putExtra("userAge", age);
				
				setResult(1,in);
				finish();

				
			}
		});
	}

}


