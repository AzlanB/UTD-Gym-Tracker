package client;
import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.security.MessageDigest;

public class Client {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
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

        // Temporary usage, can be changed
        Scanner console = new Scanner(System.in);
        String line;
        while (!(line = console.nextLine()).equals("exit")){
            if (line.equals("register")) {
                new RegistrationMenu(toServer, fromServer);
            }
            else if (line.equals("login")) {
                System.out.print("Enter Username: ");
                String username = console.nextLine();
                System.out.print("Enter Password: ");

                String hashedPassword = username + console.nextLine();
                MessageDigest mD = MessageDigest.getInstance("SHA-256");
                mD.update(hashedPassword.getBytes());
                hashedPassword = new String(mD.digest());

                toServer.println("login");
                toServer.println(username);
                toServer.println(hashedPassword);
            }
            else if (line.equals("submitRecord")) {
                new SubmitRecordMenu(toServer, fromServer);
            }
            // TODO: More Code
        }

        socket.close();
    }
}
