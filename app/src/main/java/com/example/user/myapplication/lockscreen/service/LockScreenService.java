package com.example.user.myapplication.lockscreen.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.user.myapplication.R;
import com.example.user.myapplication.lockscreen.receiver.LockScreenReceiver;
import com.example.user.myapplication.lockscreen.receiver.PackageReceiver;
import com.example.user.myapplication.main.activity.MainActivity;


/**
 * Created by Junho on 2016-02-26.
 */
public class LockScreenService extends Service {

    private BroadcastReceiver receiver = null;
    private PackageReceiver pReceiver = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onCreate() {
        super.onCreate();

//        Start listening for the Screen On, Screen Off, and Boot completed actions
        receiver = new LockScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);

        //Set up a receiver to listen for the Intents in this Service

        registerReceiver(receiver, filter);
        startForeground();

        // 패키지 업데이트,설치,삭제 시 receiver.
        pReceiver = new PackageReceiver();
        IntentFilter pFilter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        pFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        pFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        pFilter.addDataScheme("package");
        registerReceiver(pReceiver,pFilter);
    }


//  이게 더 추천하는 서비스 실행인데, 동작을 안함. => 되는데 느림. 동작이.
    // 다음에 수정하도록.

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("SERVICE", "onStartCommand");

        if(intent != null){
            if(intent.getAction() == null ){
                if(receiver == null){
                    receiver = new LockScreenReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    registerReceiver(receiver,filter);
                }
                if(pReceiver == null){
                    pReceiver = new PackageReceiver();

                }
            }
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {

        if(receiver != null);{
            unregisterReceiver(receiver);
        }
        if(pReceiver != null){
            unregisterReceiver(pReceiver);
        }

        super.onDestroy();
    }
    // Run service in foreground so it is less likely to be killed by system
    private void startForeground() {
        Intent notifyIntent = new Intent(getApplicationContext(), MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("잠금화면 실행중 / 광고 혜택 제공")
                .setSmallIcon(R.drawable.tonghana)
                .setContentIntent(notifyPendingIntent)
                .setOngoing(true)
                .build();
        startForeground(1, notification);
    }

}
