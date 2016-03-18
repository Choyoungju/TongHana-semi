package com.example.user.myapplication.lockscreen.custom;

import android.os.Handler;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Junho on 2016-03-09.
 */
public class TimeHandler {
    private String TIME_FORMAT = "aa hh:mm:ss";
    private SimpleDateFormat sdf;
    private TextView clock;
    private Handler handler = new Handler();
    private Runnable updater;

    //define update time as millisecond unit
    private long updateTime = UP_SEC;

    private static final long THOUSAND = 1000;
    public static final long UP_SEC = THOUSAND * 1;
    public static final long UP_MIN = UP_SEC * 60;
    public static final long UP_HOUR = UP_MIN * 60;
    public static final long UP_H_DAY = UP_HOUR * 12;
    public static final long UP_A_DAY = UP_H_DAY * 2;

    public TimeHandler(TextView clock){
        this.clock = clock;
        sdf = new SimpleDateFormat(TIME_FORMAT , Locale.KOREA);

    }

    public TimeHandler(TextView clock, String format){
        TIME_FORMAT = format;
        this.clock = clock;
        sdf = new SimpleDateFormat(TIME_FORMAT , Locale.KOREA);
    }
    public TimeHandler(TextView clock , String format , long updateTime){
        this(clock,format);
        this.updateTime = updateTime;
    }

    public void start(){
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        timer.schedule(tt,0,updateTime);
    }
    private void update(){
        updater = new Runnable() {
            @Override
            public void run() {
                clock.setText(sdf.format(new Date()));
            }
        };
        handler.post(updater);
    }
}
