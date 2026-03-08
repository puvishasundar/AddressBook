import java.util.*;

public class ContactDAO {
    private static List<Contact> contacts = new ArrayList<Contact>();

    public static void load() { contacts = FileDatabase.loadContacts(); }
    public static void save() { FileDatabase.saveContacts(contacts); }

    public static void addContact(Contact c) throws Exception {
        if(!Validator.validateContact(c)) throw new Exception("Invalid contact");
        c.setId(UUID.randomUUID().toString());
        contacts.add(c);
        save();
    }

    public static void updateContact(Contact c) throws Exception {
        if(!Validator.validateContact(c)) throw new Exception("Invalid contact");
        for(int i=0;i<contacts.size();i++){
            if(contacts.get(i).getId().equals(c.getId())){
                contacts.set(i,c);
                save();
                return;
            }
        }
        throw new Exception("Contact not found");
    }

    public static void deleteContact(String id) throws Exception {
        for(int i=0;i<contacts.size();i++){
            if(contacts.get(i).getId().equals(id)){
                contacts.remove(i);
                save();
                return;
            }
        }
        throw new Exception("Contact not found");
    }

    public static List<Contact> getAllContacts() { return new ArrayList<Contact>(contacts); }

    public static List<Contact> search(String keyword) {
        List<Contact> result = new ArrayList<Contact>();
        keyword = keyword.toLowerCase();
        for(Contact c:contacts){
            if(c.getName().toLowerCase().contains(keyword) ||
               c.getPhone().toLowerCase().contains(keyword) ||
               c.getEmail().toLowerCase().contains(keyword)) {
                result.add(c);
            }
        }
        return result;
    }
}