package com.cinnamon.moon.twitterchess.Ouath;

import android.os.AsyncTask;
import android.util.Log;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

/**
 * Created by moonp on 2018-03-19.
 */

public class RequestTokenAsync extends AsyncTask<Twitter, RequestToken, RequestToken> {

    private RequestToken token;

    @Override
    protected RequestToken doInBackground(Twitter... twitters) {
        try {
            Twitter twitter = twitters[0];
            token = twitter.getOAuthRequestToken();
            Log.d("twitter-chess","Request token : " + token.getToken());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return token;
    }
}
