package client;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationMenu {
    RegistrationMenu(PrintWriter toServer, BufferedReader fromServer) {
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
        responseMessage.setBounds(0,190,400,20);
        registerButton.setBounds(125,160,150,20);
        registerButton.addActionListener(e -> {
            String response;
            String pwd = password.getText();
            responseMessage.setBounds(0,190,400,20);
            if (username.getText().contains("\n") || username.getText().length() < 1)
                response = "Invalid Username";
            else if (!username.getText().endsWith("@utdallas.edu"))
                response = "Username must be a UTD email";
            else if (pwd.contains("\n"))
                response = "Invalid Password";
            else if (pwd.length() < 8)
                response = "Password must be at least 8 characters";
            else if (pwd.length() > 32)
                response = "Password must be at most 32 characters";
            else if (!(pwd.matches(".*[0-9].*") && pwd.matches(".*[a-z].*") && pwd.matches(".*[A-Z].*"))) {
                response = "Password missing: ";
                if (!pwd.matches(".*[0-9].*"))
                    response += "Number, ";
                if (!pwd.matches(".*[a-z].*"))
                    response += "Lowercase Letter, ";
                if (!pwd.matches(".*[A-Z].*"))
                    response += "Uppercase Letter, ";
                response = response.substring(0, response.length() - 2);
                if (response.length() == 60)
                    responseMessage.setBounds(-5,190,400,20);
            }
            else if (!pwd.equals(retype.getText()))
                response = "Retyped password must match password";
            else {
                try {
                    String hashedPassword = username.getText() + pwd;
                    MessageDigest mD = MessageDigest.getInstance("SHA-256");
                    mD.update(hashedPassword.getBytes());
                    hashedPassword = new String(mD.digest());
                    if (hashedPassword.contains("\n"))
                        response = "Invalid Password";
                    else {
                        toServer.println("register");
                        toServer.println(username.getText());
                        toServer.println(hashedPassword);
                        try { response = fromServer.readLine(); } catch (IOException ex) { response = "Error Getting Response From Server"; }
                    }
                } catch (NoSuchAlgorithmException ex) { response = "Error Hashing Password, Try Again Later"; }
            }
            responseMessage.setText(response);
            if (response.equals("Registration Successful")) {
                responseMessage.setForeground(Color.GREEN);
                username.setEditable(false);
                password.setEditable(false);
                retype.setEditable(false);
                registerButton.setEnabled(false);
            }
            else
                responseMessage.setForeground(Color.RED);
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
