package com.aish.asynctasknetworking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
//import android.content.pm.ApplicationInfo;

public class HomeActivity extends Activity
{
	Button btnDownload;
	ProgressBar pbar;
	ImageView imageView;
	String filePath;
	static int flag=0;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		btnDownload=(Button)findViewById(R.id.button1);
		imageView=(ImageView)findViewById(R.id.imageView1);
		pbar=(ProgressBar)findViewById(R.id.progressBar1);
		ApplicationInfo appInfo=getApplicationInfo();
		filePath =appInfo.dataDir+"/pooh.jpg";
		
		
		btnDownload.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String url="http://cliparts.co/cliparts/rTL/xde/rTLxdeXyc.png";
				ImageTask task=new ImageTask();
				task.execute(url);
				pbar.setVisibility(View.VISIBLE);
				
			}
		});
		
		
	}
	class ImageTask extends AsyncTask<String, Void, Bitmap>
	{

		@Override
		protected Bitmap doInBackground(String... a)
		{
			String url=a[0];
			Bitmap bm=null;
			//File storagePath = Environment.getExternalStorageDirectory();
			File f=new File(filePath);
			if(f.exists()==false)
			{
				HttpGet getReq=new HttpGet(url);
				HttpClient client=new DefaultHttpClient();
				//Toast.makeText(HomeActivity.this, flag, Toast.LENGTH_SHORT).show();
				Log.e("flag=",flag+"");
				try
				{
					HttpResponse resp=client.execute(getReq);
					InputStream is=resp.getEntity().getContent();
					bm=BitmapFactory.decodeStream(is);
					Log.e("flag0=",flag+"");
					FileOutputStream output = null;
					try 
						{
						Log.e("flag1=",flag+"");
						output=new FileOutputStream(filePath);
						bm.compress(Bitmap.CompressFormat.PNG, 100, output); // bmp is your Bitmap instance
					    // PNG is a lossless format, the compression factor (100) is ignored
						   
						    flag=1;
						} 
					finally
						{
						Log.e("flagf=",flag+"");
						    output.close();
						    
						}
				
				}
				catch(Exception e)
				{
					Log.e("flage=",flag+"");
					e.printStackTrace();
				}
			}
			else 
			{
				
				Log.e("flag=", flag+"");
				try
				{
					InputStream fis=new FileInputStream(f);
					bm=BitmapFactory.decodeStream(fis);
					//Toast.makeText(HomeActivity.this, flag, Toast.LENGTH_SHORT).show();
					
					
				} 
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
			}
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater i=getMenuInflater();
		i.inflate(R.menu.link,menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if(item.getItemId()==R.id.item1)
		{
			Intent in=new Intent(HomeActivity.this,ScoreActivity.class);
			startActivity(in);
		}
		return super.onOptionsItemSelected(item);
	}

}
