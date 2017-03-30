package j2se.core.net.tcp.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        while (true) {
            Socket socket = null;
            socket = server.accept();
            Thread t = new Thread(new ServerRunner(socket));
            t.start();
        }
    }

}

class ServerRunner implements Runnable {

    private Socket socket;

    public ServerRunner(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket
                    .getOutputStream()));
            while (true) {
                // 接收客户机传递过来的指令
                String command = input.readUTF();
                // 处理指令
                String[] array = command.split(":");
                StringBuilder builder = new StringBuilder();
                if ("GET".equals(array[0])) {
                    // 加载文件的处理
                    File file = new File(array[1]);
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                        builder.append("\n");
                    }
                } else if ("TIME".equals(array[0])) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    builder.append(df.format(new Date()));
                    builder.append("\n");
                } else if ("exit".equals(array[0])) {
                    break;
                } else {
                    builder.append("未知指令");
                    builder.append("\n");
                }
                builder.append("EOF\n");
                // 回应客户处理结果
                output.write(builder.toString());
                output.flush();
            }
            System.out.println("game over!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

