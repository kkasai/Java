package SocketDemo;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by kasai2 on 2016/01/09.
 */
public class SocketDemo {
    public static void main(String args[]) {
        for(int j = 0; j < 3; j++) {
            String server = "127.0.0.1";
            int port = 4321;
            try {
                Socket socket = new Socket(server, port);
                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                int i = dataInputStream.readInt();
                System.out.println(i);
                dataInputStream.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

}
