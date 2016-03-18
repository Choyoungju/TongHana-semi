package com.example.user.myapplication.main.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.myapplication.main.activity.fragment.Tab1;
import com.example.user.myapplication.main.activity.fragment.Tab2;
import com.example.user.myapplication.main.activity.fragment.Tab3;
import com.example.user.myapplication.main.activity.fragment.Tab4;

/**
 * Created by Junho on 2016-03-08.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private int tabNum;

    public PagerAdapter(FragmentManager fm,int tabNum) {
        super(fm);
        this.tabNum=tabNum;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;
            case 3:
                Tab4 tab4 = new Tab4();
                return tab4;

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return this.tabNum;
    }
}
