package com.aish.file;

import java.io.File;
import java.io.FileWriter;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity
{
	EditText e1,e2;
	Button b1,b2;
	String folderPath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		
		ApplicationInfo appInfo=getApplicationInfo();
		folderPath=appInfo.dataDir+"/notes";
		
		File notesDir=new File(folderPath);
		if(notesDir.exists()==false)
		{
			notesDir.mkdir();
		}
		
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String title=e1.getText().toString();
				String filePath=folderPath+"/"+title+".txt";
				String desc=e2.getText().toString();
				
				try
				{
					FileWriter writer=new FileWriter(filePath);
					writer.write(desc);
					writer.flush();
					writer.close();
					
				}
				catch(Exception e)
				{
					Log.e("Err",e.getMessage());
				}
				e1.setText("");
				e2.setText("");
				Toast.makeText(HomeActivity.this, "Saved",Toast.LENGTH_LONG).show();
				
			}
		});
		
		b2.setOnClickListener(new OnClickListener()
		{
			
			
			@Override
			public void onClick(View arg0) 
			{
				Intent in=new Intent(HomeActivity.this, NotesListActivity.class);
				startActivity(in);
				
			}
		});
	}
}
