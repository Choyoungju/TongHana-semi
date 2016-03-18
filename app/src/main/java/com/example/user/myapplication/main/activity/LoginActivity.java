package com.example.user.myapplication.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.User;
import com.example.user.myapplication.main.activity.task.LoginTask;
import com.example.user.myapplication.util.UserSessionManager;

import java.util.concurrent.ExecutionException;

/**
 * Created by Junho on 2016-03-08.
 */
public class LoginActivity extends AppCompatActivity{

    Button btnLogin;
    EditText txtEmailaddress, txtPassword;
    // User Session Manager Class
    UserSessionManager session;
    LoginTask loginTask;
    User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        session = new UserSessionManager(this);

        txtEmailaddress = (EditText)findViewById(R.id.emailaddress);
        txtPassword = (EditText)findViewById(R.id.password);

        Toast.makeText(this,"User Login Status"+session.isUserLogIn(),Toast.LENGTH_SHORT).show();

        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmailaddress.getText().toString();
                String password = txtPassword.getText().toString();

                if(email.trim().length() > 0 && password.trim().length() > 0){
                    //로그인 통신 동작.
                    try {
                        user = new LoginTask().execute(email,password).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    if(user != null){
                        //로그인 시 앱을 관통할 pref 선언
                        //여기서 로그인 하고 통신 동작의 값을 받아와서 넣어줌!
                        //userId & password &
                        // 일단 String 박아둠.
                        session.createUserLoginSession(user.getEmail(),user.getPassword(),"TRUE");
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        // Starting MainActivity
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "UserId, Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    // user didn't entered username or password
                    Toast.makeText(LoginActivity.this,"Please enter username and password",Toast.LENGTH_LONG).show();

                }
            }
        });



    }
    //뒤로가기 버튼 막기.

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        return ;
    }
}
