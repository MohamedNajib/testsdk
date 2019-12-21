package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

public class webview extends AppCompatActivity {
    public static  String folder ;
    public static  String file ;

    public static  String web_index ;
    String urlString ;
    private WebView webx;

    private ProgressDialog progressBar;
    public static String tash_path = "https://www.niaba-laws.com/Images/Index?path=file&folder=folder_&T=1" ;
    public static String ahkam_path ="https://www.niaba-laws.com/Images/Index?path=file&folder=folder_&T=2" ;
    public static String dawry = "niabalaws.com/IOS_PICS/dawry_pdf/" ;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webx = (WebView) findViewById(R.id.webview1);
        webx.getSettings().setJavaScriptEnabled(true);
        TextView    titelTxt = (TextView) findViewById(R.id.txtTitel);
        Typeface type3 = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        titelTxt.setTypeface(type3);
        webx.setWebViewClient(new WebViewClient());
        webx.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webx.getSettings().setBuiltInZoomControls(true);
        webx.getSettings().setUseWideViewPort(true);
        webx.getSettings().setLoadWithOverviewMode(true);
        progressBar = new ProgressDialog(this);
        checkFileExists();
        webx.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }




        });



        ImageView homeclick= (ImageView) findViewById(R.id.homeclick);
        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

        ImageView backclick= (ImageView) findViewById(R.id.backclik);
        backclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }

    public void checkFileExists(){
        if(web_index == "0") {

            urlString   = (tash_path.replace("folder_",folder).replace("file",file));


        }else if (web_index == "1") {
            urlString   = (ahkam_path.replace("folder_",folder).replace("file",file));

        }else if (web_index == "2") {


        }


        if(!urlString.equals("")){
            CheckFileExistTask task=new CheckFileExistTask();
            task.execute(urlString);
        }
    }

    private class CheckFileExistTask extends AsyncTask<String, Void, Boolean>
    {
        @Override
        protected void onPreExecute() {
            progressBar.setMessage("Loading...");
            progressBar.show();

        }
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // This connection won't follow redirects returned by the remote server.
                HttpURLConnection.setFollowRedirects(false);
                // Open connection to the remote server
                URL url=new URL(params[0]);
                HttpURLConnection con =  (HttpURLConnection) url.openConnection();
                // Set request method
                con.setRequestMethod("HEAD");
                // get returned code
                return (con.getResponseCode() == HttpURLConnection.HTTP_OK);

            }
            catch (Exception e) {
                e.printStackTrace();

                return false;

            }
        }




        @Override
        protected void onPostExecute(Boolean result) {
            // Update status message
            if (result==true)
            {
                  webx.loadUrl( urlString);
             }
            else
            {
                progressBar.dismiss();
                Toast.makeText(getApplicationContext(), "لم يتم العثور علي الملف المطلوب", Toast.LENGTH_SHORT).show();
                finish();

             }
        }
    }


}









