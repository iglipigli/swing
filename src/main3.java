import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main3 {
    public static void main(String[] args) {
        // Tworzenie głównego okna
        JFrame frame = new JFrame("Password Strength Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        // Etykieta
        JLabel label = new JLabel("Enter your password:");
        label.setBounds(20, 20, 200, 30);
        frame.add(label);

        // Pole tekstowe na hasło
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 60, 240, 30);
        frame.add(passwordField);

        // Przycisk "Check Strength"
        JButton checkButton = new JButton("Check Strength");
        checkButton.setBounds(20, 100, 150, 30);
        frame.add(checkButton);

        // Obsługa przycisku
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                String strength = evaluatePasswordStrength(password);

                // Wyświetlanie wyniku
                JOptionPane.showMessageDialog(frame, "Password Strength: " + strength,
                        "Strength Checker", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    // Metoda do oceny siły hasła
    private static String evaluatePasswordStrength(String password) {
        int score = 0;

        // Sprawdzanie długości
        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;

        // Sprawdzanie cyfr
        if (password.matches(".*\\d.*")) score++;

        // Sprawdzanie wielkich liter
        if (password.matches(".*[A-Z].*")) score++;

        // Sprawdzanie małych liter
        if (password.matches(".*[a-z].*")) score++;

        // Sprawdzanie znaków specjalnych
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) score++;

        // Ocena siły hasła
        if (score <= 2) return "Weak";
        if (score <= 4) return "Moderate";
        return "Strong";
    }
}
