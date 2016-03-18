package com.example.user.myapplication.main.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.lockscreen.task.GetTotalTask;
import com.example.user.myapplication.lockscreen.task.ProductsTask;
import com.example.user.myapplication.main.activity.Tab2DetailActivity;
import com.example.user.myapplication.main.activity.adapter.Tab2BaseAdapter;

import java.util.concurrent.ExecutionException;

@SuppressLint("ValidFragment")
public class Tab2 extends Fragment {


	private ListView listView;
	Product[] products;
	public int MAX;

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_tab2, container,false);

		listView = (ListView)view.findViewById(R.id.productlistview);

		setAdapter();

		//	listview경계
		listView.setDivider(new ColorDrawable(Color.BLACK));
		listView.setDividerHeight(3);

		//Click
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(getActivity(),Tab2DetailActivity.class);

				i.putExtra("product", products[position]);

				startActivity(i);
			}
		});

		return view;
	}

	private void setAdapter(){

		Integer[] productPoint;
		Integer[] productImage;
		try{
			products = new ProductsTask().execute().get();
			MAX = new GetTotalTask().execute().get();

			productPoint = new Integer[MAX];

			for(int i=0;i<MAX;i++){
				productPoint[i] = products[i].getPoint();
			}

			productImage = productPoint;

			Tab2BaseAdapter listViewAdapter = new Tab2BaseAdapter(getActivity(),R.layout.tab2_list_view_item_layout,R.id.product_icon,productImage);

			listView.setAdapter(listViewAdapter);
		}catch(InterruptedException e){
		}catch (ExecutionException e){
		}

	}
}