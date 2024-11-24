import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class AgendaDiaria extends JFrame {
    private final JTextField compromissoField;
    private final JSpinner dataHoraSpinner;
    private final JTable tabelaCompromissos;
    private final DefaultTableModel modeloTabela;

    public AgendaDiaria() {
        setTitle("Agenda Diária");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new GridLayout(2, 2, 10, 10));
        compromissoField = new JTextField();
        dataHoraSpinner = new JSpinner(new SpinnerDateModel());

        painelEntrada.add(new JLabel("Compromisso:"));
        painelEntrada.add(compromissoField);
        painelEntrada.add(new JLabel("Data/Hora:"));
        painelEntrada.add(dataHoraSpinner);

        JButton adicionarButton = new JButton("Adicionar Compromisso");
        painelEntrada.add(adicionarButton);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Data/Hora");
        modeloTabela.addColumn("Compromisso");
        tabelaCompromissos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaCompromissos);

        JButton removerButton = new JButton("Remover Compromisso");

        add(painelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(removerButton, BorderLayout.SOUTH);

        adicionarButton.addActionListener(_ -> adicionarCompromisso());

        removerButton.addActionListener(_ -> removerCompromisso());

        setVisible(true);
    }

    private void adicionarCompromisso() {
        String compromisso = compromissoField.getText().trim();
        Object dataHora = dataHoraSpinner.getValue();

        if (!compromisso.isEmpty()) {
            modeloTabela.addRow(new Object[]{dataHora, compromisso});
            compromissoField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, insira um compromisso!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCompromisso() {
        int row = tabelaCompromissos.getSelectedRow();
        if (row != -1) {
            modeloTabela.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um compromisso para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AgendaDiaria();
    }
}
