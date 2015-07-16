package com.aish.implicitintent;


//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class OpenDifferentApp extends Activity

{
	RadioGroup rg1;
	EditText e1;
	Button b1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		rg1=(RadioGroup)findViewById(R.id.radioGroup1);
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				int ch=rg1.getCheckedRadioButtonId();
				String str=e1.getText().toString();
				switch (ch)
				{
				case R.id.radio0:
					
						Intent in=new Intent();
						in.setAction("android.intent.action.CALCULATOR");
						startActivity(in);
						break;
						
						
				case R.id.radio1://Browser
	
					Intent in1=new Intent();
					in1.setAction("android.intent.action.VIEW");
					Uri u=Uri.parse("http://"+str);
					in1.setData(u);
					startActivity(in1);
					break;
				
				case R.id.radio2://Camera
					
					Intent in2=new Intent();
					in2.setAction("android.media.action.IMAGE_CAPTURE");
					//in2.setAction("MediaStore.ACTION_IMAGE_CAPTURE");
					startActivity(in2);
					break;
					
				case R.id.radio3://Dialer
					
					Intent in3=new Intent();
					in3.setAction("android.intent.action.DIAL");
					Uri t=Uri.parse("tel:"+str);
					in3.setData(t);
					startActivity(in3);
					break;
					
				
				
	
				default:
						
					break;
				}
				
			}
		});
	
	}
	

}
