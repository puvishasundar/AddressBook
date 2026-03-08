public class Main {
    public static void main(String[] args) {
        ContactDAO.load();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}