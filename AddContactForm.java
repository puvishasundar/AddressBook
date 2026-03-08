import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddContactForm extends JDialog {
    public AddContactForm(JFrame parent){
        super(parent,"Add Contact",true);
        setSize(300,200);
        setLayout(new GridLayout(4,2));

        final JTextField nameField = new JTextField();
        final JTextField phoneField = new JTextField();
        final JTextField emailField = new JTextField();
        final JFrame parentRef = parent;

        JButton saveBtn = new JButton("Save");

        add(new JLabel("Name")); add(nameField);
        add(new JLabel("Phone")); add(phoneField);
        add(new JLabel("Email")); add(emailField);
        add(saveBtn);

        saveBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Contact c = new Contact(nameField.getText(),phoneField.getText(),emailField.getText());
                try{
                    ContactDAO.addContact(c);
                    ((MainFrame)parentRef).refreshTable();
                    dispose();
                }catch(Exception ex){ JOptionPane.showMessageDialog(null,ex.getMessage()); }
            }
        });
    }
}