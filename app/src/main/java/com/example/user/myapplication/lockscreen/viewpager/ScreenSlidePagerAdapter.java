package com.example.user.myapplication.lockscreen.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.lockscreen.fragment.SlideFragment;

/**
 * Created by Junho on 2016-03-17.
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    Product[] products;
    private int TOTALNUM;

    public ScreenSlidePagerAdapter(FragmentManager fm, int num,Product[] products) {
        super(fm);
        this.TOTALNUM = num;
        this.products = products;
    }

    @Override
    public Fragment getItem(int position) {

        return SlideFragment.newInstance(position, products[position]);
    }

    @Override
    public int getCount() {
        return TOTALNUM;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container,position);
        registeredFragments.put(position,fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }
    public Fragment getRegisteredFragment(int position){
        return registeredFragments.get(position);
    }
}