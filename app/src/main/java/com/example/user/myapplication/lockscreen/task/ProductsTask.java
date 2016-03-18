package com.example.user.myapplication.lockscreen.task;

import android.os.AsyncTask;

import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.util.Constants;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kimmin-young on 2016. 3. 9..
 */
public class ProductsTask extends AsyncTask<Void,Void,Product[]> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Product[] doInBackground(Void... params) {


        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setAcceptEncoding(ContentCodingType.GZIP);
        HttpEntity<?> reqEntity = new HttpEntity<Object>(reqHeaders);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<Product[]> productResponseEntity =
                restTemplate.exchange(Constants.IP+Constants.LIST, HttpMethod.GET,reqEntity,Product[].class);


        Product[] product = productResponseEntity.getBody();

        return product;
    }

    @Override
    protected void onPostExecute(Product[] product) {
        super.onPostExecute(product);
    }
}
