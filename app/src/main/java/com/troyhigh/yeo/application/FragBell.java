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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class FragBell extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;
    WebView fragWebView;
    String webURL;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    public static final String WEB_CONTENT_NAME = "webContentName";


    public FragBell() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_web_layout, container,
                false);

        ivIcon = (ImageView) view.findViewById(R.id.frag_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag_text);
        fragWebView = (WebView) view.findViewById(R.id.webView);

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
                Document doc = Jsoup.connect(strings[0]).get();
                Elements table = doc.select("table.bell-schedule");
                Elements trs = table.select("tr");

                for (Element e:trs){
                    Elements bell_period = trs.select("td.col1");
                    Elements bell_start = trs.select("td.col2");
                    Elements bell_end = trs.select("td.col3");
                    Elements bell_length = trs.select("td.col4");

                    text1.append('\n').append(bell_period).append("--").append(bell_start)
                            .append(" to ").append(bell_end).append(" - length ")
                            .append(bell_length).toString();

                }

                //description.remove(0);



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

            //fragTextView.setText(Html.fromHtml(result));
            //fragTextView.setText(result);

            //mProgressDialog.dismiss();
        }

    }

}
