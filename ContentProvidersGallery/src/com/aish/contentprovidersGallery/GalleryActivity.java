package com.aish.contentprovidersGallery;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.aish.contentproviders.R;

public class GalleryActivity extends Activity
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
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				String path=list.get(arg2);
				Bitmap bmp=BitmapFactory.decodeFile(path);
				
				final Dialog d=new Dialog(GalleryActivity.this);
				d.setTitle("Your Selection");
				d.setContentView(R.layout.dialog_view);
				
				ImageView iv=(ImageView)d.findViewById(R.id.imageView1);
				Button b=(Button)d.findViewById(R.id.button1);
				
				
			}
		});
		//find URI for contacts app
		Uri u=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		Log.e("Gallery URI", u.toString());
		Cursor cursor =resolver.query(u, null, null, null, null);
		int cols=cursor.getColumnCount();
		Log.e("Coloumns=",cols+"");
		
		for(int i=0;i<cols;i++)
		{
			String colName=cursor.getColumnName(i);
			Log.e("Contact Coloumn",i+"");
		}
		
		int idPos=cursor.getColumnIndex("_id");
		int dataPos=cursor.getColumnIndex("data");
		int sizePos=cursor.getColumnIndex("_size");
		
		while(cursor.moveToNext())
		{
			String id=cursor.getString(idPos);
			String data=cursor.getString(dataPos);
			String size=cursor.getString(sizePos);
			String str=id+","+data+","+size;
			list.add(str);
		}
		cursor.close();
		adapter=new ArrayAdapter<String>(GalleryActivity.this,android.R.layout.
										simple_list_item_1,list);
		lv.setAdapter(adapter);
	}
	
}
