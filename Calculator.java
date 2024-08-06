package com.mycompany.calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {

    private static String currentOperation = "";
    private static double firstOperand = 0;
    private static boolean isNewOperation = true;

    public static void main(String[] args) {
        
        // Create the JFrame for the calculator and give it a title
        JFrame calculator = new JFrame("Calculator");
        
        // Set the size of the calculator
        calculator.setSize(400, 400);
        calculator.setLocationRelativeTo(null);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the display screen for the calculator
        JTextField screen = new JTextField();
        screen.setEditable(false);  // Make the screen non-editable
        screen.setHorizontalAlignment(JTextField.RIGHT); // Align the text to the right
        screen.setPreferredSize(new java.awt.Dimension(400, 70)); // Set preferred size of the screen
        
        // Add the screen to the JFrame at the top
        calculator.add(screen, BorderLayout.NORTH);

        // Create an array with the button texts
        String[] buttons = {
            "1", "2", "3", "4",
            "5", "6", "7", "8",
            "9", "0", "+", "-",
            "/", "X", "AC", "C", "="
        };
         
        // Create a JPanel for the buttons with a GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5)); // 5 rows, 4 columns, 5px spacing between buttons

        // Add the buttons to the JPanel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setPreferredSize(new java.awt.Dimension(50, 50)); // Set preferred size of the buttons
            buttonPanel.add(button);

            // Add an ActionListener to each button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if (command.equals("AC")) {
                        screen.setText(""); // Clear the screen
                        currentOperation = "";
                        firstOperand = 0;
                        isNewOperation = true;
                    } else if (command.equals("C")) {
                        String currentText = screen.getText();
                        if (!currentText.isEmpty()) {
                            screen.setText(currentText.substring(0, currentText.length() - 1)); // Delete the last character
                        }
                    } else if (command.equals("=")) {
                        if (currentOperation.isEmpty()) {
                            screen.setText("Error");
                            return;
                        }
                        double secondOperand = Double.parseDouble(screen.getText());
                        double result = 0;

                        switch (currentOperation) {
                            case "+":
                                result = firstOperand + secondOperand;
                                break;
                            case "-":
                                result = firstOperand - secondOperand;
                                break;
                            case "X":
                                result = firstOperand * secondOperand;
                                break;
                            case "/":
                                if (secondOperand != 0) {
                                    result = firstOperand / secondOperand;
                                } else {
                                    screen.setText("Error");
                                    return;
                                }
                                break;
                        }

                        screen.setText(String.valueOf(result));
                        isNewOperation = true;
                        currentOperation = ""; // Reset the operation after the calculation
                    } else if ("+-X/".contains(command)) {
                        firstOperand = Double.parseDouble(screen.getText());
                        currentOperation = command;
                        isNewOperation = true;
                    } else {
                        if (isNewOperation) {
                            screen.setText(command);
                            isNewOperation = false;
                        } else {
                            screen.setText(screen.getText() + command);
                        }
                    }
                }
            });
        }

        // Add the button JPanel to the JFrame
        calculator.add(buttonPanel, BorderLayout.CENTER);

        // Make the calculator visible
        calculator.pack(); // Adjust the size of the JFrame automatically based on the components
        calculator.setVisible(true);
    }
}
