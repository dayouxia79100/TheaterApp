package com.ming.dayouxia.gateway.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ming.dayouxia.theaterapp.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by dayouxia on 11/12/14.
 */
public class NewsEventFragment extends Fragment {

    private static final String WEBSITE_URL = "http://gatewayfilmcenter.com/news-and-events";
    private CircularProgressBar mProgressWheel;
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_event, container, false);

        mWebView = (WebView)rootView.findViewById(R.id.webview_gateway_news);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.getSettings().setBuiltInZoomControls(true);
        mProgressWheel = (CircularProgressBar) rootView.findViewById(R.id.progress_circle_gateway_news);


        mWebView.loadUrl(WEBSITE_URL);

         mWebView.setWebViewClient(new WebViewClient() {

             @Override
             public void onPageFinished(WebView view, String url) {
                 mProgressWheel.setVisibility(View.GONE);
             }
         });



        return rootView;
  }



}

