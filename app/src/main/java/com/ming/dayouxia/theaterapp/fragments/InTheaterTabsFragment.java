package com.ming.dayouxia.theaterapp.fragments;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ming.dayouxia.theaterapp.R;


public class InTheaterTabsFragment extends Fragment {

    private TheaterPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_tab_pager, container, false);
        mViewPager = (ViewPager)v.findViewById(R.id.pager);
        mPagerAdapter = new TheaterPagerAdapter(
                getChildFragmentManager());

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getActivity().getActionBar().setSelectedNavigationItem(position);
            }
        });
        setupTabs();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyTabs();

    }

    private void destroyTabs(){
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.removeAllTabs();
    }

    private void setupTabs(){
        final ActionBar actionBar = getActivity().getActionBar();

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

        // add text to tabs
        actionBar.addTab(
                actionBar.newTab()
                        .setText(getResources().getString(R.string.movie_now_showing))
                        .setTabListener(tabListener));
        actionBar.addTab(
                actionBar.newTab()
                        .setText(getResources().getString(R.string.movie_coming_soon))
                        .setTabListener(tabListener));

        }

    public class TheaterPagerAdapter extends FragmentStatePagerAdapter {
        public TheaterPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            // return detail fragment, need to pass some data through bundle.
            Fragment fragment = InTheaterShowingListFragment.newInstance();
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
