package com.ming.dayouxia.theaterapp.unused;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.ming.dayouxia.theaterapp.R;
import com.ming.dayouxia.theaterapp.TheaterHomeListFragment;

/**
 * Created by dayouxia on 9/27/14.
 */

// this activity is not used, we now transfer the control to fragment
public class TheaterHomeTabsActivity extends FragmentActivity {


    private TheaterPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab_pager);
        setupViewPager();
        setupTabs();
        setupDrawer();
    }

    private void setupTabs(){
        final ActionBar actionBar = getActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < 2; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }
    }

    private void setupViewPager(){
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new TheaterPagerAdapter(
                getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getActionBar().setSelectedNavigationItem(position);
            }
        });
    }

    private void setupDrawer(){

    }

    public class TheaterPagerAdapter extends FragmentStatePagerAdapter {
        public TheaterPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment = TheaterHomeListFragment.newInstance();
//            if(i == 0) fragment = TheaterHomeListFragment.newInstance();
//            if(i == 1) fragment = new MovieDetailFragment();

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }
}
