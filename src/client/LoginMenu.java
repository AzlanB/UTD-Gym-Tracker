package client;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginMenu {
    LoginMenu(PrintWriter toServer, BufferedReader fromServer) {
        JLabel usernamePrompt = new JLabel("Enter Username (UTD Email)"), passwordPrompt = new JLabel("Enter Password");
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        usernamePrompt.setBounds(50, 10, 300, 20);
        username.setBounds(50,30,300,20);
        passwordPrompt.setBounds(50, 60, 300, 20);
        password.setBounds(50,80,300,20);

        JLabel responseMessage = new JLabel("", SwingConstants.CENTER);
        JButton registerButton = new JButton("Register"), loginButton = new JButton("Log In");
        responseMessage.setBounds(50,140,300,20);
        registerButton.setBounds(60, 110, 130, 20);
        loginButton.setBounds(210, 110, 130, 20);
        registerButton.addActionListener(e -> new RegistrationMenu(toServer, fromServer));
        loginButton.addActionListener(e -> {
            String response;
            if (username.getText().contains("\n") || username.getText().length() < 1)
                response = "Invalid Username";
            else if (String.valueOf(password.getPassword()).contains("\n") || String.valueOf(password.getPassword()).length() < 1)
                response = "Invalid Password";
            else {
                try {
                    String hashedPassword = username.getText() + String.valueOf(password.getPassword());
                    MessageDigest mD = MessageDigest.getInstance("SHA-256");
                    mD.update(hashedPassword.getBytes());
                    hashedPassword = new String(mD.digest());
                    if (hashedPassword.contains("\n"))
                        response = "Invalid Password";
                    else {
                        toServer.println("login");
                        toServer.println(username.getText());
                        toServer.println(hashedPassword);
                        try { response = fromServer.readLine(); } catch (IOException ex) { response = "Error Getting Response From Server"; }
                    }
                } catch (NoSuchAlgorithmException ex) { response = "Error Hashing Password, Try Again Later"; }
            }
            responseMessage.setText(response);
            if (response.equals("Login Successful")) {
                responseMessage.setForeground(Color.GREEN);
                try { new HomeMenu(toServer, fromServer); } catch (IOException ex) { throw new RuntimeException(ex); }
            }
            else
                responseMessage.setForeground(Color.RED);
        });

        JFrame frame = new JFrame("Login");
        frame.add(usernamePrompt);
        frame.add(username);
        frame.add(passwordPrompt);
        frame.add(password);
        frame.add(responseMessage);
        frame.add(registerButton);
        frame.add(loginButton);
        frame.setSize(400, 225);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
