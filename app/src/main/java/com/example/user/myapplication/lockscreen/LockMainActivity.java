package com.example.user.myapplication.lockscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.lockscreen.custom.OnlySeekableSeekBar;
import com.example.user.myapplication.lockscreen.custom.TimeHandler;
import com.example.user.myapplication.lockscreen.fragment.SlideFragment;
import com.example.user.myapplication.lockscreen.task.AddPointTask;
import com.example.user.myapplication.lockscreen.task.GetTotalTask;
import com.example.user.myapplication.lockscreen.task.ProductsTask;
import com.example.user.myapplication.lockscreen.viewpager.ScreenSlidePagerAdapter;
import com.example.user.myapplication.lockscreen.viewpager.VerticalViewPager;
import com.example.user.myapplication.util.Constants;
import com.example.user.myapplication.util.UserSessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class LockMainActivity extends AppCompatActivity implements LockscreenToSeekbar,SlideFragmentToLockMain {

    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;
    private int selectedPage;

    private OnlySeekableSeekBar seekBar;
    private TextView day;
    private TextView clock;

    Product[] product;
    Product getProduct;

    UserSessionManager session;

    String userEmail;
    ImageView locklefticon;

    private ArrayList<Integer> tmparr;
    private static int MAX;

    int mCurrentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lockactivity_main);
        init();

        new TimeHandler(clock).start();
        new TimeHandler(day,"yyyy년 MM월 dd일 E요일",TimeHandler.UP_A_DAY).start();

        HashMap<String,String> user = session.getUserDetails();
        userEmail = user.get(UserSessionManager.KEYEMAIL);

        seekBar.setOnSeekBarChangeListener(new OnlySeekableSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

        //잠금화면을 종료하지않고 화면을 다시 잠갔을때 페이지 저장해놓은다.
        //finish를 해서 계속 0으로 보여지는데 random하게 보여지려면 여기서 초기값을 바꿔야한다.
        selectedPage = 0;

        if(savedInstanceState != null){
            selectedPage = savedInstanceState.getInt("SELECTED_PAGE");
        }

        mPager.setCurrentItem(selectedPage);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }
    // Random 변수 초기화.
    private void random(){
        int a[] =new int[MAX];
        Random ran = new Random();
        for(int i = 0 ; i < MAX ; i++){
            a[i] = ran.nextInt(MAX);
            for(int j = 0; j < i ;j++){
                if(a[i] == a[j]){
                    i--;
                }
            }
        }
        for(int k = 0 ; k < MAX ; k++){
            tmparr.add(a[k]);
        }
    }
    private void init(){
        try {
            MAX = new GetTotalTask().execute().get();
            product = new ProductsTask().execute().get();
            System.out.println("MAIN ACTIVIYU" + product[0].toString());
        }catch (ExecutionException e){ Log.e(Constants.ERROR,e.getStackTrace().toString());
        }catch (InterruptedException e){ Log.e(Constants.ERROR,e.getStackTrace().toString());}

        session = new UserSessionManager(this);
        mPager = (VerticalViewPager)findViewById(R.id.pager);

        day = (TextView)findViewById(R.id.lock_date);
        clock = (TextView)findViewById(R.id.lock_clock);

        locklefticon =(ImageView)findViewById(R.id.lock_left);

        seekBar = new OnlySeekableSeekBar(this);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),MAX,product);

        mPager.setAdapter(mPagerAdapter);
        ///현재 보여지는 Fragment를 받아오기 위한 ViewPager 리스너.
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                changeLeftIcon();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tmparr = new ArrayList<Integer>();
        random();
    }

    private String doSomethingWithCurrentFragment(){

        SlideFragment fragment = (SlideFragment)mPagerAdapter.getRegisteredFragment(mCurrentPosition);
        System.out.println(fragment.getProductURL());
        return fragment.getProductURL();
    }

    @Override
    public void onStopSeekbar(SeekBar seekbar) {
        OnlySeekableSeekBar sb = (OnlySeekableSeekBar)seekbar;
        if(sb.getProgress() <= 20 ){
            doSomethingWithCurrentFragment();
            Uri uri = Uri.parse(doSomethingWithCurrentFragment());
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
            new AddPointTask().execute(userEmail, String.valueOf(getProduct.getNo()));
            sb.setProgress(50);
            finish();
        }
        if(sb.getProgress()>=20 && sb.getProgress() < 50){
            sb.setProgress(50);
        }
        if(sb.getProgress() > 50 && sb.getProgress() <80){
            sb.setProgress(50);
        }
        if(sb.getProgress() >= 80 ){
            finish();
        }
    }

    @Override
    public void changeLeftIcon() {
        SlideFragment fragment = (SlideFragment)mPagerAdapter.getRegisteredFragment(mCurrentPosition);
        if(fragment.getProductType().equals("download")){
            locklefticon.setImageResource(R.drawable.ic_lockactivity_download_app_black_36dp);
        }else if(fragment.getProductType().equals("browser")){
            locklefticon.setImageResource(R.drawable.ic_lockactivity_browser_black_36dp);
        }else if(fragment.getProductType().equals("watching")){
            locklefticon.setImageResource(R.drawable.ic_lockactivity_watching_black_36dp);
        }
    }




}
