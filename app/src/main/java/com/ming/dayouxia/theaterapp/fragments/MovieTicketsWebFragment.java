package com.ming.dayouxia.theaterapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ming.dayouxia.theaterapp.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by Angello on 2/1/2015.
 */
public class MovieTicketsWebFragment extends Fragment{
    private static final String WEBSITE_URL = "http://www.movietickets.com/theater/hid/22812";
    private CircularProgressBar mProgressWheel;
    WebView mWebView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("MovieTIckets", "launching the website");
        View rootView = inflater.inflate(R.layout.fragment_movie_tickets, container, false);

        mWebView = (WebView)rootView.findViewById(R.id.webview_movie_tickets);
        mProgressWheel = (CircularProgressBar) rootView.findViewById(R.id.progress_circle);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(WEBSITE_URL);

        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressWheel.setVisibility(View.GONE);
            }
        });

        return rootView;
    }



}
