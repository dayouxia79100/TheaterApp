package com.ming.dayouxia.theaterapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ming.dayouxia.theaterapp.MovieDetailActivity;
import com.ming.dayouxia.theaterapp.R;
import com.ming.dayouxia.theaterapp.TheaterDrawerMainActivity;
import com.ming.dayouxia.theaterapp.model.Movie;

import java.io.IOException;
import java.util.ArrayList;


// this fragment displays now or coming soon.

public class InTheaterShowingListFragment extends Fragment {

    private ListView mListView;
    private String[] movieList = {"Star Trek", "Dumb and Dumber to", "The hunger games"};
    private int[] iconList = {R.drawable.startrek, R.drawable.dumb_and_dumber, R.drawable.hunger_game};


    public static InTheaterShowingListFragment newInstance(){
        InTheaterShowingListFragment fragment = new InTheaterShowingListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_theater_home_listview, container, false);
        mListView = (ListView)v.findViewById(R.id.theater_home_listview);

        ArrayList<Movie> movies = new ArrayList();
        for(int i = 0; i < 12; i++){
            movies.add(new Movie(movieList[i%3], "Show time: 3:00PM to 6:00 PM", iconList[i%3]));
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
           ViewHolder holder = null;
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.home_listview_item, parent, false);
               holder = new ViewHolder(convertView);
               convertView.setTag(holder);
            }
            else{
             holder = (ViewHolder) convertView.getTag();
            }

            Movie current =  getItem(position);

            ImageView moviePreview = (ImageView)convertView.findViewById(R.id.movie_preview_image);
            TextView movieTitle = (TextView) convertView.findViewById(R.id.movie_name_text);
            TextView showTime = (TextView) convertView.findViewById(R.id.show_time_text);

            moviePreview.setImageResource(current.getIcon());
            movieTitle.setText(current.getMovieName());
            showTime.setText(current.getShowTime());

            holder.getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment frag = new MovieTicketsWebFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, frag).commit();
                }
            });

            return convertView;
        }
    }

    private class ViewHolder{
        private View base;
        private Button button;

        public ViewHolder(View base){
            this.base = base;
        }
        public Button getButton(){
            if (button ==null){
                button = (Button) base.findViewById(R.id.button_purchase);
            }
            return button;
        }



    }

}
