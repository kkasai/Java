package com.example.itcollege.sky;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TalkActivity extends FragmentActivity{
    private Handler _handler;
    private SkyWayRoundUp _skyWay;
    private String _peerId;

//    public TalkActivity(SkyWayRoundUp skyWay) {
//        _skyWay = skyWay;
//        _skyWay.setDataConnectionWhenConnected();
//    }
//
//    public TalkActivity(SkyWayRoundUp skyway, String peerId) {
//        _skyWay = skyway;
//        _skyWay.connect(peerId);
//    }

    public void run() {
        _skyWay.setTextViewWhenReceivedData();

        Button submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                TextView textView = new TextView(TalkActivity.this);
                String text = editText.getText().toString();
                Boolean result = _skyWay.sendText(text);
                textView.setText(text);
                textView.setTextColor(Color.BLACK);
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.content);
                linearLayout.addView(textView);
            }
        });

        System.out.println(_peerId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        Intent intent = getIntent();
        _peerId = intent.getStringExtra("peerId");
        _skyWay = MainActivity.getSkyWay();
    }
}
