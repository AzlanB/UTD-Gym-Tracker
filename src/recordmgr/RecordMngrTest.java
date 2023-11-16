package recordmgr;

import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

class RecordMngrTest {
    @org.junit.jupiter.api.Test
    void submit() {
        HashSet<String> usernames = new HashSet<>(), categories = new HashSet<>();
        usernames.add("axb210107");
        categories.add("Deadlift");
        RecordMngr recordMngr = new RecordMngr(usernames, categories);

        Form valid = new Form("axb210107", "Deadlift", "Lifted 100 Pounds", "https://youtu.be/T9DgaK1ZoeA?si=BMj3EDyogYJCExEd");
        assertEquals("Record Submitted", recordMngr.submit(valid));

        Form badUsername = new Form("tst314159", "Deadlift", "Lifted 100 Pounds", "https://youtu.be/T9DgaK1ZoeA?si=BMj3EDyogYJCExEd");
        Form exceptionalUsername = new Form("", "Deadlift", "Lifted 100 Pounds", "https://youtu.be/T9DgaK1ZoeA?si=BMj3EDyogYJCExEd");
        assertAll("Username Validation", () -> assertEquals("Error Validating Username", recordMngr.submit(badUsername)), () -> assertEquals("Error Validating Username", recordMngr.submit(exceptionalUsername)));

        Form badCategory = new Form("axb210107", "Unicycling", "Unicycled 20 Miles", "https://youtu.be/T9DgaK1ZoeA?si=BMj3EDyogYJCExEd");
        Form exceptionalCategory = new Form("axb210107", "", "Look at this cool video", "https://youtu.be/T9DgaK1ZoeA?si=BMj3EDyogYJCExEd");
        assertAll("Category Validation", () -> assertEquals("Invalid Category", recordMngr.submit(badCategory)), () -> assertEquals("Invalid Category", recordMngr.submit(exceptionalCategory)));

        Form exceptionalDescription = new Form("axb210107", "Deadlift", "", "https://youtu.be/T9DgaK1ZoeA?si=BMj3EDyogYJCExEd");
        assertEquals("Invalid Description", recordMngr.submit(exceptionalDescription));

        Form badProof = new Form("axb210107", "Deadlift", "Lifted 1000 Pounds", "Trust Me :)");
        Form exceptionalProof = new Form("axb210107", "Deadlift", "Lifted 100 Pounds", "");
        assertAll("Proof Validation", () -> assertEquals("Invalid Proof", recordMngr.submit(badProof)), () -> assertEquals("Invalid Proof", recordMngr.submit(exceptionalProof)));
    }
}