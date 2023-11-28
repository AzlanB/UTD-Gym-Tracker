package server;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
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
                String line;
                while ((line = fromClient.readLine()) != null) {
                    // Temporary acknowledgement of input
                    System.out.println("From " + socket.getInetAddress().getHostAddress() + ": " + line);
                    // TODO: Register input and send appropriate output to client
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
