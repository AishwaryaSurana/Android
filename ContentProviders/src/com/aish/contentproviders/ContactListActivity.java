package com.aish.contentproviders;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactListActivity extends Activity
{
	ListView lv;
	ArrayList<String> list =new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		lv=(ListView)findViewById(R.id.listView1);
		ContentResolver resolver=getContentResolver();
		
		//find URI for contacts app
		Uri u=ContactsContract.Contacts.CONTENT_URI;
		Log.e("Contact URI", u.toString());
		Cursor cursor =resolver.query(u, null, null, null, null);
		int cols=cursor.getColumnCount();
		Log.e("Coloumns=",cols+"");
		for(int i=0;i<cols;i++)
		{
			String colName=cursor.getColumnName(i);
			Log.e("Contact Coloumn",i+"");
		}
		
		int idPos=cursor.getColumnIndex("_id");
		int namePos=cursor.getColumnIndex("display_name");
		int hasphone=cursor.getColumnIndex("has_phone_number");
		
		while(cursor.moveToNext())
		{
			String id=cursor.getString(idPos);
			String name=cursor.getString(namePos);
			String hasPhone=cursor.getString(hasphone);
			String data=id+","+name+","+hasPhone;
			list.add(data);
		}
		cursor.close();
		adapter=new ArrayAdapter<String>(ContactListActivity.this,android.R.layout.
										simple_list_item_1,list);
		lv.setAdapter(adapter);
	}
}
