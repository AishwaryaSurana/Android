package com.aish.imagelist;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends Activity
{

	Button b1;
	ImageView i;
	String selectedImagePath;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		
		b1=(Button)findViewById(R.id.button1);
		i=(ImageView)findViewById(R.id.imageView1);
	
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
			
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivity(intent);
			
				Toast.makeText(HomeActivity.this, "Showing Image",Toast.LENGTH_LONG).show();
				
			}
		});
		
	}
	 public void onActivityResult(int requestCode, int resultCode, Intent data)
	 {
	        if (resultCode == RESULT_OK) 
	        {
	            if (requestCode ==1) 
	            {
	                Uri selectedImageUri = data.getData();
	                //selectedImagePath = getPath(selectedImageUri);
	                //System.out.println("Image Path : " + selectedImagePath);
	                //ContentResolver.openInputStream(selectedImageUri);
	                i.setImageURI(selectedImageUri);
	            }
	        }
	    }
	 
	    
	}