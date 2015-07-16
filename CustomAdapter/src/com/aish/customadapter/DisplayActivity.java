/*Requires FormActivity*/
package com.aish.customadapter;

import java.util.ArrayList;

import com.aish.customadapter.Person;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayActivity extends Activity
{
	ListView lv1;
	ArrayList<Person> alp=new ArrayList<Person>();
	Button bt1;
	PersonAdpater adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		lv1=(ListView)findViewById(R.id.listView1);
		bt1=(Button)findViewById(R.id.addbutton1);
		adapter=new PersonAdpater();
		lv1.setAdapter(adapter);
		/*
		Intent i=getIntent();
		Person p1=(Person)i.getSerializableExtra("userName");
		alp.add(p1);*/
		
		bt1.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v1) 
			{
				Intent in=new Intent(DisplayActivity.this,FormActivity.class);
				startActivityForResult(in, 1);
				
			}
		});

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if(requestCode==1)
		{
		super.onActivityResult(requestCode, resultCode, data);
		String name=data.getStringExtra("userName");
		String mobile=data.getStringExtra("userMobile");
		int age=data.getIntExtra("userAge",0);
		
		Person p1=new Person(name, mobile, age);
		alp.add(p1);
		}
	}
	
	
	
	class PersonAdpater extends BaseAdapter
	{

		@Override
		public int getCount() 
		{
			return alp.size();//returns the no of element present in arrayList 
		}

		@Override
		public Person getItem(int pos) 
		{
		
			return alp.get(pos);//returns the object present at that position
		}

		@Override
		public long getItemId(int pos) {
			
			return pos;//returns the position of selected object
		}

		@Override
		public View getView(final int pos, View arg1, ViewGroup arg2) //loads Xml in memory returns UI object of View Type
		{
			final Person p=alp.get(pos);
			LayoutInflater inf=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			View itemView=inf.inflate(R.layout.person_list_items, null);
			TextView tv1=(TextView)itemView.findViewById(R.id.textView1);
			TextView tv2=(TextView)itemView.findViewById(R.id.textView2);
			Button b1=(Button)itemView.findViewById(R.id.button1);
			Button b2=(Button)itemView.findViewById(R.id.button2);
			tv1.setText(p.getName());
			tv2.setText(p.getAge()+"");
			
			b1.setOnClickListener(new OnClickListener()
			{
				
				
				@Override
				public void onClick(View arg0) 
				{
					Intent i=new Intent(Intent.ACTION_CALL);
					Uri t=Uri.parse("tel:"+p.getMobile());
					i.setData(t);
					startActivity(i);
				}
			});
			
			
				b2.setOnClickListener(new OnClickListener()
					{
						
						
						@Override
						public void onClick(View arg0) 
						{
							alp.remove(pos);
							adapter.notifyDataSetChanged();
							
						}
					});
				
				
		
			
			return itemView;
		}
		
	}
	
}
