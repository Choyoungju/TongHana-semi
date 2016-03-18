package com.example.user.myapplication.lockscreen.custom;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

import com.example.user.myapplication.lockscreen.LockMainActivity;

/**
 * Created by Junho on 2016-03-04.
 */
public class OnlySeekableSeekBar extends SeekBar{

    private static LockMainActivity mCallback;
    private float progress;

    @Override
    public Handler getHandler() {
        return super.getHandler();
    }

    public OnlySeekableSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCallback = (LockMainActivity)context;

    }

    public OnlySeekableSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCallback = (LockMainActivity)context;
    }

    public OnlySeekableSeekBar(Context context) {
        super(context);
        mCallback = (LockMainActivity)context;
       // mCallback.onStopSeekbar();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                final int width = getWidth();
                final int available = width - getPaddingLeft() - getPaddingRight();
                int x = (int) event.getX();
                float scale;
                progress = 0;
                if (x < getPaddingLeft()) {
                    scale = 0.0f;
                } else if (x > width - getPaddingRight()) {
                    scale = 1.0f;
                } else {
                    scale = (float) (x - getPaddingLeft()) / (float) available;
                }
                final int max = getMax();
                progress += scale * max;
                if (progress < 0) {
                    progress = 0;
                } else if (progress > max) {
                    progress = max;
                }

                if (Math.abs(progress - getProgress()) < 10)
                    return super.onTouchEvent(event);
                else
                    return false;
                case MotionEvent.ACTION_UP:
                    setProgress(getProgress());
                    mCallback.onStopSeekbar(this);
                    return true;
            default:
                return super.onTouchEvent(event);
        }
    }


}
