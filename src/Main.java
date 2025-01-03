import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    public static void main(String[] args) {
        JFrame apka = new JFrame();
        apka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        apka.setTitle("JFrame zadania");
        apka.setSize(new Dimension(800, 600));
        apka.setLayout(null); // Use null layout for absolute positioning

        JLabel label = new JLabel();
        JTextField textfield = new JTextField();
        JPasswordField passwordfield = new JPasswordField();
        JButton button = new JButton("Submit");
        JTextArea textarea = new JTextArea();
        JButton greetButton = new JButton("Enter Name");

        label.setBounds(50, 50, 100, 50);
        label.setText("Test");
        textfield.setBounds(50, 110, 100, 50);
        passwordfield.setBounds(50, 170, 100, 50);
        button.setBounds(50, 230, 100, 50);
        textarea.setBounds(50, 290, 200, 100);
        greetButton.setBounds(200, 230, 150, 50);

        apka.add(label);
        apka.add(textfield);
        apka.add(passwordfield);
        apka.add(button);
        apka.add(textarea);
        apka.add(greetButton);

        // Create a popup menu
        JPopupMenu popupMenu = createPopupMenu();

        // Add mouse listener for each component
        addPopupMenu(label, popupMenu);
        addPopupMenu(textfield, popupMenu);
        addPopupMenu(passwordfield, popupMenu);
        addPopupMenu(button, popupMenu);
        addPopupMenu(textarea, popupMenu);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textfield.getText();
                char[] text2 = passwordfield.getPassword();
                textarea.setText(text1 + "\n" + new String(text2));
            }
        });

        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(apka, "Jak masz na imię?", "Wprowadź imię: ", JOptionPane.QUESTION_MESSAGE);
                if (name != null && !name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(apka, "Witaj, " + name + "!", "Uszanowanko", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(apka, "Błędne dane", "Ostrzeżenie!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        apka.setVisible(true);
    }

    private static JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem changeSize = new JMenuItem("Change Size");
        JMenuItem changeColor = new JMenuItem("Change Color");
        JMenuItem changeLocation = new JMenuItem("Change Location");

        popupMenu.add(changeSize);
        popupMenu.add(changeColor);
        popupMenu.add(changeLocation);

        changeSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String width = JOptionPane.showInputDialog("Enter width");
                String height = JOptionPane.showInputDialog("Enter height");
                int widthf;
                int heightf;
                try {
                    widthf = Integer.parseInt(width);
                    heightf = Integer.parseInt(height);
                    Component source = ((JPopupMenu) ((JMenuItem) e.getSource()).getParent()).getInvoker();
                    source.setSize(widthf, heightf);
                } catch (Exception b) {
                    JOptionPane.showMessageDialog(null, "Bad input");
                }
            }
        });

        changeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
                if (color != null) {
                    Component source = ((JPopupMenu) ((JMenuItem) e.getSource()).getParent()).getInvoker();
                    if (source instanceof JLabel) {
                        (source).setForeground(color);
                    } else {
                        source.setBackground(color);
                    }
                }
            }
        });

        changeLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x = JOptionPane.showInputDialog("Enter x position");
                String y = JOptionPane.showInputDialog("Enter y position");
                int xf;
                int yf;
                try {
                    xf = Integer.parseInt(x);
                    yf = Integer.parseInt(y);
                    Component source = ((JPopupMenu) ((JMenuItem) e.getSource()).getParent()).getInvoker();
                    source.setLocation(new Point(xf, yf));
                } catch (Exception b) {
                    JOptionPane.showMessageDialog(null, "Bad input");
                }
            }
        });

        return popupMenu;
    }

    private static void addPopupMenu(Component component, JPopupMenu popupMenu) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}
