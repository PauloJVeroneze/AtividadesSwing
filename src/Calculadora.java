import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private final JTextField display;
    private String operador;
    private double num1;
    private double resultado;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5)); // Layout 4x4

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        double num2;
        if (command.matches("[0-9]")) {
            display.setText(display.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            operador = command;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operador) {
                case "+" -> resultado = num1 + num2;
                case "-" -> resultado = num1 - num2;
                case "*" -> resultado = num1 * num2;
                case "/" -> {
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro: Divisão por zero!");
                        display.setText("");
                        return;
                    }
                }
            }
            display.setText(String.valueOf(resultado));
        } else if (command.equals("C")) { // Limpar
            display.setText("");
            num1 = resultado = 0;
            operador = null;
        }
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
