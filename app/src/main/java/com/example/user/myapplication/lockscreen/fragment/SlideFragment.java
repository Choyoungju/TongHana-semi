package com.example.user.myapplication.lockscreen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.lockscreen.SlideFragmentToLockMain;
import com.example.user.myapplication.util.Constants;

/**
 * Created by Junho on 2016-03-03.
 */
public class SlideFragment extends Fragment {

    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    public static final String PRODUCT ="PRODUCT";
    private SlideFragmentToLockMain mCallback;


    TextView urlText;
    TextView typeText;


    public SlideFragment(){

    }
    public static SlideFragment newInstance(int position,Product product){
        SlideFragment f = new SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SlideFragment.EXTRA_POSITION, position);
        bundle.putSerializable(SlideFragment.PRODUCT, product);
        f.setArguments(bundle);
        return f;
    }

    public String getProductURL(){
        return urlText.getText().toString();
    }
    public String getProductType(){ return typeText.getText().toString(); }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (SlideFragmentToLockMain)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement SlideFragmentToLockMain");
        }
    }

    @Override
    public void onDetach() {
        mCallback=null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.lockactivity_slide_page, container, false);

        ImageView img = (ImageView)rootView.findViewById(R.id.sliceimg);
        urlText = (TextView)rootView.findViewById(R.id.urltextView);
        typeText = (TextView)rootView.findViewById(R.id.typetextView);


        Product tmpProduct = (Product)getArguments().getSerializable(SlideFragment.PRODUCT);

        urlText.setText(tmpProduct.getUrl());
        urlText.setVisibility(View.INVISIBLE);

        typeText.setText(tmpProduct.getType());
        typeText.setVisibility(View.INVISIBLE);

        Glide.with(this)
                .load(Constants.IP+tmpProduct.getLoc1())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);
        return rootView;
    }
}


