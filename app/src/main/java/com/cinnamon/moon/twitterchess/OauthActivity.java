package com.cinnamon.moon.twitterchess;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.cinnamon.moon.twitterchess.Javascript.GetPincodeScript;

/**
 * Created by moonp on 2018-03-19.
 */

@SuppressLint("Registered")
public class OauthActivity extends Activity {

    private WebView webView;
    private EditText editText;
    private Button button;
    private Handler handler;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouath);

        handler = new Handler();

        webView = findViewById(R.id.loginForm);
        editText = findViewById(R.id.pinForm);
        button = findViewById(R.id.sendButton);


        Intent intent = getIntent();
        String url = intent.getStringExtra("token url");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new GetPincodeScript(handler, editText), "PINCODE");

        webView.setWebViewClient(client);
        webView.loadUrl(url);
    }

    private WebViewClient client = new WebViewClient(){
      public void onPageFinished(WebView view, String url){
          super.onPageFinished(view, url);
          view.loadUrl("javascript:window.PINCODE.getPinCode(document.getElementById('oauth_pin').innerHTML);");
      }
    };

    public class MessageHandler extends Handler{
        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);


        }
    }
}
