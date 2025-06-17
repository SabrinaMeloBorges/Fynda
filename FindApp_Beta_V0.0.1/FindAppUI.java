import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FindAppUI extends JFrame {

    private JTextField campoBusca;
    private JTextArea resultado;
    private EstabelecimentoService service;

    public FindAppUI() {
        service = new EstabelecimentoService();
        setTitle("FindApp - Localizador de Estabelecimentos");
        setSize(700, 500);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        // Campo de busca
        campoBusca = new JTextField();
        campoBusca.setFont(new Font("SansSerif", Font.PLAIN, 16));
        campoBusca.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        campoBusca.setBackground(Color.WHITE);
        campoBusca.setForeground(Color.BLACK);

        // Botão de busca
        JButton botaoBuscar = new JButton("🔍");
        botaoBuscar.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoBuscar.setBackground(Color.BLACK);
        botaoBuscar.setForeground(Color.WHITE);
        botaoBuscar.setFocusPainted(false);
        botaoBuscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Painel de topo
        JPanel painelTopo = new JPanel(new BorderLayout(5, 5));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        painelTopo.setBackground(Color.WHITE);
        painelTopo.add(campoBusca, BorderLayout.CENTER);
        painelTopo.add(botaoBuscar, BorderLayout.EAST);

        // Área de resultado
        resultado = new JTextArea();
        resultado.setEditable(false);
        resultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultado.setMargin(new Insets(10, 10, 10, 10));
        resultado.setBackground(Color.WHITE);
        resultado.setForeground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(resultado);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        add(painelTopo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        botaoBuscar.addActionListener(e -> buscar());
        campoBusca.addActionListener(e -> buscar());

        try {
            service.carregar("dados/estabelecimentos.txt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar arquivo: " + ex.getMessage());
        }
    }

    private void buscar() {
        String termo = campoBusca.getText();
        List<Estabelecimento> achados = service.buscar(termo);

        resultado.setText("");
        if (achados.isEmpty()) {
            resultado.setText("Nenhum estabelecimento encontrado para \"" + termo + "\".");
            return;
        }

        for (Estabelecimento e : achados) {
            resultado.append("🔎 " + e.getNome() + "\n");
            resultado.append("Endereço: " + e.getEndereco() + "\n");
            resultado.append("Telefone: " + e.getTelefone() + "\n");
            resultado.append("Categoria: " + e.getCategoria() + "\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FindAppUI().setVisible(true));
    }
}
