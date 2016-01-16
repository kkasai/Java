package com.example.itcollege.androidsample;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by kasai2 on 2016/01/16.
 */
public class ReceiveTextAsyncTaskLoaderHelper extends AsyncTaskLoader<String> {
    Context context = null;

    public ReceiveTextAsyncTaskLoaderHelper(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    public String loadInBackground() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(43210);
            byte buffer[] = new byte[1024];
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String str = new String(datagramPacket.getData());
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test";
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
