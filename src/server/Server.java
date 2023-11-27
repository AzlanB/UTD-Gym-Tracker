package server;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(4444);
        Socket socket = server.accept();

        // TODO: Code

        socket.close();
        server.close();
    }
}
