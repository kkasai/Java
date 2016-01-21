package com.example.itcollege.androidsample2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    public void skyWaySample(Peer peer) {

    }
}
