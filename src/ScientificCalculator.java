
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;
    private StringBuilder currentInput;
    private double result;
    private String operator;

    public ScientificCalculator() {
        display = new JTextField();
        panel = new JPanel();
        currentInput = new StringBuilder();
        result = 0;
        operator = "";

        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        String[] buttons = {
                "7", "8", "9", "/", "sqrt",
                "4", "5", "6", "*", "^",
                "1", "2", "3", "-", "C",
                "0", ".", "=", "+", "CE"
        };

        panel.setLayout(new GridLayout(5, 5));

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setTitle("Scientific Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                currentInput.setLength(0);
                display.setText("");
                break;
            case "CE":
                currentInput.setLength(0);
                display.setText("");
                result = 0;
                operator = "";
                break;
            case "=":
                computeResult();
                operator = "";
                break;
            case "sqrt":
                computeSquareRoot();
                break;
            case "^":
                operator = "^";
                result = Double.parseDouble(currentInput.toString());
                currentInput.setLength(0);
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                operator = command;
                result = Double.parseDouble(currentInput.toString());
                currentInput.setLength(0);
                break;
            default: // numbers and dot
                currentInput.append(command);
                display.setText(currentInput.toString());
                break;
        }
    }

    private void computeResult() {
        double currentValue = Double.parseDouble(currentInput.toString());
        switch (operator) {
            case "+":
                result += currentValue;
                break;
            case "-":
                result -= currentValue;
                break;
            case "*":
                result *= currentValue;
                break;
            case "/":
                if (currentValue != 0) {
                    result /= currentValue;
                } else {
                    display.setText("Error: Division by zero");
                    return;
                }
                break;
            case "^":
                result = Math.pow(result, currentValue);
                break;
        }
        display.setText(String.valueOf(result));
        currentInput.setLength(0);
    }

    private void computeSquareRoot() {
        double value = Double.parseDouble(currentInput.toString());
        result = Math.sqrt(value);
        display.setText(String.valueOf(result));
        currentInput.setLength(0);
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}

