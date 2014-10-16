package com.ming.dayouxia.theaterapp;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ming.dayouxia.theaterapp.R;

public class  ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    int[] imageArray;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, int[] imageArray) {
        this.context = context;
        this.imageArray = imageArray;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        ImageView imgflag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        //txtrank = (TextView) itemView.findViewById(R.id.rank);
        //txtcountry = (TextView) itemView.findViewById(R.id.country);
        //txtpopulation = (TextView) itemView.findViewById(R.id.population);

        // Capture position and set to the TextViews
        //txtrank.setText(rank[position]);
        //txtcountry.setText(country[position]);
        //txtpopulation.setText(population[position]);

        // Locate the ImageView in viewpager_item.xml
        imgflag = (ImageView) itemView.findViewById(R.id.image);
        // Capture position and set to the ImageView
        imgflag.setImageResource(imageArray[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}