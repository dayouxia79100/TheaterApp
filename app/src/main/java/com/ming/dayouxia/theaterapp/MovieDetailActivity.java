package com.ming.dayouxia.theaterapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class MovieDetailActivity extends FragmentActivity {

    private MovieDetailFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_home);
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getSupportFragmentManager();
        mDetailFragment = (MovieDetailFragment) fm.findFragmentById(R.id.container);
        if(mDetailFragment == null){
            mDetailFragment = new MovieDetailFragment();
            fm.beginTransaction()
                    .replace(R.id.container, mDetailFragment)
                    .commit();
        }
    }

}
