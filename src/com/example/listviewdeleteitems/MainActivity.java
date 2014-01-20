package com.example.listviewdeleteitems;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ListActivity 
{
	ArrayList<String> list = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adapter = new ArrayAdapter<String> ( this, android.R.layout.simple_list_item_multiple_choice,list );
		Button btn = (Button) findViewById( R.id.btnAdd );
		btn.setOnClickListener( new OnClickListener()
		{
			@Override
			public void onClick ( View view )
			{
				EditText edit = (EditText) findViewById( R.id.txtItem );
				list.add(edit.getText().toString());
				edit.setText( "" );
				adapter.notifyDataSetChanged();
			}
		});
		
		Button del = (Button) findViewById( R.id.btnDel );
		del.setOnClickListener( new OnClickListener()
		{
			@Override
			public void onClick ( View view )
			{
				SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
				int itemCount = getListView().getCount();
				
				for ( int i = itemCount - 1; i >= 0 ; i-- )
				{
					if ( checkedItemPositions.get(i) )
					{
						adapter.remove(list.get(i));
					}
				}
				checkedItemPositions.clear(); 
				adapter.notifyDataSetChanged();
			}
		});
		
		setListAdapter(adapter);
	}
}