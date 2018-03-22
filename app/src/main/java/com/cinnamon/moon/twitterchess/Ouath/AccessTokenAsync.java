package com.cinnamon.moon.twitterchess.Ouath;

import android.os.AsyncTask;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by moonp on 2018-03-20.
 */

public class AccessTokenAsync extends AsyncTask<Object, AccessToken, AccessToken> {

    private AccessToken accessToken;

    @Override
    protected AccessToken doInBackground(Object... objects) {
        try {
            if (objects.length == 3) {
                Twitter twitter = (Twitter) objects[0];
                RequestToken requestToken = (RequestToken) objects[1];
                String pincode = (String) objects[2];
                accessToken = twitter.getOAuthAccessToken(requestToken, pincode);
            }else if (objects.length == 2){
                accessToken = new AccessToken(objects[0].toString(), objects[1].toString());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return accessToken;
    }
}
