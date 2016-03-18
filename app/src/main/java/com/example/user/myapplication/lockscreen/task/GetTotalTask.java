package com.example.user.myapplication.lockscreen.task;

import android.os.AsyncTask;

import com.example.user.myapplication.util.Constants;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kimmin-young on 2016. 3. 9..
 */
public class GetTotalTask extends AsyncTask<Void,Void, Integer> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Integer doInBackground(Void... params) {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<Integer> integerResponseEntity = restTemplate.getForEntity(Constants.IP+Constants.GETTOTAL,Integer.class);

        int cnt =integerResponseEntity.getBody();

        return cnt;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
