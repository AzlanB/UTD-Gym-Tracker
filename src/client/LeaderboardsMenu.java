package client;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;

public class LeaderboardsMenu {
    LeaderboardsMenu(PrintWriter toServer, BufferedReader fromServer) throws IOException {
        JFrame frame = new JFrame("Leaderboards");

        toServer.println("getCategories");
        String[] categories = new String[Integer.parseInt(fromServer.readLine())];
        for (int i = 0; i < categories.length; i++)
            categories[i] = fromServer.readLine();
        Arrays.sort(categories);

        JScrollPane pane = new JScrollPane();
        pane.setBounds(0, 20, 390, 225);

        JComboBox<String> categoryList = new JComboBox<>(categories);
        categoryList.setBounds(0, 0, 320, 20);
        JButton viewButton = new JButton("View");
        viewButton.setBounds(320, 0, 70, 20);
        viewButton.addActionListener(e -> {
            toServer.println("getLeaderboard");
            toServer.println(categoryList.getItemAt(categoryList.getSelectedIndex()));
            try {
                int size = Integer.parseInt(fromServer.readLine());
                String[][] leaderboard = new String[size][3];
                for (int i = 0; i < size; i++)
                    leaderboard[i] = new String[] { fromServer.readLine(), fromServer.readLine(), fromServer.readLine() };
                JTable table = new JTable(leaderboard, new String[] { "Username", "Description", "Proof" });
                pane.setViewportView(table);
            } catch (IOException ex) { ex.printStackTrace(); }
        });

        frame.add(categoryList);
        frame.add(viewButton);
        frame.add(pane);
        frame.setSize(400, 275);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
