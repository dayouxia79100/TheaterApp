package com.ming.dayouxia.theaterapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class ColumbusLandFragment extends Fragment{

    private static final String WEBSITE_URL = "http://www.columbusland.net";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("Cbus land", "launching the website");
        View rootView = inflater.inflate(R.layout.fragment_columbus_land, container, false);
        WebView website = (WebView)rootView.findViewById(R.id.webview);
        website.getSettings().setJavaScriptEnabled(true);
        website.loadUrl(WEBSITE_URL);

        return rootView;
    }

}
