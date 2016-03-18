package com.example.user.myapplication.intro;

import android.content.Context;
import android.widget.ImageView;

import com.example.user.myapplication.R;

/**
 * Nothing but an ImageView with a preset image resource
 * @author yildizkabaran
 *
 */
public class ContentView extends ImageView {

  public ContentView(Context context){
    super(context);
    initialize();
  }


  private void initialize(){
    // set the dummy content image here
    setImageResource(R.drawable.intro);


  }
}
