import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class main4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Contact Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);

        // Lista kontaktów
        DefaultListModel<String> contactListModel = new DefaultListModel<>();
        JList<String> contactList = new JList<>(contactListModel);
        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setBounds(20, 20, 200, 300);
        frame.add(scrollPane);

        // Panel do wprowadzania danych
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(240, 20, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(300, 20, 200, 30);
        frame.add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(240, 60, 100, 30);
        frame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(300, 60, 200, 30);
        frame.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(240, 100, 100, 30);
        frame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(300, 100, 200, 30);
        frame.add(emailField);

        // Przyciski
        JButton addButton = new JButton("Add");
        addButton.setBounds(240, 140, 80, 30);
        frame.add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(330, 140, 80, 30);
        frame.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(420, 140, 80, 30);
        frame.add(deleteButton);

        // Lista przechowująca obiekty kontaktów
        ArrayList<Contact> contacts = new ArrayList<>();

        // Dodawanie kontaktu
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Contact newContact = new Contact(name, phone, email);
                contacts.add(newContact);
                contactListModel.addElement(newContact.toString());

                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
            }
        });

        // Edytowanie kontaktu
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a contact to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Contact selectedContact = contacts.get(selectedIndex);
                selectedContact.setName(name);
                selectedContact.setPhone(phone);
                selectedContact.setEmail(email);

                contactListModel.set(selectedIndex, selectedContact.toString());

                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
            }
        });

        // Usuwanie kontaktu
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                contacts.remove(selectedIndex);
                contactListModel.remove(selectedIndex);
            }
        });

        frame.setVisible(true);
    }
}

// Klasa reprezentująca kontakt
class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " - " + phone + " - " + email;
    }
}
