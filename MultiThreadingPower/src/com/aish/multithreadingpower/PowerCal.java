package com.aish.multithreadingpower;

import com.aish.customadapterpower.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PowerCal extends Activity

{
	TextView t1,t2,t3;
	EditText e1,e2;
	Button b1;
	MainHandler handler=new MainHandler();
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		t3=(TextView)findViewById(R.id.textView3);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		
		b1=(Button)findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String b=e1.getText().toString();
				int base=Integer.parseInt(b);
				String e=e2.getText().toString();
				int exp=Integer.parseInt(e);
				CounterThread t=new CounterThread(base,exp);
				t.start();
			}
		});

	}
	class CounterThread extends Thread
	{
		int base,exp;
		public CounterThread(int b,int e)
		{
			base=b;
			exp=e;
			
		}
		public void run()
		{
			double pow;
			for(int i=1;i<=exp;i++)
			{
				//t2.setText("Count="+i);
				//Log.e("Count :", i+"");
				pow=Math.pow(base, i);
				//Log.e("Power :", pow+"");
				try
				{
					Thread.sleep(1000);
				}
				catch (Exception e)
				{
					//Log.e("Error=",e+"");
					e.printStackTrace();
				}
				Bundle bn=new Bundle();
				bn.putInt("base", base);
				bn.putInt("exponent", i);
				bn.putDouble("result", pow);
				Message msg=new Message();
				msg.setData(bn);
				handler.sendMessage(msg);
				
			}
		}//eof run
	}//eof Thread class
	class MainHandler extends Handler
	{
			
			
		@Override
		public void handleMessage(Message msg) 
		{
			super.handleMessage(msg);
			//process message
			Bundle bn=msg.getData();
			int base=bn.getInt("base");
			int exponent=bn.getInt("exponent");
			double result=bn.getDouble("result");
			t3.setText(base+"to the power"+exponent+"="+result);
		}
	}//eof handler
}//eof Activity
