package com.example.asynctask;

import java.util.Calendar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HomeActivity extends Activity
{
	TextView t1,t2,t3,t4,t5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.watch);
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		t3=(TextView)findViewById(R.id.textView3);
		t4=(TextView)findViewById(R.id.textView4);
		t5=(TextView)findViewById(R.id.textView5);
		//int hours = new Time(System.currentTimeMillis()).getHours();
		//Log.e("Time :",hours+"");
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min=cal.get(Calendar.MINUTE);
		int sec=cal.get(Calendar.SECOND);
		Log.e("Time :",hour+":"+min+":"+sec);
		MyTime mt=new MyTime();
		mt.execute(hour,min,sec);
	}
	class MyTime extends AsyncTask<Integer, Integer, Void>
	{

		@Override
		protected Void doInBackground(Integer... arg0)
		{
			int h=arg0[0];
			int m=arg0[1];
			int s=arg0[2];
			for(int i=0;i<61;i++)
			{
				s++;
				if(i==60)
				{
					i=0;
					s=0;
					m++;
					if(m==60)
					{
						m=0;
						h++;
						if(h==24)
						{
							h=0;
						}
						
					}
					
				}
				publishProgress(h,m,s);
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e){}
			}
			
			
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) 
		{
			super.onProgressUpdate(values);
			t1.setText(values[0]+"");
			t2.setText(values[1]+"");
			t3.setText(values[2]+"");
			
		}
		
	}
	


}
