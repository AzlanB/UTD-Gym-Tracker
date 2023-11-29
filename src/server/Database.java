package server;
import java.io.*;
import java.util.*;

public class Database {
    private static final HashSet<String> categories = new HashSet<>();
    private static final HashMap<String, String> accounts = new HashMap<>();

    public static void loadAccounts() {
        try {
            Scanner file = new Scanner(new File("src\\server\\Accounts.dat"));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (!line.equals("")) {
                    accounts.put(line, file.nextLine());
                }
            }
        } catch (FileNotFoundException ignored) {}
    }

    public static void loadCategories() throws FileNotFoundException {
        Scanner file = new Scanner(new File("src\\server\\Categories.txt"));
        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (!line.equals(""))
                categories.add(file.nextLine());
        }
    }

    public static HashSet<String> getCategories() {
        return categories;
    }

    public static String register(String username, String hashedPassword) {
        if (accounts.containsKey(username))
            return "Username Already in Use";
        try {
            File file = new File("src\\server\\Accounts.dat");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath(), true));
            writer.append(username);
            writer.append("\n");
            writer.append(hashedPassword);
            writer.append("\n\n");
            writer.close();

            accounts.put(username, hashedPassword);
            return "Registration Successful";
        } catch (Exception e) {
            return "Error Registering, Try Again Later";
        }
    }

    public static String login(String username, String hashedPassword) {
        if (!accounts.containsKey(username))
            return "Username Not Found";
        if (accounts.get(username).equals(hashedPassword))
            return "Login Successful";
        return "Incorrect Password";
    }

    public static String submitRecord(String username, String category, String description, String proof) {
        if (!accounts.containsKey(username))
            return "Error Validating Username";
        if (!categories.contains(category))
            return "Invalid Category";
        if (description.length() < 5 || description.length() > 1000)
            return "Invalid Description";
        if (!(proof.contains("youtube.com") || proof.contains("youtu.be") || proof.contains("drive.google.com")))
            return "Invalid Proof";

        try {
            File file = new File("src\\server\\Submitted Records.txt");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath(), true));
            writer.append(username);
            writer.append("\n");
            writer.append(category);
            writer.append("\n");
            writer.append(description);
            writer.append("\n");
            writer.append(proof);
            writer.append("\n\n");
            writer.close();
            return "Record Submitted";
        } catch (Exception e) {
            return "Error Submitting Record, Try Again Later";
        }
    }
}