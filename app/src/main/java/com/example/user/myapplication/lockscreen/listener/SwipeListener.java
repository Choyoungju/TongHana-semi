package com.example.user.myapplication.lockscreen.listener;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Junho on 2016-03-02.
 */
public class SwipeListener implements View.OnTouchListener{
    private int min_distance=100;
    private float downX,downY,upX,upY;
    View v;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.v = v;
        switch (event.getAction()){
            //Check vertical and horizontal touches
            case MotionEvent.ACTION_DOWN:{
                downX = event.getX();
                downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP:{
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                //HORIZONTAL SCROLL
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > min_distance) {
                        // left or right
                        if (deltaX < 0) {
                            this.onLeftToRightSwipe();
                            return true;
                        }
                        if (deltaX > 0) {
                            this.onRightToLeftSwipe();
                            return true;
                        }
                    } else {
                        //not long enough swipe...
                        return false;
                    }
                }
                //VERTICAL SCROLL
                else {
                    if (Math.abs(deltaY) > min_distance) {
                        // top or down
                        if (deltaY < 0) {
                            this.onTopToBottomSwipe();
                            return true;
                        }
                        if (deltaY > 0) {
                            this.onBottomToTopSwipe();
                            return true;
                        }
                    } else {
                        //not long enough swipe...
                        return false;
                    }
                }
                return false;

            }

        }


        return false;
    }
    public void onLeftToRightSwipe(){
        Log.d("SWIPE","left to right");
        Toast.makeText(v.getContext(), "left to right",
                Toast.LENGTH_SHORT).show();
    }

    public void onRightToLeftSwipe() {
        Log.d("SWIPE","right to left");
        Toast.makeText(v.getContext(),"right to left",
                Toast.LENGTH_SHORT).show();
    }

    public void onTopToBottomSwipe() {
        Log.d("SWIPE","top to bottom");
        Toast.makeText(v.getContext(),"top to bottom",
                Toast.LENGTH_SHORT).show();
    }

    public void onBottomToTopSwipe() {
        Log.d("SWIPE","bottom to top");
        Toast.makeText(v.getContext(),"bottom to top",
                Toast.LENGTH_SHORT).show();
    }

}
