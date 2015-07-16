package com.aish.multithreading;

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

public class HomeActivity extends Activity

{
	TextView t1,t2;
	EditText e1;
	Button b1;
	MainHandler handler=new MainHandler();
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String n=e1.getText().toString();
				int no=Integer.parseInt(n);
				CounterThread t=new CounterThread(no);
				t.start();
			}
		});

	}
	class CounterThread extends Thread
	{
		int n;
		public CounterThread(int n)
		{
			this.n=n;
			
		}
		public void run()
		{
			for(int i=n;i>0;i--)
			{
				//t2.setText("Count="+i);
				Log.e("Count :", i+"");
				try
				{
					Thread.sleep(1000);
				}
				catch (Exception e)
				{
//					Log.e("Error=",e+"");
					e.printStackTrace();
				}
				Bundle bn=new Bundle();
				bn.putInt("count", i);
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
			int c=bn.getInt("count");
			t2.setText(c+"");
		}
	}//eof handler
}//eof Activity
