package workshop4.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static String port = "12345";
    private static String cookiePath = "C:\\Users\\Brendon\\JavaProjects\\workshop4\\src\\main\\java\\workshop4\\app\\cookie_file.txt";

    public static void main(String[] args) throws NumberFormatException, IOException {
        if(args.length > 0) {
            try {
                port = args[0];
                cookiePath = args[1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Parameter(s)");
            }
        }

        System.out.println("Waiting for connection...");
        ServerSocket server = new ServerSocket(Integer.parseInt(port));

        while(true) {
            Socket sock = server.accept();
            System.out.println("Connected!");
    
            ExecutorService threadPool = Executors.newFixedThreadPool(3);
            CookieClientHandler thr = new CookieClientHandler(sock, cookiePath);
            threadPool.submit(thr);
            System.out.println("Submitted to thread pool ");
        }
    }
}
