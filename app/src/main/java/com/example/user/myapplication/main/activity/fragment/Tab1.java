package com.example.user.myapplication.main.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.lockscreen.service.LockScreenService;
import com.example.user.myapplication.main.activity.task.FetchProductTask;
import com.example.user.myapplication.main.activity.task.GetPointlTask;
import com.example.user.myapplication.sqlite.MySQLiteOpenHelper;
import com.example.user.myapplication.util.Constants;
import com.example.user.myapplication.util.UserSessionManager;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@SuppressLint("ValidFragment")
public class Tab1 extends Fragment {
	UserSessionManager session;

	SQLiteDatabase db;
	MySQLiteOpenHelper helper;
	Product product;
	TextView mileage;
	ImageView tmpImage;
	int mDegree = 0;
		@Override
		public View onCreateView(LayoutInflater inflater, 
				ViewGroup container, Bundle savedInstanceState){
			View rootView = inflater.inflate(R.layout.activity_tab1, container,false);

			helper = new MySQLiteOpenHelper(getActivity());
			session = new UserSessionManager(getActivity());

			//로그인 후 잠금화면을 기본적으로 보여주는 설정을 하는 코드

			String lockFlag = session.getLockcheck();
			if(lockFlag.equals("TRUE")){
				Intent i = new Intent(getActivity(), LockScreenService.class);
				getActivity().startService(i);
			}else if(lockFlag.equals("FALSE")){

			}


			db = helper.getWritableDatabase();

			FetchProductTask productTask = new FetchProductTask(getActivity());

			productTask.execute();

			//////// Login  ////////////

			TextView textMileage = (TextView)rootView.findViewById(R.id.home_point_wait);

			///image rotate///
			tmpImage = (ImageView)rootView.findViewById(R.id.home_point_circle);

			//Step1.Create the RotateAnimation Object
			RotateAnimation anim = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			//Step2.Set the Animation Properties
			anim.setInterpolator(new LinearInterpolator());

			anim.setRepeatCount(2);
			anim.setDuration(700);


			// Step 3: Start animating the image
			tmpImage.startAnimation(anim);

			// Later. if you want to  stop the animation
			//tmpImage.setAnimation(null);

			HashMap<String,String> user = session.getUserDetails();
			String userEmail = user.get(UserSessionManager.KEYEMAIL);

			try{
				int point = new GetPointlTask().execute(userEmail).get();
				textMileage.setText(String.valueOf(point));
			}catch (ExecutionException e){ Log.e(Constants.ERROR, e.getStackTrace().toString());
			}catch (InterruptedException e){ Log.e(Constants.ERROR,e.getStackTrace().toString());}



			return rootView;
		}


}