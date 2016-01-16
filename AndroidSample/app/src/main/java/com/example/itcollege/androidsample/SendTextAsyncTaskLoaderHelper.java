package com.example.itcollege.androidsample;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by kasai2 on 2016/01/16.
 */
public class SendTextAsyncTaskLoaderHelper extends AsyncTaskLoader<String> {
    String text = "";
    Context context = null;

    public SendTextAsyncTaskLoaderHelper(Context context, String text) {
        super(context);

        this.text = text;
        this.context = context;
    }

    @Override
    public String loadInBackground() {
        DatagramSender datagramSender = new DatagramSender("", 4321);
        datagramSender.send(text);
        return "test";
    }
}
