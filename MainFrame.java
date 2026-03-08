import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private JButton addBtn, editBtn, deleteBtn, refreshBtn, searchBtn;

    public MainFrame() {
        setTitle("Digital Address Book");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"ID","Name","Phone","Email"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel top = new JPanel();
        searchField = new JTextField(15);
        searchBtn = new JButton("Search");
        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        refreshBtn = new JButton("Refresh");

        top.add(searchField); top.add(searchBtn);
        top.add(addBtn); top.add(editBtn); top.add(deleteBtn); top.add(refreshBtn);
        add(top, BorderLayout.NORTH);

        refreshTable();

        final MainFrame self = this;

        refreshBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ refreshTable(); }
        });

        searchBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String kw = searchField.getText();
                refreshTable(ContactDAO.search(kw));
            }
        });

        addBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                AddContactForm f = new AddContactForm(self);
                f.setVisible(true);
            }
        });

        editBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int r = table.getSelectedRow();
                if(r==-1){ JOptionPane.showMessageDialog(null,"Select a row"); return; }
                String id = (String)model.getValueAt(r,0);
                String name = (String)model.getValueAt(r,1);
                String phone = (String)model.getValueAt(r,2);
                String email = (String)model.getValueAt(r,3);
                EditContactForm f = new EditContactForm(self,id,name,phone,email);
                f.setVisible(true);
            }
        });

        deleteBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int r = table.getSelectedRow();
                if(r==-1){ JOptionPane.showMessageDialog(null,"Select a row"); return; }
                String id = (String)model.getValueAt(r,0);
                int res = JOptionPane.showConfirmDialog(null,"Delete selected contact?");
                if(res==JOptionPane.YES_OPTION){
                    try{ ContactDAO.deleteContact(id); refreshTable(); }
                    catch(Exception ex){ JOptionPane.showMessageDialog(null,ex.getMessage()); }
                }
            }
        });
    }

    public void refreshTable(){ refreshTable(ContactDAO.getAllContacts()); }

    public void refreshTable(List<Contact> list){
        model.setRowCount(0);
        for(Contact c:list){
            model.addRow(new Object[]{c.getId(),c.getName(),c.getPhone(),c.getEmail()});
        }
    }
}