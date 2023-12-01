package client;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length < 1){
            System.out.println("Please provide ip address as an argument");
            return;
        }

        Socket socket;
        InetAddress serverConnect;
        try { serverConnect = InetAddress.getByName(args[0]); }
        catch (Exception e) {
            System.out.println("Server not found");
            return;
        }
        if (serverConnect.isReachable(5000)) {
            try { socket = new Socket(args[0], 4444); }
            catch (Exception e) {
                System.out.println("Connection to server failed");
                return;
            }
        }
        else {
            System.out.println("Server unresponsive or down");
            return;
        }

        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new LoginMenu(toServer, fromServer);
    }
}
