import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraIMC extends JFrame {
    private final JTextField pesoField;
    private final JTextField alturaField;
    private final JLabel resultadoLabel;

    public CalculadoraIMC() {
        setTitle("Calculadora de IMC");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("Peso (kg):"));
        pesoField = new JTextField();
        add(pesoField);

        add(new JLabel("Altura (m):"));
        alturaField = new JTextField();
        add(alturaField);

        JButton calcularButton = new JButton("Calcular IMC");
        add(calcularButton);

        resultadoLabel = new JLabel("Resultado: ");
        resultadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultadoLabel);

        calcularButton.addActionListener(e -> calcularIMC());

        setVisible(true);
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(pesoField.getText().trim());
            double altura = Double.parseDouble(alturaField.getText().trim());

            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(this, "Peso e altura devem ser maiores que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double imc = peso / (altura * altura);

            String categoria;
            if (imc < 18.5) {
                categoria = "Baixo peso";
            } else if (imc >= 18.5 && imc < 24.9) {
                categoria = "Normal";
            } else if (imc >= 25 && imc < 29.9) {
                categoria = "Sobrepeso";
            } else {
                categoria = "Obesidade";
            }

            resultadoLabel.setText(String.format("IMC: %.2f - %s", imc, categoria));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CalculadoraIMC();
    }
}
