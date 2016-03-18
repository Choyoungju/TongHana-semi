package com.example.user.myapplication.lockscreen.task;

import android.os.AsyncTask;

import com.example.user.myapplication.util.Constants;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kimmin-young on 2016. 3. 10..
 */
public class AddPointTask  extends AsyncTask<String,Void, Boolean> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String email = params[0];
        String no = params[1];


        Map<String, Object> map = new ConcurrentHashMap<>();

        map.put("email", email);
        map.put("no", no);

        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        URI uri = URI.create(Constants.IP + Constants.ADDPOINT+no+"/"+email);


        rt.put(uri, map);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}