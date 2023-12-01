package client;
import java.io.*;
import javax.swing.*;

public class HomeMenu {
    HomeMenu(PrintWriter toServer, BufferedReader fromServer) throws IOException {
        JButton leaderboards = new JButton("View Leaderboards"), history = new JButton("View Record History"), submit = new JButton("Submit Record Attempt");
        leaderboards.setBounds(40, 10, 180, 20);
        history.setBounds(40, 40, 180, 20);
        submit.setBounds(40, 70, 180, 20);
        leaderboards.addActionListener(e -> new LeaderboardsMenu(toServer, fromServer));
        history.addActionListener(e -> new HistoryMenu(toServer, fromServer));
        submit.addActionListener(e -> { try { new SubmitRecordMenu(toServer, fromServer); } catch (IOException ex) { throw new RuntimeException(ex); } });

        JFrame frame = new JFrame("UTD Gym Tracker");
        toServer.println("isAdmin");
        if (fromServer.readLine().equals("true")) {
            JButton review = new JButton("Review a Record");
            review.setBounds(40, 100, 180, 20);
            review.addActionListener(e -> { try { new ReviewRecordMenu(toServer, fromServer); } catch (IOException ex) { throw new RuntimeException(ex); }});
            frame.add(review);
            frame.setSize(270, 160);
        }
        else
            frame.setSize(270, 130);

        frame.add(leaderboards);
        frame.add(history);
        frame.add(submit);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
