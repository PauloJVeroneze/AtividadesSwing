import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CadastroDeContatos extends JFrame {
    private final JTextField nomeField;
    private final JTextField telefoneField;
    private final JTextField emailField;
    private final DefaultListModel<String> listaModel;
    private final JList<String> listaContatos;
    private final ArrayList<Contato> contacts;

    public CadastroDeContatos() {
        setTitle("Cadastro de Contatos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        contacts = new ArrayList<>();

        JPanel painelSuperior = new JPanel(new GridLayout(4, 2, 5, 5));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelSuperior.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        painelSuperior.add(nomeField);

        painelSuperior.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        painelSuperior.add(telefoneField);

        painelSuperior.add(new JLabel("E-mail:"));
        emailField = new JTextField();
        painelSuperior.add(emailField);

        JButton adicionarContatoButton = new JButton("Adicionar Contato");
        painelSuperior.add(adicionarContatoButton);

        JButton removerContatoButton = new JButton("Remover Contato");
        painelSuperior.add(removerContatoButton);

        add(painelSuperior, BorderLayout.NORTH);

        listaModel = new DefaultListModel<>();
        listaContatos = new JList<>(listaModel);
        JScrollPane scrollPane = new JScrollPane(listaContatos);
        add(scrollPane, BorderLayout.CENTER);

        adicionarContatoButton.addActionListener(_ -> adicionarContato());

        removerContatoButton.addActionListener(_ -> removerContato());

        setVisible(true);
    }

    private void adicionarContato() {
        String nome = nomeField.getText().trim();
        String telefone = telefoneField.getText().trim();
        String email = emailField.getText().trim();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contato contato = new Contato(nome, telefone, email);
        contacts.add(contato);
        listaModel.addElement(contato.toString());

        nomeField.setText("");
        telefoneField.setText("");
        emailField.setText("");
    }

    private void removerContato() {
        int selectedIndex = listaContatos.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum contato selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        contacts.remove(selectedIndex);
        listaModel.remove(selectedIndex);
    }

    public static void main(String[] args) {
        new CadastroDeContatos();
    }
}

class Contato {
    private final String nome;
    private final String telefone;
    private final String email;

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }


    public String toString() {
        return nome + " - " + telefone + " - " + email;
    }
}
