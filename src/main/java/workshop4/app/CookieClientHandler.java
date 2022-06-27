package workshop4.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CookieClientHandler implements Runnable {
    private Socket sock;
    private String cookiePath;

    public CookieClientHandler(Socket sock, String cookiePath) {
        this.sock = sock;
        this.cookiePath = cookiePath;
    }

    @Override
    public void run() {
        
        try{
            InputStream is = sock.getInputStream();
            OutputStream os = sock.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            
            boolean loop = true;
            String clientInput;
            while(loop) {
                clientInput = dis.readUTF();
                if(clientInput.equals("get-cookie")) {
                    System.out.println("Got cookie command");
                    dos.writeUTF("cookie-text" + Cookie.randomCookie(cookiePath));
                    dos.flush();
                } else if (clientInput.equals("close")) {
                    dos.writeUTF("Socket closed");
                    loop = false;
                } else {
                    dos.writeUTF("Invalid input!");
                    dos.flush();
                }
            }
            dos.close();
            dis.close();
            os.flush();
            os.close();
            is.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}