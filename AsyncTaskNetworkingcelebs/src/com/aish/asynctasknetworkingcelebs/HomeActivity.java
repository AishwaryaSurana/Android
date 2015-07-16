package com.aish.asynctasknetworkingcelebs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity
{
	Spinner s;
	ProgressBar pbar;
	ImageView imageView;
	TextView t;
	ListView lv;
	ArrayList<String> name=new ArrayList<String>();
	ArrayAdapter<String>adapter;
	String root =Environment.getExternalStorageDirectory().toString();
	
	String iname="";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		lv=(ListView)findViewById(R.id.listView1);
		s=(Spinner)findViewById(R.id.spinner1);
		t=(TextView)findViewById(R.id.textView1);
		imageView=(ImageView)findViewById(R.id.imageView1);
		pbar=(ProgressBar)findViewById(R.id.progressBar1);
		
		name.add("Bhagat Singh");
		name.add("Emma Watson");
		name.add("Daniel Vittory");
		name.add("Bret Lee");
		
		adapter=new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_spinner_item,name);
		s.setAdapter(adapter);
		
		File myDir = new File(root + "/saved_images");    
	    myDir.mkdirs();
		
	    s.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			String url;
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			
			{
				Toast.makeText(HomeActivity.this, "file created at "+root,
					Toast.LENGTH_LONG).show();
				if(arg2==0)
				{
					iname="/bhagat.png";
					url="http://www.themotherindia.com/wp-content/uploads/2012/07/bhagatsingh.jpg";
					ImageTask i1=new ImageTask();
					i1.execute(url,iname,root);
					pbar.setVisibility(View.VISIBLE);
				}
				else if(arg2==1)
				{
					iname="/emma.png";
					url="http://theredlist.com/media/database/muses/icon/cinematic_women/2010/emma-watson/026-emma-watson-theredlist.jpg";
					ImageTask i2=new ImageTask();
					i2.execute(url,iname,root);
					pbar.setVisibility(View.VISIBLE);
				}
				else if(arg2==2)
				{
					iname="/vittory.png";
					url="http://images.tvnz.co.nz/tvnz_images/sport2010/cricket/black-caps/vettori_big_smile_2.jpg";
					ImageTask i3=new ImageTask();
					i3.execute(url,iname,root);
					pbar.setVisibility(View.VISIBLE);
				}
				else if(arg2==3)
				{
					iname="/lee.png";
					url="http://www.images99.com/i99/03/87335/87335.jpg";
					ImageTask i4=new ImageTask();
					i4.execute(url,iname,root);
					pbar.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				Toast.makeText(HomeActivity.this, "nothing is selected",
						Toast.LENGTH_LONG).show();
				
			}
		});
			}
		
	
	class ImageTask extends AsyncTask<String, Void, Bitmap>
	{

		@Override
		protected Bitmap doInBackground(String... a)
		{
			String url=a[0];
			String img=a[1];
			String path=a[2];
			Bitmap bm=null;
			Log.e("path",path);
			
			String imagePath=path+img;
			//File storagePath = Environment.getExternalStorageDirectory();
			File f=new File(imagePath);
			if(f.exists()==false)
			{
				HttpGet getReq=new HttpGet(url);
				HttpClient client=new DefaultHttpClient();
				Toast.makeText(HomeActivity.this, "file not existing", Toast.LENGTH_SHORT).show();
				
				try
				{
					HttpResponse resp=client.execute(getReq);
					InputStream is=resp.getEntity().getContent();
					bm=BitmapFactory.decodeStream(is);
					FileOutputStream output = null;
					try 
						{
						output=new FileOutputStream(imagePath);
						bm.compress(Bitmap.CompressFormat.PNG, 100, output); // bmp is your Bitmap instance
					    // PNG is a lossless format, the compression factor (100) is ignored
						} 
					finally
						{
						    output.close();
						}
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else 
			{
			try
				{
					InputStream fis=new FileInputStream(f);
					bm=BitmapFactory.decodeStream(fis);

					Toast.makeText(HomeActivity.this, "file existing", Toast.LENGTH_SHORT).show();
					
				} 
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
			}
			Log.e("i path",imagePath);
			return bm;
		}
		@Override
		protected void onPostExecute(Bitmap result) 
		{
			super.onPostExecute(result);
			imageView.setImageBitmap(result);
			pbar.setVisibility(View.INVISIBLE);
		}
		
	}	
	

}
