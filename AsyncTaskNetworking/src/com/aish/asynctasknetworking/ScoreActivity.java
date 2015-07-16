package com.aish.asynctasknetworking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
public class ScoreActivity extends Activity
{
	Button b1;
	ListView l1;
	ArrayList<String> l=new ArrayList<String>();
	ArrayAdapter<String>adapter;
	String filePath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		b1=(Button)findViewById(R.id.button1);
		l1=(ListView)findViewById(R.id.listView1);
		ApplicationInfo appInfo=getApplicationInfo();
		filePath =appInfo.dataDir+"/score.xml";
		
		b1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				String url="http://static.cricinfo.com/rss/livescores.xml";
				ScoreTask task= new ScoreTask();
				task.execute(url);
				
			}
		});
		
	
	
	}

	class ScoreTask extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... arg0) 
		{
			String url=arg0[0];
			HttpGet getReq=new HttpGet(url);
			HttpClient client=new DefaultHttpClient();
			String result="";
			try
			{
				HttpResponse resp=client.execute(getReq);
				InputStream is=resp.getEntity().getContent();
				InputStreamReader reader=new InputStreamReader(is);
				BufferedReader br=new BufferedReader(reader);
				while(true)
				{
					String str=br.readLine();
					if(str==null)
					{
						break;
					}
					result+=str;
					
				}
				br.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return result;
		}
		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			try
			{
				FileWriter writer=new FileWriter(filePath);
				writer.write(result);
				writer.flush();
				writer.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			parseScoreXML();
			
		}

		void parseScoreXML()
		{
			try
			{
				FileReader reader= new FileReader(filePath);
				XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser parser=factory.newPullParser();
				parser.setInput(reader);
				int tokenType=-1;
				
				while(true)
				{
					tokenType =parser.next();
					if(tokenType==XmlPullParser.END_DOCUMENT)
						{
						
						break;
						}
					if(tokenType==XmlPullParser.START_TAG)
					{
						String name=parser.getName();
						if(name.equals("title"))
						{
							
							tokenType=parser.next();
							if(tokenType==XmlPullParser.TEXT)
							{
								
								String str=parser.getText();
								l.add(str);
								
							}
						}
					}
					
				}
				reader.close();
				adapter=new ArrayAdapter<String>(ScoreActivity.this, android.R.layout.simple_list_item_1
						,l);
				l1.setAdapter(adapter);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
	}
	
}

