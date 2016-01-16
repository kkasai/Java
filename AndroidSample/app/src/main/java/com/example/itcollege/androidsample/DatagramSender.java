package com.example.itcollege.androidsample;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by kasai2 on 2016/01/16.
 */
public class DatagramSender {
    String stringIPAddress = "";
    int port = 4321;

    public DatagramSender(String stringIPAddress, int port) {
        this.stringIPAddress = stringIPAddress;
        this.port = port;
    }

    public void send(String text) {
        try {
            InetAddress inetAddress = InetAddress.getByName(stringIPAddress);
            DatagramSocket datagramSocket = new DatagramSocket();
            byte buffer[] = text.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            for (int i = 0; i < 3; i++) {
                datagramSocket.send(datagramPacket);
            }
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
