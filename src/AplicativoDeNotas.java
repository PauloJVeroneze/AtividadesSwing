import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AplicativoDeNotas extends JFrame {
    private final JTextField notaField;
    private final JTextArea listaNotasArea;
    private final JLabel mediaLabel;
    private final ArrayList<Double> notas;

    public AplicativoDeNotas() {
        setTitle("Aplicativo de Notas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        notas = new ArrayList<>();

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout());

        notaField = new JTextField(10);
        JButton adicionarNotaButton = new JButton("Adicionar Nota");

        painelSuperior.add(new JLabel("Nota:"));
        painelSuperior.add(notaField);
        painelSuperior.add(adicionarNotaButton);

        add(painelSuperior, BorderLayout.NORTH);

        listaNotasArea = new JTextArea(10, 30);
        listaNotasArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaNotasArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout());

        JButton calcularMediaButton = new JButton("Calcular Média");
        mediaLabel = new JLabel("Média: - | Status: -");

        painelInferior.add(calcularMediaButton);
        painelInferior.add(mediaLabel);

        add(painelInferior, BorderLayout.SOUTH);

        adicionarNotaButton.addActionListener(_ -> adicionarNota());

        calcularMediaButton.addActionListener(_ -> calcularMedia());

        setVisible(true);
    }

    private void adicionarNota() {
        try {
            double nota = Double.parseDouble(notaField.getText());
            if (nota >= 0 && nota <= 10) {
                notas.add(nota);
                atualizarListaNotas();
                notaField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "A nota deve estar entre 0 e 10!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor numérico válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarListaNotas() {
        StringBuilder lista = new StringBuilder("Notas adicionadas:\n");
        for (int i = 0; i < notas.size(); i++) {
            lista.append("Nota ").append(i + 1).append(": ").append(notas.get(i)).append("\n");
        }
        listaNotasArea.setText(lista.toString());
    }

    private void calcularMedia() {
        if (notas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma nota foi adicionada!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        double media = soma / notas.size();
        String status = (media >= 7.0) ? "Aprovado" : "Reprovado";
        mediaLabel.setText(String.format("Média: %.2f | Status: %s", media, status));
    }

    public static void main(String[] args) {
        new AplicativoDeNotas();
    }
}
