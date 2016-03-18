package com.example.user.myapplication.main.activity.task;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.myapplication.domain.Product;

/**
 * Created by Junho on 2016-03-08.
 */
public class FetchProductTask extends AsyncTask<Void, Void,Product> {

    private final Context mContext;
    public FetchProductTask(Context context){
        mContext = context;
    }

    @Override
    protected Product doInBackground(Void... params) {
        return null;
    }
}
