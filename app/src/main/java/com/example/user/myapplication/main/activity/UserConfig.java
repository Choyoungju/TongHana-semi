package com.example.user.myapplication.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.myapplication.R;
import com.example.user.myapplication.util.UserSessionManager;

/**
 * Created by Junho on 2016-03-14.
 */
public class UserConfig extends AppCompatActivity {
    //로그아웃
    //비밀번호 변경
    //회원정보수정
    //회원탈퇴
    //추천인
    //
    UserSessionManager session;
    private Button usrLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userconfig);

        session = new UserSessionManager(this);
        usrLogout = (Button)findViewById(R.id.userlogout);
        usrLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.logoutUser();
                finish();
            }
        });

    }

}
