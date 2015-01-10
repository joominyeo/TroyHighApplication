package com.chang.troyhigh;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class FragText extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;
    TextView fragTextView;
    String webURL;
    AdView mAdView;


    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    public static final String WEB_CONTENT_NAME = "webContentName";


    public FragText() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_text_layout, container,
                false);

        ivIcon = (ImageView) view.findViewById(R.id.frag_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag_text);
        fragTextView = (TextView) view.findViewById(R.id.frag_content);
        mAdView = (AdView) view.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        tvItemName.setText(getArguments().getString(ITEM_NAME));
        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));

        webURL = getArguments().getString(WEB_CONTENT_NAME);
        new Description().execute(webURL);


        //fragWebView.loadData(text1.toString(), "text/html", "UTF-8");
        //fragWebView.loadData(getArguments().getString(WEB_CONTENT_NAME), "text/html", "UTF-8");

        return view;
    }
    protected class Description extends AsyncTask<String, Void, String> {

        ProgressDialog prog;
/*

        @Override
        protected void onPreExecute() {
            prog = new ProgressDialog(getActivity());
            prog.setMessage("Loading....");
            prog.show();
        }
*/

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder text1 = new StringBuilder();

            try {
                // Connect to the web site

                Document document = Jsoup.connect(strings[0]).get();
                document.outputSettings(new Document.OutputSettings().prettyPrint(false));
                document.select("br").append("br34k");
                document.select("p").prepend("br34k");

                Elements description = document.select("div#content_main");

                for (org.jsoup.nodes.Element e : description) {
                   //String data = e.text();
                    //String data = e.html();
                    //data += "</br>";
                    text1.append(e.text());
                }

                String data = text1.toString();
                data = data.replace("br34k","<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                return data;
                //return text1.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return text1.toString();
        }


        @Override
        protected void onPostExecute(String result) {

            // Set description into TextView
            //prinMessWebView.loadData(result, "text/html", "UTF-8");

            fragTextView.setText(Html.fromHtml(result));
            //fragTextView.setText(result);
            //prog.dismiss();

            //mProgressDialog.dismiss();
        }

    }

}
