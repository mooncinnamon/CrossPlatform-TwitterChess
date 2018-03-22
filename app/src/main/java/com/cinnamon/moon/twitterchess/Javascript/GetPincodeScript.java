package com.cinnamon.moon.twitterchess.Javascript;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.EditText;

import com.cinnamon.moon.twitterchess.OauthActivity;

/**
 * Created by moonp on 2018-03-20.
 */

public class GetPincodeScript {
    private Handler handler;
    private EditText editText;

    public GetPincodeScript(Handler handler, EditText editText){
        this.handler = handler;
        this.editText = editText;
    }

    @JavascriptInterface
    public void getPinCode(final String pin) {
        if (pin.length() > 0) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String pincode = pin.split("<code>")[1].substring(0,7);
                    Log.d("twitter-chess", "pin : "+ pincode);
                    editText.setText(pincode);
                }
            });
        } else {
            Log.d("twitter-chess", "get pin failed...");
        }
    }
}
