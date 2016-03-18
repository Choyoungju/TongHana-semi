package com.example.user.myapplication.main.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.lockscreen.receiver.BootReceiver;
import com.example.user.myapplication.lockscreen.service.LockScreenService;
import com.example.user.myapplication.main.activity.UserConfig;
import com.example.user.myapplication.util.UserSessionManager;

/**
 * Created by Junho on 2016-03-10.
 */
public class Tab4 extends Fragment {
    UserSessionManager session;

    private Switch swc;
    private TextView usrconfig;


    private BroadcastReceiver myBroad;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_tab4, container,false);

        swc =(Switch)rootview.findViewById(R.id.swc1);
        session = new UserSessionManager(getActivity());
        myBroad = new BootReceiver();

        String lockFlag = session.getLockcheck();
        if(lockFlag.equals("TRUE")){
            swc.setChecked(true);
        }else if(lockFlag.equals("FALSE")){
            swc.setChecked(false);
        }

        swc.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String str = String.valueOf(isChecked); // boolean -> String 반환

                //상태가 on // off 인 경우에 알맞게 토스트를 띄움
                if(isChecked){

                    IntentFilter filter = new IntentFilter();
                    filter.addAction(Intent.ACTION_BOOT_COMPLETED);
                    getActivity().registerReceiver(myBroad, filter);
                    session.setLockcheck("TRUE");
                    Intent i = new Intent(getActivity(), LockScreenService.class);
                    getActivity().startService(i);
                }else{

                    session.setLockcheck("FALSE");

                    Intent i = new Intent(getActivity(), LockScreenService.class);
                    getActivity().stopService(i);

                }

            }
        });

        usrconfig = (TextView)rootview.findViewById(R.id.userconfig);
        usrconfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UserConfig.class);
                startActivity(i);
            }
        });


        return rootview;
    }

}