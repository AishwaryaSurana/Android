package com.example.imageadapter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity
{
	
	TextView v1,v2,v3;
	ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		v1=(TextView)findViewById(R.id.textView1);
		v2=(TextView)findViewById(R.id.textView2);
		v3=(TextView)findViewById(R.id.textView3);
		iv=(ImageView)findViewById(R.id.imageView1);
		
		Intent in=getIntent();
		User u=(User)in.getSerializableExtra("user");
		v1.setText(u.getName());
		v2.setText(u.getDob());
		v3.setText(u.getEmail());
		
		iv.setImageResource(u.getImageRes());
	}

}
