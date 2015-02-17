package com.ming.dayouxia.gateway.tabhostfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ming.dayouxia.gateway.fragments.InTheaterShowingListFragment;
import com.ming.dayouxia.theaterapp.R;

/**
 * Created by dayouxia on 9/27/14.
 */

// this fragment is going to use tabhosts
public class TheaterHomeTabHostFragment extends Fragment {


    private FragmentTabHost mTabHost;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_host_home, container, false);

        mTabHost = (FragmentTabHost)v.findViewById(R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Tab 1"),
                InTheaterShowingListFragment.class, null);
        mTabHost.getCurrentTabView().setBackgroundColor(Color.DKGRAY);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Tab 2"),
                InTheaterShowingListFragment.class, null);
        mTabHost.getTabWidget().getChildTabViewAt(1).setBackgroundColor(Color.DKGRAY);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
