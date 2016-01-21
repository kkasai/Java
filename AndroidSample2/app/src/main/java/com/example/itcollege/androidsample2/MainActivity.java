package com.example.itcollege.androidsample2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.skyway.Peer.DataConnection;
import io.skyway.Peer.OnCallback;
import io.skyway.Peer.Peer;
import io.skyway.Peer.PeerOption;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////////////////////
        PeerOption options = new PeerOption();

        options.key = "e3585a60-9f61-4bc7-a146-7da471cf1d14";
        options.domain = "localhost";

//        final Peer peer = new Peer(getApplicationContext(), options);
        Peer peer = new Peer(getApplicationContext(), "Android2", options);
        ////////////////////////////////

        skyWaySample(peer);
    }

    public void skyWaySample(Peer orgPeer) {
        final Peer peer = orgPeer;
        peer.on(Peer.PeerEventEnum.OPEN, new OnCallback() {
            @Override
            public void onCallback(Object o) {
                System.out.println("aaaaa");
                DataConnection dataConnection = peer.connect("Android1");
                System.out.println("bbbbb");
                dataConnection.send("Hello SkyWay!");
            }
        });
//        dataConnection.on(DataConnection.DataEventEnum.OPEN, new OnCallback() {
//            @Override
//            public void onCallback(Object object) {
//                //
//            }
//        });
//        dataConnection.on(DataConnection.DataEventEnum.DATA, new OnCallback() {
//            @Override
//            public void onCallback(Object object) {
//                //
//            }
//        });
//        dataConnection.send("Hello SkyWay!");

    }
}
