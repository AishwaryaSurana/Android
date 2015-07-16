package com.example.imageadapter;
import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends Activity
{
	ListView l1;
	ArrayList<User> ual=new ArrayList<User>();
	UserAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		l1=(ListView)findViewById(R.id.listView1);
		User u1=new User("HeadPhone","1/11/2012", "headfon@gmail.com", R.drawable.headphones);
		User u2=new User("Heart","1/11/2012", "heart@gmail.com", R.drawable.heart);
		User u3=new User("Rocket","1/11/2012", "rocket@gmail.com", R.drawable.rocket);
		User u4=new User("Star","1/11/2012", "star@gmail.com", R.drawable.star);
		
		ual.add(u4);
		ual.add(u3);
		ual.add(u2);
		ual.add(u1);
		
		adapter=new UserAdapter();
		l1.setAdapter(adapter);
	
		
	
	}
	
	class UserAdapter extends BaseAdapter
	{

		@Override
		public int getCount() 
		{
			
			return ual.size();
		}

		@Override
		public User getItem(int pos) {
			
			return ual.get(pos);
		}

		@Override
		public long getItemId(int pos) {
				return pos;
		}

		@Override
		public View getView(int pos, View arg1, ViewGroup arg2) 
		{
			
			LayoutInflater inf=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			View itemView=inf.inflate(R.layout.user_list_items, null);
			final User u=ual.get(pos);
			TextView tv1=(TextView)itemView.findViewById(R.id.textView1);
			TextView tv2=(TextView)itemView.findViewById(R.id.textView2);
			ImageView iv=(ImageView)itemView.findViewById(R.id.imageView1);
			Button b1=(Button)itemView.findViewById(R.id.button1);
			
			iv.setImageResource(u.getImageRes());
			tv1.setText(u.getName());
			tv2.setText(u.getDob()+"");
			
			b1.setOnClickListener(new OnClickListener()
			{
				
				
				@Override
				public void onClick(View arg0) 
				{
					Intent in=new Intent(HomeActivity.this,DetailActivity.class);
					in.putExtra("user", u);
					startActivity(in);
				}
			});
			
			return itemView;
		}
		
	}
}
