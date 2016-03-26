package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;

/**
 * Created by exequiel on 03/08/2015.
 */
public class WebViewFragment extends FrameworkBaseFragment {

    private WebView webView;


    private static final String TAG_FRAGMENT = "WEB_VIEW_FRAGMENT";
    public static final String KEY_URL_LOAD = "KEY_URL_LOAD";

    private String urlLoad="https://m.jeanscentre.nl/?utm_medium=app";


    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater,container,savedInstanceState, R.layout.fragment_web_view);

        if (getArguments()!=null && getArguments().getString(KEY_URL_LOAD)!=null){
            urlLoad = getArguments().getString(KEY_URL_LOAD);
        }

        if (webView==null){
            webView = (WebView) view.findViewById(R.id.fragment_shop_web_view);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {

                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        }
            webView.loadUrl(urlLoad);
        return view;
    }

}
