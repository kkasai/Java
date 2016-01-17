package DatagramSender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by kasai2 on 2016/01/09.
 */
public class DatagramSender {
    public static void main(String args[]) {
        int port = 43210;

        try {
            InetAddress inetAddress = InetAddress.getByName("49.98.133.229");
//            InetAddress inetAddress = InetAddress.getLoopbackAddress();
            DatagramSocket datagramSocket = new DatagramSocket();
            byte buffer[] = "bbbaaaaaaaaaaaaaa".getBytes();
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
