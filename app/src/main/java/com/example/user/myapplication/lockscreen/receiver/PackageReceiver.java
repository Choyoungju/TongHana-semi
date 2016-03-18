package com.example.user.myapplication.lockscreen.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.user.myapplication.lockscreen.service.LockScreenService;

/**
 * Created by Junho on 2016-03-01.
 */
public class PackageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
            //앱이 설치되었을때
        }else if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
            //앱이 삭제 되었을때.
        }else if(action.equals(Intent.ACTION_PACKAGE_REPLACED)){
            //앱이 업데이트 되었을때.
            Intent i = new Intent(context, LockScreenService.class);
            context.startService(i);
        }
    }
}
