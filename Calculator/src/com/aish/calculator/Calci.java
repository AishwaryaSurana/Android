package com.aish.calculator;


//import android.R;
import android.app.Activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Calci extends Activity
{
	EditText ed1,ed2;
	RadioButton r1,r2,r3;
	//Button b1;
	TextView t1;
@Override
protected void onCreate(Bundle savedInstanceState) 
	{
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	
	setContentView(R.layout.calc);

	r1=(RadioButton)findViewById(R.id.r1);
	r3=(RadioButton)findViewById(R.id.r3);
	r2=(RadioButton)findViewById(R.id.r2);
	ed1=(EditText)findViewById(R.id.e1);
	ed2=(EditText)findViewById(R.id.e2);
	//b1=(Button)findViewById(R.id.b1);
	t1=(TextView)findViewById(R.id.t1);
	
	r1.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0)
		{
			String no1=ed1.getText().toString();
			String no2=ed2.getText().toString();
			
			double n1,n2,res;
			
			n1=Double.parseDouble(no1);
			n2=Double.parseDouble(no2);
			
			res=n1+n2;
						
			t1.setText(res+"");
			
			
		}
	});
	
	r2.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0)
		{
			String no1=ed1.getText().toString();
			String no2=ed2.getText().toString();
			
			double n1,n2,res;
			
			n1=Double.parseDouble(no1);
			n2=Double.parseDouble(no2);
			
			res=n1*n2;
						
			t1.setText(res+"");
			
			
		}
	});
	
	r3.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0)
		{
			String no1=ed1.getText().toString();
			String no2=ed2.getText().toString();
			
			double n1,n2,res;
			
			n1=Double.parseDouble(no1);
			n2=Double.parseDouble(no2);
			
			res=Math.pow(n1, n2);
						
			t1.setText(res+"");
			
			
		}
	});
			
			
			
		}
		
	
	
	
	}

