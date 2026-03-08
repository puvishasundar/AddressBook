import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditContactForm extends JDialog {
    public EditContactForm(JFrame parent, String id, String name, String phone, String email){
        super(parent,"Edit Contact",true);
        setSize(300,200);
        setLayout(new GridLayout(4,2));

        final JTextField nameField = new JTextField(name);
        final JTextField phoneField = new JTextField(phone);
        final JTextField emailField = new JTextField(email);
        final String contactId = id;
        final JFrame parentRef = parent;

        JButton updateBtn = new JButton("Update");

        add(new JLabel("Name")); add(nameField);
        add(new JLabel("Phone")); add(phoneField);
        add(new JLabel("Email")); add(emailField);
        add(updateBtn);

        updateBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Contact c = new Contact(contactId,nameField.getText(),phoneField.getText(),emailField.getText());
                try{
                    ContactDAO.updateContact(c);
                    ((MainFrame)parentRef).refreshTable();
                    dispose();
                }catch(Exception ex){ JOptionPane.showMessageDialog(null,ex.getMessage()); }
            }
        });
    }
}