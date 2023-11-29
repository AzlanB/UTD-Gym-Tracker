package client;
import java.io.*;
import javax.swing.*;

public class RegistrationMenu {
    RegistrationMenu(PrintWriter toServer, BufferedReader fromServer) throws IOException {
        JLabel usernamePrompt = new JLabel("Enter Username (UTD Email)"), passwordPrompt = new JLabel("Enter Password"), retypePrompt = new JLabel("Retype Password");
        JTextField username = new JTextField(), password = new JTextField(), retype = new JTextField();
        usernamePrompt.setBounds(50, 10, 300, 20);
        username.setBounds(50, 30, 300, 20);
        passwordPrompt.setBounds(50, 60, 300, 20);
        password.setBounds(50,80,300,20);
        retypePrompt.setBounds(50, 110, 300, 20);
        retype.setBounds(50,130,300,20);

        JLabel responseMessage = new JLabel("", SwingConstants.CENTER);
        JButton registerButton = new JButton("Register");
        responseMessage.setBounds(50,190,300,20);
        registerButton.setBounds(125,160,150,20);
        registerButton.addActionListener(e -> {
            /*
            String response;
            if (categoryList.getItemAt(categoryList.getSelectedIndex()).contains("\n"))
                response = "Invalid Category";
            else if (description.getText().contains("\n"))
                response = "Invalid Description";
            else if (proof.getText().contains("\n"))
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
            */
        });

        JFrame frame = new JFrame("Register");
        frame.add(usernamePrompt);
        frame.add(username);
        frame.add(passwordPrompt);
        frame.add(password);
        frame.add(retypePrompt);
        frame.add(retype);
        frame.add(responseMessage);
        frame.add(registerButton);
        frame.setSize(400, 275);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
