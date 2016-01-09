package DatagramReceiver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by kasai2 on 2016/01/09.
 */
public class DatagramReceiver {
    private final static int BUFSIZE = 1024;
    public static void main(String args[]) {
        int port = 4321;
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            byte buffer[] = new byte[BUFSIZE];
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String str = new String(datagramPacket.getData());
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
