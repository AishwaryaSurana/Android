package com.aish.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NotesListActivity extends ListActivity
{
	ListView l1;
	ArrayList<String>listFiles=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		final ListView l1=getListView();
		String folderpath=getApplicationInfo().dataDir+"/notes";
		File notesDir=new File(folderpath);
		if(notesDir.exists()==true)
		{
			String fname[]=notesDir.list();
			for(int i=0;i<fname.length;i++)
			{
				listFiles.add(fname[i]);
			}
			
		}
		adapter = new ArrayAdapter<String>(NotesListActivity.this, android.R.layout.simple_list_item_1,listFiles);
		l1.setAdapter(adapter);
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				/*String str="";
				StringBuffer buf = new StringBuffer();			
				InputStream is = this.getResources().openRawResource(R.drawable.my_base_data);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				if (is!=null) {							
					while ((str = reader.readLine()) != null) {	
						buf.append(str + "\n" );
					}				
				}		
				is.close();	
				Toast.makeText(getBaseContext(), 
						buf.toString(), Toast.LENGTH_LONG).show();	
				
				*/
				
				String fn=l1.getItemAtPosition(arg2).toString();
				String fp=getApplicationInfo().dataDir+"/notes/"+fn;
				try
				{
				File f=new File(fp);
				String content="";
				FileReader fr=new FileReader(f);
				BufferedReader br = new BufferedReader(fr); 
				String s; 
				while((s = br.readLine()) != null) 
					{ 
					content+=s;
					} 
				fr.close(); 
				Toast.makeText(NotesListActivity.this,content,Toast.LENGTH_LONG).show();	
				
				} 
				
				
				catch(Exception e)
				{
				}
			}
		});
	}
	
	

}
