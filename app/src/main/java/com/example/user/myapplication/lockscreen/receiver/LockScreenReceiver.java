package com.example.user.myapplication.lockscreen.receiver;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.example.user.myapplication.lockscreen.LockMainActivity;


/**
 * Created by Junho on 2016-02-26.
 */
public class LockScreenReceiver extends BroadcastReceiver {

    //This is deprecated, but it is a simple way to disable the lockscreen in code
    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock keyLock = null;

//전화상태 체크
    private TelephonyManager telephonyManager = null;
    private boolean isPhoneIdle = true;


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();


        //If the screen was just turned on or it just booted up, start your Lock Activity
        if(action.equals(Intent.ACTION_SCREEN_OFF))
        {

            //기본 잠금화면 없애기
            if(km == null){
                km = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);

            }
            if(keyLock == null){
                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
            }

         //전화상태에서 잠금화면 없애기
            if(telephonyManager == null){
                telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
            }
            if(isPhoneIdle) {


                disableKeyguard();


                Intent i = new Intent(context, LockMainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        }
    }
    public void disableKeyguard() {
        keyLock.disableKeyguard();
    }
    public void reenableKeyguard(){
        keyLock.reenableKeyguard();
    }


    //전화 상태 체크!
    private PhoneStateListener phoneListener = new PhoneStateListener(){

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE:
                    isPhoneIdle = true;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    isPhoneIdle = false;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    isPhoneIdle = false;
                    break;


            }

        }
    };
}
