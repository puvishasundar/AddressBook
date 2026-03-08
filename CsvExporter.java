import java.io.FileWriter;
import java.util.List;

public class CsvExporter {

    public static void exportAllToCsv(String filename) throws Exception {
        List<Contact> list = ContactDAO.getAllContacts();
        FileWriter fw = new FileWriter(filename);

        fw.write("id,name,phone,email\n");

        for (Contact c : list) {
            fw.write(c.getId() + "," +
                     c.getName() + "," +
                     c.getPhone() + "," +
                     c.getEmail() + "\n");
        }

        fw.close();
    }
}