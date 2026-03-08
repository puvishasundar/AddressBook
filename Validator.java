public class Validator {
    public static boolean isValidName(String name) {
        return name != null && name.trim().length() > 0;
    }

    public static boolean isValidPhone(String phone) {
        return phone == null || phone.isEmpty() || phone.matches("\\d{7,15}");
    }

    public static boolean isValidEmail(String email) {
        return email == null || email.isEmpty() || email.matches(".+@.+\\..+");
    }

    public static boolean validateContact(Contact c) {
        return isValidName(c.getName()) &&
               isValidPhone(c.getPhone()) &&
               isValidEmail(c.getEmail());
    }
}