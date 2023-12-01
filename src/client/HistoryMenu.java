package client;
import java.io.*;
import javax.swing.*;

public class HistoryMenu {
    HistoryMenu(PrintWriter toServer, BufferedReader fromServer) throws IOException {
        JFrame frame = new JFrame("Record History");
        toServer.println("getRecordHistory");
        String status = fromServer.readLine();
        if (status.equals("Record History:")) {
            int size = Integer.parseInt(fromServer.readLine());
            String[][] history = new String[size][3];
            for (int i = 0; i < size; i++)
                history[i] = new String[]{ fromServer.readLine(), fromServer.readLine(), fromServer.readLine() };
            JTable table = new JTable(history, new String[] { "Category", "Description", "Proof" });

            JScrollPane pane = new JScrollPane();
            pane.setViewportView(table);
            pane.setBounds(0, 0, 390, 245);

            frame.add(pane);
            frame.setSize(400, 275);
        }
        else {
            JLabel error = new JLabel(status, SwingConstants.CENTER);
            error.setBounds(25, 10, 350, 20);
            frame.add(error);
            frame.setSize(400, 75);
        }
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
