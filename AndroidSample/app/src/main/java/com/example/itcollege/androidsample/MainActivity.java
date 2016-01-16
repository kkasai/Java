package com.example.itcollege.androidsample;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.View;
import android.widget.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<String> {
    int i = 0;
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                TextView textView = new TextView(getApplicationContext());
                text = editText.getText().toString();
//                String text = editText.getText().toString();
                textView.setText(text);
                textView.setTextColor(Color.BLACK);
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.content);
                linearLayout.addView(textView);
                i++;
                System.out.println(i);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InetAddress inetAddress = InetAddress.getByName("192.168.137.1");
                            DatagramSocket datagramSocket = new DatagramSocket();
                            byte buffer[] = text.getBytes();
                            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 4321);
                            datagramSocket.send(datagramPacket);
//                            Toast.makeText(getApplicationContext(), "送信しました。", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

//                startAsyncLoadText(text);
            }
        });

        // AsyncTaskにするべきかも
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket(43210);
                    byte buffer[] = new byte[1024];
                    while (true) {
                        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                        datagramSocket.receive(datagramPacket);
                        String str = new String(datagramPacket.getData(), "UTF-8");
                        TextView textView = new TextView(getApplicationContext());
                        textView.setText(str);
                        textView.setTextColor(Color.BLACK);
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.content);
                        linearLayout.addView(textView);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        serverStartAsyncLoadText();
    }

    public void serverStartAsyncLoadText() {
        Bundle args = new Bundle();
        args.putString("text", "server");
        getSupportLoaderManager().initLoader(0, args, MainActivity.this);
    }
    public void startAsyncLoadText(String text) {
        Bundle args = new Bundle();
        args.putString("text", text);
        getSupportLoaderManager().initLoader(0, args, MainActivity.this);
    }

    @Override
    public android.support.v4.content.Loader<String> onCreateLoader(int id, Bundle args) {
        String text = args.getString("text");
        switch (text) {
            case "text" :
                return new SendTextAsyncTaskLoaderHelper(getApplicationContext(), text);
            case "server" :
                return new ReceiveTextAsyncTaskLoaderHelper(getApplicationContext());
        }
        return null;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {

    }

}
