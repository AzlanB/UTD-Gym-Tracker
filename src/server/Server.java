package server;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        Scanner usernamesFile = new Scanner(new File("src\\server\\usernames.txt"));
        HashSet<String> usernames = new HashSet<>();
        while (usernamesFile.hasNextLine())
            usernames.add(usernamesFile.nextLine());
        Database.setUsernames(usernames);

        Scanner categoriesFile = new Scanner(new File("src\\server\\categories.txt"));
        HashSet<String> categories = new HashSet<>();
        while (categoriesFile.hasNextLine())
            categories.add(categoriesFile.nextLine());
        Database.setCategories(categories);

        ServerSocket server = new ServerSocket(4444);
        server.setReuseAddress(true);
        try {
            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (Exception ignored) {}
        server.close();
    }

    private record ClientHandler(Socket socket) implements Runnable {
        @Override
        public void run() {
            PrintWriter toClient = null;
            BufferedReader fromClient = null;
            try {
                toClient = new PrintWriter(socket.getOutputStream(), true);
                fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                boolean loggedIn = false;
                String username = null;

                String line;
                while ((line = fromClient.readLine()) != null) {
                    if (line.equals("login")) {
                        loggedIn = true;
                        username = fromClient.readLine();
                        String password = fromClient.readLine();
                        // TODO: Login Validation
                    }
                    else if (line.equals("getCategories")) {
                        HashSet<String> categories = Database.getCategories();
                        toClient.println(categories.size());
                        for (String category : categories)
                            toClient.println(category);
                    }
                    else if (line.equals("submitRecord")) {
                        if (loggedIn)
                            toClient.println(Database.submitRecord(username, fromClient.readLine(), fromClient.readLine(), fromClient.readLine()));
                        else
                            toClient.println("Please login");
                    }
                    // TODO: Register more inputs and send appropriate outputs to client
                }
            } catch (IOException e) { e.printStackTrace(); }
            if (toClient != null)
                toClient.close();
            if (fromClient != null) {
                try { fromClient.close(); } catch (IOException e) { e.printStackTrace(); }
            }
            try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }
}
