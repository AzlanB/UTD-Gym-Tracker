package recordmgr;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class DatabaseMngr {
    public static boolean submitRecord(Form form){
        try {
            File file = new File("Submitted Records.txt");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter("Submitted Records.txt", true));
            writer.append(form.username);
            writer.append("\n");
            writer.append(form.category);
            writer.append("\n");
            writer.append(form.description);
            writer.append("\n");
            writer.append(form.proof);
            writer.append("\n\n");
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}