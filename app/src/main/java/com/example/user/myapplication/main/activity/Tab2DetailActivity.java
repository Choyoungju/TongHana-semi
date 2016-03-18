package com.example.user.myapplication.main.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.util.Constants;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Junho on 2016-03-14.
 */
public class Tab2DetailActivity extends AppCompatActivity{

    Product product;

    Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2detail);

        Intent i = getIntent();

        product = (Product) i.getSerializableExtra("product");

        ImageView img = (ImageView) findViewById(R.id.bigimg);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);

        Glide.with(this)
                .load(Constants.IP + product.getLoc1())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "1조와 연결", Toast.LENGTH_LONG).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(product.getUrl());

                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl();

//                shareImage();

            }
        });


    }
    private void shareTextUrl(){
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);

        share.setType("text/plain");
//        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        share.putExtra(Intent.EXTRA_SUBJECT, product.getTitle());
        share.putExtra(Intent.EXTRA_TEXT, product.getUrl());

        startActivity(Intent.createChooser(share, "Share Link!"));

    }

    private void shareImage(){
        Intent share = new Intent();

        share.setAction(Intent.ACTION_SEND);

        share.setType("image/jpeg");

        try {

            Uri uri = Uri.parse(Constants.IP + product.getLoc1());

            Log.e(Constants.CHK, "12"+String.valueOf(uri));

            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            Log.e(Constants.CHK, "22"+String.valueOf(bitmap));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] byteArray = stream.toByteArray();

            share.putExtra(Intent.EXTRA_STREAM, byteArray);

            Log.e(Constants.CHK, "32"+String.valueOf(byteArray));

        }catch(FileNotFoundException e){
        }catch (IOException e){}

        startActivity(Intent.createChooser(share, "Share image!"));

    }


}
