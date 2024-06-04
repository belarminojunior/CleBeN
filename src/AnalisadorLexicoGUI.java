import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AnalisadorLexicoGUI {

    private JFrame frame;
    private JTextArea codigoArea;
    private JTable tabelaSimbolosTable;
    private JTable tokensMalFormadosTable;

    public AnalisadorLexicoGUI() {
        criarInterface();
    }

    private void criarInterface() {
        frame = new JFrame("Analisador Léxico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Aba para o código
        JTabbedPane abas = new JTabbedPane();

        codigoArea = new JTextArea();
        codigoArea.setPreferredSize(new Dimension(400, 300));
        abas.addTab("Escrita De Código", new JScrollPane(codigoArea));

        // Tabela de Símbolos com colunas "Token" e "Tipo"
        String[] colunasTabelaSimbolos = { "Token", "Tipo" };
        DefaultTableModel tabelaSimbolosModel = new DefaultTableModel(colunasTabelaSimbolos, 0);
        tabelaSimbolosTable = new JTable(tabelaSimbolosModel);
        JScrollPane tabelaSimbolosPane = new JScrollPane(tabelaSimbolosTable);
        tabelaSimbolosPane.setPreferredSize(new Dimension(400, 300));
        abas.addTab("Tabela de Símbolos", tabelaSimbolosPane);

        tokensMalFormadosTable = new JTable();
        JScrollPane tokensMalFormadosPane = new JScrollPane(tokensMalFormadosTable);
        tokensMalFormadosPane.setPreferredSize(new Dimension(400, 300));
        abas.addTab("Tokens Mal Formados", tokensMalFormadosPane);

        painelPrincipal.add(abas, BorderLayout.CENTER);

        // Botão para analisar
        JButton botaoAnalisar = new JButton("Analisar");
        botaoAnalisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        // Botão para limpar tudo
        JButton botaoLimpar = new JButton("Limpar Tudo");
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigoArea.setText("");
                ((DefaultTableModel) tabelaSimbolosTable.getModel()).setRowCount(0);
                ((DefaultTableModel) tokensMalFormadosTable.getModel()).setRowCount(0);
            }
        });

        JPanel botoesPanel = new JPanel();
        botoesPanel.add(botaoAnalisar);
        botoesPanel.add(botaoLimpar);

        painelPrincipal.add(botoesPanel, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new AnalisadorLexicoGUI();
    }
}