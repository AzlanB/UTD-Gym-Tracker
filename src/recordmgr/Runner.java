package recordmgr;

import java.util.HashSet;

public class Runner {
    public static void main(String[] args){
        HashSet<String> usernames = new HashSet<>();
        usernames.add("axb210107");
        HashSet<String> categories = new HashSet<>();
        categories.add("Deadlift");
        categories.add("Bench Press");
        categories.add("Squat");

        RecordMngr recordMngr = new RecordMngr(usernames, categories);
        new SubmitRecordMenu(recordMngr, "axb210107");
    }
}
