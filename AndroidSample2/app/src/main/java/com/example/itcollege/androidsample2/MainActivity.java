package com.example.itcollege.androidsample2;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import io.skyway.Peer.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String id = "";
    Peer _peer;
    Handler _handler;
    DataConnection _data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////////////////////
        _handler = new Handler(Looper.getMainLooper());

        PeerOption options = new PeerOption();

        options.key = "e3585a60-9f61-4bc7-a146-7da471cf1d14";
        options.domain = "localhost";

//        final Peer peer = new Peer(getApplicationContext(), options);
        _peer = new Peer(getApplicationContext(), "Android2", options);
        ////////////////////////////////

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("aaaa");
                boolean bResult = _data.send("return str");
                System.out.println(bResult);
            }
        });

//        skyWaySample();
        reserveString();
    }

    private void reserveString() {
        _peer.on(Peer.PeerEventEnum.CONNECTION, new OnCallback() {
            @Override
            public void onCallback(Object object) {
                if (!(object instanceof DataConnection)) {
                    return;
                }
                _data = (DataConnection) object;
                _data.on(DataConnection.DataEventEnum.OPEN, new OnCallback() {
                    @Override
                    public void onCallback(Object object) {
                        //
                    }
                });
                _data.on(DataConnection.DataEventEnum.DATA, new OnCallback() {
                    @Override
                    public void onCallback(Object object) {
//                        String strValue = null;
                        System.out.println(object);
                        if (object instanceof String){
                            final String strValue = (String)object;
                            System.out.println(strValue);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = new TextView(getApplicationContext());
                                    textView.setText(strValue);
                                    textView.setTextColor(Color.BLACK);
                                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative);
                                    relativeLayout.addView(textView);
                                }
                            });
                        }
                    }
                });
                //送信？
//                data.send("Hello SkyWay!");

            }
        });

    }

    public void getlist() {
        _peer.listAllPeers(new OnCallback() {
            @Override
            public void onCallback(Object object) {
                JSONArray jsonArray = (JSONArray) object;
                try {
                    id = jsonArray.getString(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void skyWaySample() {
        getlist();
        _peer.on(Peer.PeerEventEnum.OPEN, new OnCallback() {
            @Override
            public void onCallback(Object o) {
                if(!(id.length() > 0)) {
                    return;
                }
                System.out.println("aaaaa");
                System.out.println(id);
                _handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ConnectOption option = new ConnectOption();
                        option.serialization = DataConnection.SerializationEnum.BINARY;
                        DataConnection dataConnection = _peer.connect(id, option);
                        System.out.println("bbbbb");
                        dataConnection.send("Hello SkyWay!");
                    }
                });
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
