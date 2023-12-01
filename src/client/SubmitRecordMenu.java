package client;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SubmitRecordMenu {
    SubmitRecordMenu(PrintWriter toServer, BufferedReader fromServer) throws IOException {
        toServer.println("getCategories");
        String[] categories = new String[Integer.parseInt(fromServer.readLine())];
        for (int i = 0; i < categories.length; i++)
            categories[i] = fromServer.readLine();
        Arrays.sort(categories);

        JLabel categoryPrompt = new JLabel("Select Category");
        JComboBox<String> categoryList = new JComboBox<>(categories);
        categoryPrompt.setBounds(50, 10, 300, 20);
        categoryList.setBounds(50, 30, 300, 20);

        JLabel descriptionPrompt = new JLabel("Enter Brief Description"), proofPrompt = new JLabel("Enter Proof (Youtube/Google Drive Link)");
        JTextField description = new JTextField(), proof = new JTextField();
        descriptionPrompt.setBounds(50, 60, 300, 20);
        description.setBounds(50,80,300,20);
        proofPrompt.setBounds(50, 110, 300, 20);
        proof.setBounds(50,130,300,20);

        JLabel responseMessage = new JLabel("", SwingConstants.CENTER);
        JButton submitButton = new JButton("Submit");
        responseMessage.setBounds(50,190,300,20);
        submitButton.setBounds(125,160,150,20);
        submitButton.addActionListener(e -> {
            String response;
            if (categoryList.getItemAt(categoryList.getSelectedIndex()).contains("\n"))
                response = "Invalid Category";
            else if (description.getText().contains("\n") || description.getText().length() < 1)
                response = "Invalid Description";
            else if (proof.getText().contains("\n") || proof.getText().length() < 1)
                response = "Invalid Proof";
            else {
                toServer.println("submitRecord");
                toServer.println(categoryList.getItemAt(categoryList.getSelectedIndex()));
                toServer.println(description.getText());
                toServer.println(proof.getText());
                try { response = fromServer.readLine(); } catch (IOException ex) { response = "Error Getting Response From Server"; }
            }
            responseMessage.setText(response);
            if (response.equals("Record Submitted")) {
                responseMessage.setForeground(Color.GREEN);
                categoryList.setEnabled(false);
                description.setEnabled(false);
                proof.setEnabled(false);
                submitButton.setEnabled(false);
            }
            else
                responseMessage.setForeground(Color.RED);
        });

        JFrame frame = new JFrame("Submit Record Attempt");
        frame.add(categoryPrompt);
        frame.add(categoryList);
        frame.add(descriptionPrompt);
        frame.add(description);
        frame.add(proofPrompt);
        frame.add(proof);
        frame.add(submitButton);
        frame.add(responseMessage);
        frame.setSize(400, 275);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}