import java.io.*;
import java.util.*;

public class CsvImporter {

    // Minimal CSV splitter that respects quotes (simple cases)
    private static List<String> splitCsvLine(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                // handle escaped quotes ("")
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    cur.append('"');
                    i++; // skip next quote
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (ch == ',' && !inQuotes) {
                parts.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(ch);
            }
        }
        parts.add(cur.toString());
        return parts;
    }

    public static void importFromCsv(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("CSV file not found: " + fileName);
            return;
        }

        int total = 0, success = 0, skipped = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("CSV is empty.");
                return;
            }

            // Expect columns: id,name,phone,email (order matters)
            String line;
            while ((line = br.readLine()) != null) {
                total++;
                List<String> cols = splitCsvLine(line);
                while (cols.size() < 4) cols.add(""); // pad if missing

                String id    = cols.get(0).trim();
                String name  = cols.get(1).trim();
                String phone = cols.get(2).trim();
                String email = cols.get(3).trim();

                try {
                    Contact c = new Contact(id.isEmpty() ? null : id, name, phone, email);
                    // Reuse the DAO (validation + duplicate check happen inside)
                    ContactDAO.addContact(c);
                    success++;
                } catch (Exception ex) {
                    skipped++;
                    System.out.println("Skipped row " + total + ": " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Import failed: " + e.getMessage());
            return;
        }

        System.out.printf("Import completed. Total rows: %d, Added: %d, Skipped: %d%n", total, success, skipped);
    }
}