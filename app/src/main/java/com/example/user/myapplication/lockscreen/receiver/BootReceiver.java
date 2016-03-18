package com.example.user.myapplication.lockscreen.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.user.myapplication.lockscreen.service.LockScreenService;


/**
 * Created by Junho on 2016-03-01.
 */
public class BootReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){

            Intent i = new Intent(context, LockScreenService.class);
            context.startService(i);
        }
    }
}
