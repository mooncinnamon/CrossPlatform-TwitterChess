package com.cinnamon.moon.twitterchess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.cinnamon.moon.twitterchess.Ouath.AccessTokenAsync;
import com.cinnamon.moon.twitterchess.Ouath.RequestTokenAsync;
import com.cinnamon.moon.twitterchess.SharedPreferences.SharedMaster;

import java.util.concurrent.ExecutionException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {

    private SharedMaster sharedMaster;
    private Twitter twitter;
    private TwitterStream twitterStream;
    private RequestToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedMaster = new SharedMaster(getApplicationContext());

        Log.d("twitter-chess", "api-key : " + BuildConfig.CONSUMER_KEY);

        TwitterFactory twitterFactory = new TwitterFactory(getConfigurationBuilder().build());
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(getConfigurationBuilder().build());
        twitter = twitterFactory.getInstance();
        twitterStream = twitterStreamFactory.getInstance();
        try {
            if (sharedMaster.getSharedPref("oauth-login", false)) {
                String accessToken_key = sharedMaster.getSharedPref("accessToken", "");
                String accessTokenSecret_key = sharedMaster.getSharedPref("accessSecret", "");

                Log.d("twitter-chess", "accesstoken : " + accessToken_key + " accessSecret : " + accessTokenSecret_key);

                AccessTokenAsync tokenAsync = new AccessTokenAsync();
                AccessToken accessToken = tokenAsync.execute(accessToken_key, accessTokenSecret_key).get();


                twitter.setOAuthAccessToken(accessToken);
                twitterStream.setOAuthAccessToken(accessToken);
            } else {
                getLogin();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void getLogin() throws ExecutionException, InterruptedException {
        RequestTokenAsync tokenAsync = new RequestTokenAsync();
        token = tokenAsync.execute(twitter).get();
        Log.d("twitter-chess", "token url : " + token.getAuthorizationURL());
        Intent intent = new Intent(getApplicationContext(), OauthActivity.class);
        intent.putExtra("token url", token.getAuthorizationURL());
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                String pincode = data.getStringExtra("pin-code");
                Log.d("twitter-chess", "pin-code : " + data.getStringExtra("pin-code"));
                try {
                    AccessTokenAsync tokenAsync = new AccessTokenAsync();
                    AccessToken accessToken = tokenAsync.execute(new Object[]{twitter, token, pincode}).get();
                    Log.d("twitter-chess", "aceess token : " + accessToken.getToken());
                    sharedMaster.setSharedPref("accessToken", accessToken.getToken());
                    sharedMaster.setSharedPref("accessSecret", accessToken.getTokenSecret());
                    sharedMaster.setSharedPref("oauth-login", true);
                    twitter.setOAuthAccessToken(accessToken);
                    twitterStream.setOAuthAccessToken(accessToken);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ConfigurationBuilder getConfigurationBuilder() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(BuildConfig.CONSUMER_KEY);
        configurationBuilder.setOAuthConsumerSecret(BuildConfig.CONSUMER_SECRET);
        return configurationBuilder;
    }
}
