package client;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter server ip: ");
        String ip = console.next();
        int port = 4444;
        Socket socket;
        try {
            socket = new Socket(ip, port);
        } catch(Exception e) {
            System.out.println("Connection failed");
            return;
        }

        //TODO: Code

        socket.close();
    }
}
