package com.example.user.myapplication.main.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.main.activity.adapter.Tab3BaseAdapter;
import com.example.user.myapplication.main.activity.custom.ExpandableHeightGridView;

@SuppressLint("ValidFragment")
public class Tab3 extends Fragment {

	private ExpandableHeightGridView gridView;
	private TextView mileage;
	private static final Integer[] tmpImage =new Integer[]{R.drawable.ic_tab3_tmp_item1,R.drawable.ic_tab3_tmp_item2,R.drawable.ic_tab3_tmp_item3,R.drawable.ic_tab3_tmp_item4,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};

		@Override
		public View onCreateView(LayoutInflater inflater, 
				ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.activity_tab3, container,false);
			///// Tab3 Mileage/////
			mileage = (TextView)view.findViewById(R.id.tab3mileage);




			gridView = (ExpandableHeightGridView)view.findViewById(R.id.gridview);
			gridView.setExpanded(true);
			setAdapters();
	    	return view;
		}
	private void setAdapters(){
		Integer[] gridImageList = tmpImage;
		Tab3BaseAdapter gridViewAdapter = new Tab3BaseAdapter(getActivity(), R.layout.tab3_list_view_item_layout, R.id.gridview, gridImageList);
		gridView.setAdapter(gridViewAdapter);
	}

}