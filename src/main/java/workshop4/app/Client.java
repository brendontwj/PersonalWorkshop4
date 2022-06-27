package workshop4.app;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static Console cons = System.console();
    static String port = "12345";
    static Socket socket;
    public static void main(String[] args) throws IOException, UnknownHostException {

        if(args.length > 0) {
            try {
                socket = new Socket("localhost", Integer.parseInt(args[0]));
            } catch (IndexOutOfBoundsException e) {

            }
        } else {
            socket = new Socket("localhost", Integer.parseInt(port));
        }

        System.out.println("Connected!");

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);

        String input = null;
        boolean stop = false;
        String cookie;
        String serverResponse;
        while(!stop) {
            input = cons.readLine("Please input command: ");
            dos.writeUTF(input);
            dos.flush();
            serverResponse = dis.readUTF();
            if(serverResponse.contains("cookie-text")) {
                cookie = serverResponse.substring(11, serverResponse.length());
                System.out.println("Type of cookie given by server is " + cookie);
            } else if (serverResponse.equals("Socket closed")) {
                System.out.println("Ended connection with server");
                stop = true;
            } else {
                System.out.println(serverResponse);
            }
        }
        dos.close();
        dis.close();
        is.close();
        os.close();
    }
}
