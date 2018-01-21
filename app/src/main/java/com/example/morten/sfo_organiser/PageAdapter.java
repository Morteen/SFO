package com.example.morten.sfo_organiser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by morten on 15.01.2018.
 */

public class PageAdapter extends FragmentPagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Log.d("Pagermelding","sidetall="+i);
        switch (i){
            case 0: return new FragmentVelkom();//new FragmentVelkom();
            case 1: return  new FragmentSjekkInn();
            case 2: return  new  FragmentOverSikt();//new FragmentOverSikt();
            default:break;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
