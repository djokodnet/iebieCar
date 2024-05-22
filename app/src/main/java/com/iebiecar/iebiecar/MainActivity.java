package com.iebiecar.iebiecar;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;


public class MainActivity extends Activity {
    WebView mainWebView = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //must be called before adding content!
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWebView = (WebView) findViewById(R.id.mainWebView);
        mainWebView.getSettings().setLoadWithOverviewMode(true);
        mainWebView.getSettings().setUseWideViewPort(true);
        mainWebView.loadUrl("http://www.iebiecar.com");

        mainWebView.setWebViewClient(new MainWebViewClient());

        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
                Window.PROGRESS_VISIBILITY_ON);

        mainWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                MainActivity.this.setTitle("Loading...");
                MainActivity.this.setProgress(progress * 100);
                if (progress == 100){
                    MainActivity.this.setTitle(view.getTitle());
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(mainWebView.canGoBack())
            mainWebView.goBack();
        else
            super.onBackPressed();
    }

    private class MainWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("Log", "loading: " + url);

            view.loadUrl(url);
            return true;
        }
    }
}