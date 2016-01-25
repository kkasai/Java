package com.example.itcollege.sky;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Handler _handler;
    private Context _context;
    private static SkyWayRoundUp _skyWay;
    private SkyWayApplication _skyWayApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //context取得
        _context = getApplicationContext();
        //Application取得
//        _skyWayApp = (SkyWayApplication) getApplication();

        //SkyWayに接続
        _skyWay = new SkyWayRoundUp(_context);
        //
//        _skyWayApp.setSkyWay(_skyWay);
//        SkyWayApplication._skyWay = _skyWay;

        //リソース取得
        ListView peerListView = (ListView) findViewById(R.id.peer_list_view);

        //リソースにデータセット
        String peerList[] = _skyWay.getPeerList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, peerList);
        peerListView.setAdapter(arrayAdapter);

        //処理関係
        peerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                String peerId = (String) listView.getItemAtPosition(position);
//                _skyWay.connect(peerId);
//                TalkActivity talkActivity;
//                if(_skyWay.getIsConnected()) {
//                    talkActivity = new TalkActivity(_skyWay);
//                } else {
//                    talkActivity = new TalkActivity(_skyWay, peerId);
//                }
//                setContentView(R.layout.activity_talk);
//                talkActivity.run();
                Intent intent = new Intent(_context, TalkActivity.class);
                intent.putExtra("peerId", peerId);
                startActivity(intent);

            }
        });

    }

    public static SkyWayRoundUp getSkyWay() {
        return _skyWay;
    }

    public static void setSkyWay(SkyWayRoundUp skyWay) {
        _skyWay = skyWay;
    }

}
