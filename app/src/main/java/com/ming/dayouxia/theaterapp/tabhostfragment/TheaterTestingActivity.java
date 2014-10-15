package com.ming.dayouxia.theaterapp.tabhostfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.ming.dayouxia.theaterapp.R;
import com.ming.dayouxia.theaterapp.fragments.TheaterHomeTabsFragmentV2;


public class TheaterTestingActivity extends FragmentActivity {

    private TheaterHomeTabsFragmentV2 mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_home);
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getSupportFragmentManager();
        mFragment = (TheaterHomeTabsFragmentV2) fm.findFragmentById(R.id.container);
        if(mFragment == null){
            mFragment = new TheaterHomeTabsFragmentV2();
            fm.beginTransaction()
                    .replace(R.id.container, mFragment)
                    .commit();
        }
    }
}
