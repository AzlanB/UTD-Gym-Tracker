package recordmgr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class SubmitRecordMenu {
    SubmitRecordMenu(RecordMngr recordMngr, String username){
        HashSet<String> categories = recordMngr.getCategories();
        String[] categoriesArray = new String[categories.size()];
        Iterator<String> s = categories.iterator();
        int i = 0;
        while (s.hasNext())
            categoriesArray[i++] = s.next();
        Arrays.sort(categoriesArray);

        JLabel categoryPrompt = new JLabel("Select Category");
        JComboBox<String> categoryList = new JComboBox<>(categoriesArray);
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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Form form = new Form(username, categoryList.getItemAt(categoryList.getSelectedIndex()), description.getText(), proof.getText());
                String response = recordMngr.submit(form);
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
            }
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