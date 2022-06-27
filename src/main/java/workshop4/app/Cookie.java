package workshop4.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {
    private static File cookieFile;
    private static List<String> cookieTypes = new LinkedList<String>();

    public static String randomCookie(String cookiePath) throws IOException {
        cookieFile = new File(cookiePath);
        InputStream is = new FileInputStream(cookieFile);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String cookie;
        while((cookie = br.readLine()) != null) {
            cookieTypes.add(cookie);
        }

        br.close();
        isr.close();
        is.close();

        Random rand = new Random();
        int x = rand.nextInt(cookieTypes.size());
        cookie = (String) cookieTypes.get(x);
        
        return cookie;
    }
}
