package client;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.*;

public class ReviewRecordMenu {
    ReviewRecordMenu(PrintWriter toServer, BufferedReader fromServer) throws IOException {
        JFrame frame = new JFrame("Review Record");
        toServer.println("reviewRecord");
        String status = fromServer.readLine();
        if (status.equals("Submitted Record:")) {
            JTextField username = new JTextField(fromServer.readLine()), category = new JTextField(fromServer.readLine()), description = new JTextField(fromServer.readLine()), proof = new JTextField(fromServer.readLine());
            JLabel usernamePrompt = new JLabel("Username"), categoryPrompt = new JLabel("Category"), descriptionPrompt = new JLabel("Description"), proofPrompt = new JLabel("Proof");
            usernamePrompt.setBounds(50, 10, 300, 20);
            username.setBounds(50, 30, 300, 20);
            username.setEditable(false);
            categoryPrompt.setBounds(50, 60, 300, 20);
            category.setBounds(50,80,300,20);
            category.setEditable(false);
            descriptionPrompt.setBounds(50, 110, 300, 20);
            description.setBounds(50,130,300,20);
            description.setEditable(false);
            proofPrompt.setBounds(50, 160, 300, 20);
            proof.setBounds(50, 180, 300, 20);
            proof.setEditable(false);

            JSpinner score = new JSpinner();
            score.setBounds(60, 210, 40, 20);
            JButton accept = new JButton("Accept"), deny = new JButton("Deny");
            accept.setBounds(100, 210, 90, 20);
            deny.setBounds(210, 210, 130, 20);
            deny.addActionListener(e -> {
                accept.setEnabled(false);
                deny.setEnabled(false);
                toServer.println("deny");
            });
            accept.addActionListener(e -> {
                accept.setEnabled(false);
                deny.setEnabled(false);
                toServer.println("accept");
                toServer.println(score.getValue());
            });

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    toServer.println("abort");
                    super.windowClosing(e);
                }
            });

            frame.add(usernamePrompt);
            frame.add(username);
            frame.add(categoryPrompt);
            frame.add(category);
            frame.add(descriptionPrompt);
            frame.add(description);
            frame.add(proofPrompt);
            frame.add(proof);
            frame.add(score);
            frame.add(accept);
            frame.add(deny);
            frame.setSize(400, 300);
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
