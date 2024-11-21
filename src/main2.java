import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(null);

        // Pole tekstowe na wynik
        JTextField display = new JTextField();
        display.setBounds(20, 20, 240, 40);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false); // Pole tylko do odczytu
        frame.add(display);

        // Panel na przyciski
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 80, 240, 240);
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // Przyciski cyfr i operacji
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Obsługa kalkulatora
        StringBuilder currentInput = new StringBuilder(); // Zapisuje bieżące dane wejściowe
        final double[] firstOperand = {0}; // Pierwszy operand
        final String[] operator = {""}; // Operator (+, -, *, /)

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));

            // Akcja przycisków
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if ("0123456789".contains(command)) { // Przyciski cyfr
                        currentInput.append(command);
                        display.setText(currentInput.toString());
                    } else if ("+-*/".contains(command)) { // Przyciski operacji
                        if (!currentInput.isEmpty()) {
                            firstOperand[0] = Double.parseDouble(currentInput.toString());
                            currentInput.setLength(0); // Wyczyść dane wejściowe
                            operator[0] = command;
                            display.setText(""); // Wyświetl pusty ekran
                        }
                    } else if ("=".equals(command)) { // Przycisk "="
                        if (!currentInput.isEmpty() && !operator[0].isEmpty()) {
                            double secondOperand = Double.parseDouble(currentInput.toString());
                            double result = 0;

                            switch (operator[0]) {
                                case "+":
                                    result = firstOperand[0] + secondOperand;
                                    break;
                                case "-":
                                    result = firstOperand[0] - secondOperand;
                                    break;
                                case "*":
                                    result = firstOperand[0] * secondOperand;
                                    break;
                                case "/":
                                    if (secondOperand != 0) {
                                        result = firstOperand[0] / secondOperand;
                                    } else {
                                        display.setText("Error");
                                        return;
                                    }
                                    break;
                            }

                            display.setText(String.valueOf(result));
                            currentInput.setLength(0); // Zresetuj dane wejściowe
                            operator[0] = ""; // Zresetuj operator
                        }
                    } else if ("C".equals(command)) { // Przycisk "C"
                        currentInput.setLength(0);
                        operator[0] = "";
                        firstOperand[0] = 0;
                        display.setText("");
                    }
                }
            });

            buttonPanel.add(button);
        }

        frame.add(buttonPanel);
        frame.setVisible(true);
    }
}
