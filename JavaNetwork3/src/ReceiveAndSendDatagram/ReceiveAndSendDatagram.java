package ReceiveAndSendDatagram;

import java.io.*;
import java.net.*;

/**
 * Created by kasai2 on 2016/01/17.
 */
public class ReceiveAndSendDatagram {
    public static void main (String args[]) {

//        connectedSocket();
        clientSocket();

        messageReceive();

    }

    public static void clientSocket() {
        try {
            Socket socket = new Socket("", 12345);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int i = dataInputStream.readInt();
            System.out.println(i);
            dataInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void connectedSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeInt(123456789);
                dataOutputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void messageReceive() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(4321);
            byte buffer[] = new byte[1024];
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String str = new String(datagramPacket.getData());
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                System.out.println(str);
                System.out.println(inetAddress);
                System.out.println(port);
                messageSend(str, inetAddress, port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void messageSend(String str, InetAddress inetAddress, int port) {
        try {
//            InetAddress inetAddress = InetAddress.getByName("49.98.133.229");
            DatagramSocket datagramSocket = new DatagramSocket();
            byte buffer[] = str.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 43210);
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
