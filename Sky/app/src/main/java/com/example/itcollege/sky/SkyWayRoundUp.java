package com.example.itcollege.sky;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;
import io.skyway.Peer.*;


public class SkyWayRoundUp {
    private final static String API_KEY = "e3585a60-9f61-4bc7-a146-7da471cf1d14";
    private final static String DOMAIN = "localhost";
    private Peer _peer;
    private DataConnection _dataConnection;
    private Context _context;
    private Boolean _isConnected = false;
    private String str = "aaaa";

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
////        dest.writeValue(_peer);
//        dest.writeValue(_context);
//    }
//
//    protected SkyWayRoundUp(Parcel in) {
////        _peer = (Peer) in.readValue(Peer.class.getClassLoader());
////        _context = (Context) in.readValue(Context.class.getClassLoader());
//    }
//
//    public static final Creator<SkyWayRoundUp> CREATOR = new Creator<SkyWayRoundUp>() {
//        @Override
//        public SkyWayRoundUp createFromParcel(Parcel in) {
//            return new SkyWayRoundUp(in);
//        }
//
//        @Override
//        public SkyWayRoundUp[] newArray(int size) {
//            return new SkyWayRoundUp[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }

    SkyWayRoundUp(Context context) {
        PeerOption options = new PeerOption();
        options.key = API_KEY;
        options.domain = DOMAIN;

        _context = context;
        _peer = new Peer(_context, options);
    }


    //接続されているpeerIdを取得する。
    public String[] getPeerList() {
//        _peer.listAllPeers(new OnCallback() {
//            @Override
//            public void onCallback(Object object) {
//                JSONArray jsonArray = (JSONArray) object;
//                return jsonArray;
//            }
//        });
        String peerList[] = {"aaaaaaaaa", "bbbbbbbbbb", "ccccccccc"};
        return peerList;
    }

    //指定したpeerに接続する。
    public void connect(final String peerId) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                ConnectOption options = new ConnectOption();
                options.serialization = DataConnection.SerializationEnum.BINARY;
                _dataConnection = _peer.connect(peerId, options);
            }
        });
    }

    public Boolean sendText(String text) {
        Boolean bResult = _dataConnection.send(text);

        return bResult;
    }

    //接続受信した時のみ(接続したあとに呼ぶ)
    public void setDataConnectionWhenConnected() {
        _peer.on(Peer.PeerEventEnum.CONNECTION, new OnCallback() {
            @Override
            public void onCallback(Object object) {
                _dataConnection = (DataConnection) object;
                _isConnected = true;
            }
        });
    }

    public void setTextViewWhenReceivedData() {
        _dataConnection.on(DataConnection.DataEventEnum.DATA, new OnCallback() {
            @Override
            public void onCallback(Object object) {
                final String text = (String) object;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = new TextView(_context);
                        textView.setText(text);
                        textView.setTextColor(Color.BLACK);
                    }
                });
            }
        });
    }

    public DataConnection getDataConnection() {
        return _dataConnection;
    }

    public void setDataConnection(DataConnection dataConnection) {
        this._dataConnection = dataConnection;
    }

    public Boolean getIsConnected() {
        return _isConnected;
    }

    public void setIsConnected(Boolean isConnected) {
        this._isConnected = isConnected;
    }

}
