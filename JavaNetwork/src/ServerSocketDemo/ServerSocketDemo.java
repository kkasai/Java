package ServerSocketDemo;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by kasai2 on 2016/01/09.
 */
public class ServerSocketDemo {
    public static void main (String args[]) {
        int port = 4321;
        Random random = new Random();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeInt(random.nextInt());
                dataOutputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
