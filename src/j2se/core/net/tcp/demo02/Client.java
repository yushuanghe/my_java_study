package j2se.core.net.tcp.demo02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by yobdc on 2016/12/28.
 */
public class Client {
    public static void main(String[] args) {
        new Client().startUp();
    }

    private void startUp() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 6666);

            Thread thread = new Thread(new ClientThread(socket));
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientThread implements Runnable {

    private Socket socket;

    private BufferedReader in = null;
    private PrintWriter out = null;
    private BufferedReader sbr = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            sbr = new BufferedReader(new InputStreamReader(System.in));

            final BufferedReader br = in;

//            boolean isRun = true;
//            while (isRun) {
//                String line = null;
//                String result = null;
//
//                line = sbr.readLine();
//                if ("exit".equals(line)) {
//                    isRun = false;
//                }
//                out.println(line);
//
//                result = in.readLine();
//                System.out.println(result);
//            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = null;
                    try {
                        while ((result = br.readLine()) != null) {
                            System.out.println(result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            String line = null;

            while ((line = sbr.readLine()) != null) {
                out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (sbr != null)
                    sbr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
