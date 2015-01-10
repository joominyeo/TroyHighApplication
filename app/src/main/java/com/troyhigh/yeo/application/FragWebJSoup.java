package com.troyhigh.yeo.application;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class FragWebJSoup extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;
    WebView fragWebView;
    String webURL;
    AdView mAdView;


    TextView fragTextView;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    public static final String WEB_CONTENT_NAME = "webContentName";


    public FragWebJSoup() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_web_layout, container,
                false);

        ivIcon = (ImageView) view.findViewById(R.id.frag_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag_text);
        fragWebView = (WebView) view.findViewById(R.id.webView);
        fragTextView = (TextView)view.findViewById(R.id.textView);
        mAdView = (AdView) view.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        tvItemName.setText(getArguments().getString(ITEM_NAME));
        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));

        webURL = getArguments().getString(WEB_CONTENT_NAME);
        //fragWebView.loadUrl(webURL);
        new Description().execute(webURL);

        //fragWebView.loadData(text1.toString(), "text/html", "UTF-8");
        //fragWebView.loadData(getArguments().getString(WEB_CONTENT_NAME), "text/html", "UTF-8");

        return view;
    }

    protected class Description extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder text1 = new StringBuilder();

            try {
                // Connect to the web site
                Document document = Jsoup.connect(strings[0]).get();
                Elements description = document.select("div#content_main");
                description.select("div.pageTitle").remove();
                description.select("div#push").remove();
                description.select("div.page-files").remove();
                description.select("div.user-page-info-wrapper generic-mobile-padding dark").remove();
                description.select("table#pageNav").remove();
                //description = description.select("table#pageNav").remove();
                //description = description.removeClass("table#pageNav");

                for (org.jsoup.nodes.Element e : description) {

                    //String data = e.text();
                    //String data = e.html();
                    //data += "</br>";
                    text1.append(e.html());

                }

                return text1.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return text1.toString();
        }


        @Override
        protected void onPostExecute(String result) {

            // Set description into TextView
            fragWebView.loadData(result, "text/html", "UTF-8");
            //fragTextView.setText(result);

            //fragTextView.setText(Html.fromHtml(result));
            //fragTextView.setText(result);

            //mProgressDialog.dismiss();
        }

    }

}
