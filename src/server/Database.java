package server;
import java.io.*;
import java.util.*;

public class Database {
    private static final HashSet<String> categories = new HashSet<>(), admins = new HashSet<>();
    private static final HashMap<String, String> accounts = new HashMap<>();
    private static final HashMap<String, ArrayList<String[]>> recordHistories = new HashMap<>(), leaderboards = new HashMap<>();
    private static final ArrayList<String[]> submittedRecords = new ArrayList<>();
    private static boolean reviewingRecord = false, accessingApprovedRecords = false, accessingSubmittedRecords = false, accessingAccounts = false;

    public static void loadAccounts() throws InterruptedException {
        while (accessingAccounts)
            Thread.sleep(100);
        try {
            accessingAccounts = true;
            Scanner file = new Scanner(new File("src\\server\\Accounts.dat"));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (!line.equals(""))
                    accounts.put(line, file.nextLine());
            }
            accessingAccounts = false;
        } catch (FileNotFoundException e) { accessingAccounts = false; }
    }

    public static void loadSubmittedRecords() throws InterruptedException {
        while (accessingSubmittedRecords)
            Thread.sleep(100);
        try {
            accessingSubmittedRecords = true;
            Scanner file = new Scanner(new File("src\\server\\Submitted Records.txt"));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (!line.equals(""))
                    submittedRecords.add(new String[] {line, file.nextLine(), file.nextLine(), file.nextLine()});
            }
            accessingSubmittedRecords = false;
        } catch (FileNotFoundException e) { accessingSubmittedRecords = false; }
    }

    public static void loadApprovedRecords() throws InterruptedException {
        while (accessingApprovedRecords)
            Thread.sleep(100);
        try {
            accessingApprovedRecords = true;
            Scanner file = new Scanner(new File("src\\server\\Approved Records.txt"));
            while (file.hasNextLine()) {
                String username = file.nextLine();
                if (!username.equals("")) {
                    String category = file.nextLine(), description = file.nextLine(), proof = file.nextLine();
                    if (!recordHistories.containsKey(username))
                        recordHistories.put(username, new ArrayList<>());
                    recordHistories.get(username).add(new String[] { category, description, proof });

                    if (!leaderboards.containsKey(category))
                        leaderboards.put(category, new ArrayList<>());
                    leaderboards.get(category).add(new String[] { username, description, proof });
                }
            }
            accessingApprovedRecords = false;
        } catch (FileNotFoundException e) { accessingApprovedRecords = false; }
    }

    public static ArrayList<String[]> getRecordHistory(String username) {
        if (recordHistories.containsKey(username))
            return recordHistories.get(username);
        return new ArrayList<>();
    }

    public static ArrayList<String[]> getLeaderboard(String category) {
        if (leaderboards.containsKey(category))
            return leaderboards.get(category);
        return new ArrayList<>();
    }

    public static void loadCategories() throws FileNotFoundException {
        Scanner file = new Scanner(new File("src\\server\\Categories.txt"));
        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (!line.equals(""))
                categories.add(line);
        }
    }

    public static HashSet<String> getCategories() {
        return categories;
    }

    public static void loadAdmins() throws FileNotFoundException {
        Scanner file = new Scanner(new File("src\\server\\Admins.txt"));
        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (!line.equals(""))
                admins.add(line);
        }
    }

    public static boolean isAdmin(String username) {
        return admins.contains(username);
    }

    public static String register(String username, String hashedPassword) {
        if (accounts.containsKey(username))
            return "Username Already in Use";
        if (accessingAccounts)
            return "Error Registering, Try Again Later";
        try {
            accessingAccounts = true;
            File file = new File("src\\server\\Accounts.dat");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath(), true));
            writer.append(username);
            writer.append("\n");
            writer.append(hashedPassword);
            writer.append("\n\n");
            writer.close();
            accessingAccounts = false;

            accounts.put(username, hashedPassword);
            return "Registration Successful";
        } catch (Exception e) {
            accessingAccounts = false;
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
        if (accessingSubmittedRecords)
            return "Error Submitting Record, Try Again Later";
        try {
            accessingSubmittedRecords = true;
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
            accessingSubmittedRecords = false;

            submittedRecords.add(new String[] {username, category, description, proof});
            return "Record Submitted";
        } catch (Exception e) {
            accessingSubmittedRecords = false;
            return "Error Submitting Record, Try Again Later";
        }
    }

    public static String[] reviewRecord() {
        if (reviewingRecord || submittedRecords.size() == 0)
            return null;
        reviewingRecord = true;
        return submittedRecords.get(0);
    }

    public static void reviewRecord(String review, int score) {
        reviewingRecord = false;
        // TODO: Finish Method
    }
}