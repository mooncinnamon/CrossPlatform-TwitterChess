package com.cinnamon.moon.twitterchess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.cinnamon.moon.twitterchess.SharedPreferences.SharedMaster;

public class MainActivity extends AppCompatActivity {

    private SharedMaster sharedMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedMaster = new SharedMaster(getApplicationContext());


        if(sharedMaster.getSharedPref("oauth-login", false)){
            
        }else {
            getLogin();
        }
    }

    private void getLogin() {
        Log.d("twitter-chess", "호출됨");
    }
}
