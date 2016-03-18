package com.example.user.myapplication.main.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.user.myapplication.R;
import com.example.user.myapplication.main.activity.adapter.PagerAdapter;
import com.example.user.myapplication.util.UserSessionManager;

public class MainActivity extends AppCompatActivity {
	UserSessionManager session;
	private TabLayout tabLayout;
	private int[] whiteImg = {R.drawable.ic_main_account_circle_white_36dp,R.drawable.ic_main_monetization_on_white_36dp,R.drawable.ic_main_add_shopping_cart_white_36dp,R.drawable.ic_main_build_white_36dp};
	private int[] blackImg = {R.drawable.ic_main_account_circle_black_36dp,R.drawable.ic_main_monetization_on_black_36dp,R.drawable.ic_main_add_shopping_cart_black_36dp,R.drawable.ic_main_build_black_36dp};
	private String[] imgText = {"HOME", "MONEY", "SHOPPING","SETTING"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		session = new UserSessionManager(this);



		if(session.checkLogin())
			finish();


		/////// UI .//////////
		//Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		//setSupportActionBar(toolbar);

		tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.addTab(tabLayout.newTab().setTag(0).setText("HOME").setIcon(R.drawable.ic_main_account_circle_white_36dp));//.setText("Main").setIcon(R.drawable.ic_launcher)
		tabLayout.addTab(tabLayout.newTab().setTag(1).setIcon(R.drawable.ic_main_monetization_on_black_36dp));//.setText("적립하기").setIcon(R.drawable.ic_launcher)
		tabLayout.addTab(tabLayout.newTab().setTag(2).setIcon(R.drawable.ic_main_add_shopping_cart_black_36dp));//.setText("소모하기").setIcon(R.drawable.ic_launcher)
		tabLayout.addTab(tabLayout.newTab().setTag(3).setIcon(R.drawable.ic_main_build_black_36dp));//.setText("설정").setIcon(R.drawable.ic_launcher)

		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		final PagerAdapter adapter = new PagerAdapter
				(getSupportFragmentManager(), tabLayout.getTabCount());
		viewPager.setAdapter(adapter);
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				//0123
				viewPager.setCurrentItem(tab.getPosition());
				Log.d("TAB POSITION :: ", String.valueOf(tab.getPosition()));
				refreshTab(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		//startService(new Intent(this, LockScreenService.class));
	}

	// 메뉴 탭 바꾸기.
	private void refreshTab(int position){
		for(int i = 0 ; i < tabLayout.getTabCount(); i++){
			tabLayout.getTabAt(i).setText("").setIcon(blackImg[i]);
		}
		tabLayout.getTabAt(position).setText(imgText[position]).setIcon(whiteImg[position]);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	//	public void clickDetail(View view){
//		setContentView(R.layout.activity_item_detail);
//	}
}