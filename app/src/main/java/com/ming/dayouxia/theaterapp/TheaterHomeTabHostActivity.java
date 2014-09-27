package com.ming.dayouxia.theaterapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class TheaterHomeTabHostActivity extends FragmentActivity {

    private TheaterHomeTabHostFragment mTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_home);
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getSupportFragmentManager();
        mTabFragment = (TheaterHomeTabHostFragment) fm.findFragmentById(R.id.container);
        if(mTabFragment == null){
            mTabFragment = new TheaterHomeTabHostFragment();
            fm.beginTransaction()
                    .replace(R.id.container, mTabFragment)
                    .commit();
        }
    }
}
