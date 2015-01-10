package com.troyhigh.yeo.application;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class FragWebProgBar extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;
    WebView fragWebView;
    String webURL;
    AdView mAdView;

    ProgressBar progressBar;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    public static final String WEB_CONTENT_NAME = "webContentName";


    public FragWebProgBar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_web_progress_bar, container,
                false);

        ivIcon = (ImageView) view.findViewById(R.id.frag_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag_text);
        fragWebView = (WebView) view.findViewById(R.id.webView);
        progressBar=(ProgressBar) view.findViewById(R.id.progressBar1);
        mAdView = (AdView) view.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tvItemName.setText(getArguments().getString(ITEM_NAME));
        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));

        webURL = getArguments().getString(WEB_CONTENT_NAME);
        fragWebView.setWebViewClient(new MyBrowser());
        fragWebView.getSettings().setJavaScriptEnabled(true);
        fragWebView.loadUrl(webURL);
        //new Description().execute(webURL);

        //fragWebView.loadData(text1.toString(), "text/html", "UTF-8");
        //fragWebView.loadData(getArguments().getString(WEB_CONTENT_NAME), "text/html", "UTF-8");

        return view;
    }

    private class MyBrowser extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url){
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished (WebView view, String url){
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

//    public boolean onKeyDown (int keyCode, KeyEvent event){
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && fragWebView.canGoBack()){
//            fragWebView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//        }

}
