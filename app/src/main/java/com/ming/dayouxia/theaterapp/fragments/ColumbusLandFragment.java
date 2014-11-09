package com.ming.dayouxia.theaterapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ming.dayouxia.theaterapp.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class ColumbusLandFragment extends Fragment{

    private static final String WEBSITE_URL = "http://www.columbusland.net";
    private CircularProgressBar mProgressWheel;
    private WebView mWebView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Cbus land", "launching the website");
        View rootView = inflater.inflate(R.layout.fragment_columbus_land, container, false);

        mWebView = (WebView)rootView.findViewById(R.id.webview);
        mProgressWheel = (CircularProgressBar) rootView.findViewById(R.id.progress_circle);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(WEBSITE_URL);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressWheel.setVisibility(View.GONE);
            }
        });

        return rootView;
    }
}
