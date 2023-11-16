package recordmgr;

import java.util.HashSet;

public class RecordMngr {
    private HashSet<String> usernames, categories;
    private HashSet<Form> submittedRecords;

    public RecordMngr(){
        this(new HashSet<>(), new HashSet<>());
    }

    public RecordMngr(HashSet<String> usernames, HashSet<String> categories){
        this.usernames = usernames;
        this.categories = categories;
        submittedRecords = new HashSet<>();
    }

    public boolean addUsername(String username){
        return usernames.add(username);
    }

    public boolean removeUsername(String username){
        return usernames.remove(username);
    }

    public boolean addCategory(String category){
        return categories.add(category);
    }

    public boolean removeCategory(String category){
        return categories.remove(category);
    }

    public HashSet<String> getCategories(){
        return categories;
    }

    public String submit(Form form){
        if (!usernames.contains(form.username) || form.username.contains("\n"))
            return "Error Validating Username";
        if (!categories.contains(form.category) || form.category.contains("\n"))
            return "Invalid Category";
        if (form.description.length() < 5 || form.description.length() > 1000 || form.description.contains("\n"))
            return "Invalid Description";
        if (!(form.proof.contains("youtube.com") || form.proof.contains("youtu.be") || form.proof.contains("drive.google.com")) || form.proof.contains("\n"))
            return "Invalid Proof";

        if (DatabaseMngr.submitRecord(form)) {
            submittedRecords.add(form);
            return "Record Submitted";
        }
        return "Error Submitting Record, Try Again Later";
    }
}