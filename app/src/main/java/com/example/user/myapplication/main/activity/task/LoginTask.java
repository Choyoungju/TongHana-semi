package com.example.user.myapplication.main.activity.task;

import android.os.AsyncTask;

import com.example.user.myapplication.domain.User;
import com.example.user.myapplication.util.Constants;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Junho on 2016-03-08.
 */
public class LoginTask extends AsyncTask<String,Void,User>{


    @Override
    protected User doInBackground(String... params) {
        String email =params[0];
        String password =params[1];

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity(Constants.IP+Constants.LOGIN+"?email="+email+"&password="+password,User.class);

        User user = userResponseEntity.getBody();



        return user;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

}
