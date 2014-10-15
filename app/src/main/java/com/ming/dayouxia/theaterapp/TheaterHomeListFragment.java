package com.ming.dayouxia.theaterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ming.dayouxia.theaterapp.model.Movie;

import java.util.ArrayList;


public class TheaterHomeListFragment extends Fragment {

    private ListView mListView;


    public static TheaterHomeListFragment newInstance(){
        TheaterHomeListFragment fragment = new TheaterHomeListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_theater_home_listview, container, false);
        mListView = (ListView)v.findViewById(R.id.theater_home_listview);

        ArrayList<Movie> movies = new ArrayList();
        for(int i = 0; i < 12; i++){
            movies.add(new Movie());
        }


        HomeListAdapter adapter = new HomeListAdapter(movies);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    private class HomeListAdapter extends ArrayAdapter<Movie>{

        public HomeListAdapter(ArrayList<Movie> movies){
            super(getActivity(), 0, movies);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.home_listview_item, parent, false);
            }
            //Movie movieItem = getItem(position);
            return convertView;
        }
    }
}
