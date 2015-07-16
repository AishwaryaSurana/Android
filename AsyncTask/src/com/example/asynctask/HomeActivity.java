package com.example.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity
{
	TextView t1;
	EditText e1,e2;
	Button b1;
	ProgressBar pb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		t1=(TextView)findViewById(R.id.textView1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				int base=Integer.parseInt(e1.getText().toString());
				int exp=Integer.parseInt(e2.getText().toString());
				pb.setMax(exp);
				PowerTask task=new PowerTask();
				task.execute(base,exp);
			}
		});
	}
	class PowerTask extends AsyncTask<Integer, Integer, String>
	{

		@Override
		protected String doInBackground(Integer... arg0) 
		{
			int a=arg0[0];
			int b=arg0[1];
			int power=1;
			for(int i=1;i<=b;i++)
			{
				power=power*a;
				publishProgress(power,i);
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e){}
				
			}
			
			return "Done";
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) 
		{
			super.onProgressUpdate(values);
			t1.setText(values[0]+","+values[1]);
			pb.setProgress(values[1]);
			
		}
		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			Toast.makeText(HomeActivity.this, result, 5).show();
			
		}
		
		
	}


}
