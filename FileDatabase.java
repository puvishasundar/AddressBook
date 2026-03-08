import java.util.*;
import java.io.*;

public class FileDatabase {
    private static final String FILE_NAME = "contacts.json";

    public static List<Contact> loadContacts() {
        List<Contact> list = new ArrayList<Contact>();
        try {
            File f = new File(FILE_NAME);
            if(!f.exists()) {
                f.createNewFile();
                FileWriter fw = new FileWriter(f);
                fw.write("[]");
                fw.close();
            }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null) sb.append(line);
            br.close();
            String content = sb.toString().trim();
            if(content.length() < 3) return list;
            content = content.substring(1, content.length()-1); // remove [ ]
            String[] items = content.split("\\},\\{");
            for(String item : items) {
                item = item.replace("{","").replace("}","");
                String[] fields = item.split(",");
                String id="", name="", phone="", email="";
                for(String field : fields) {
                    String[] kv = field.split(":");
                    if(kv.length<2) continue;
                    String key = kv[0].trim().replace("\"","");
                    String value = kv[1].trim().replace("\"","");
                    if(key.equals("id")) id=value;
                    else if(key.equals("name")) name=value;
                    else if(key.equals("phone")) phone=value;
                    else if(key.equals("email")) email=value;
                }
                list.add(new Contact(id,name,phone,email));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveContacts(List<Contact> list) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            fw.write("[");
            for(int i=0;i<list.size();i++){
                Contact c = list.get(i);
                fw.write("{\"id\":\""+c.getId()+"\",\"name\":\""+c.getName()+"\",\"phone\":\""+c.getPhone()+"\",\"email\":\""+c.getEmail()+"\"}");
                if(i!=list.size()-1) fw.write(",");
            }
            fw.write("]");
            fw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}