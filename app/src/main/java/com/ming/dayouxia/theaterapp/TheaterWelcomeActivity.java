package com.ming.dayouxia.theaterapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.viewpagerindicator.UnderlinePageIndicator;

/**
 * Created by Administrator on 2014/10/01.
 */
public class TheaterWelcomeActivity extends FragmentActivity {
    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    int[] imageArray;

    UnderlinePageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Get the view from viewpager_main.xml
        setContentView(R.layout.activity_welcome);

        // Generate sample data
        imageArray = new int[]{R.drawable.welcomepage1, R.drawable.welcomepage2, R.drawable.welcomepage3};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(TheaterWelcomeActivity.this, imageArray);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        // ViewPager IndicatorX
        mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
        mIndicator.setFades(false);
        mIndicator.setViewPager(viewPager);

        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

                if (i == imageArray.length - 1) {
                    Log.d("Log:", "" + v);


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(TheaterWelcomeActivity.this, TheaterDrawerMainActivity.class);
                            startActivity(intent);
                        }
                    }, 1000);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
