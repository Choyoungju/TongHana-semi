package com.example.user.myapplication.main.activity.task;

import android.os.AsyncTask;

import com.example.user.myapplication.util.Constants;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kimmin-young on 2016. 3. 9..
 */
public class GetPointlTask extends AsyncTask<String,Void, Integer> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Integer doInBackground(String... params) {

        String email = params[0];

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        String url = Constants.IP+ Constants.GETPOINT+email;

        ResponseEntity<Integer> integerResponseEntity = restTemplate.getForEntity(url,Integer.class);

        int cnt =integerResponseEntity.getBody();

        return cnt;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
