import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class interface_teste {

    private JFrame frame;
    private JTextArea codigoArea;
    private JTable tabelaSimbolosTable;
    private JTable tokensMalFormadosTable;

    public interface_teste() {
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
        String[] colunas = { "Token", "Tipo", "Linha" };
        DefaultTableModel tabelaSimbolosModel = new DefaultTableModel(colunas, 0);
        tabelaSimbolosTable = new JTable(tabelaSimbolosModel);
        JScrollPane tabelaSimbolosPane = new JScrollPane(tabelaSimbolosTable);
        tabelaSimbolosPane.setPreferredSize(new Dimension(400, 300));
        abas.addTab("Tabela de Símbolos", tabelaSimbolosPane);

    
        DefaultTableModel tokensMalFormadosModel= new DefaultTableModel(colunas, 0);
        tokensMalFormadosTable = new JTable(tokensMalFormadosModel);
        JScrollPane tokensMFPane = new JScrollPane(tokensMalFormadosTable);
        tokensMFPane .setPreferredSize(new Dimension(400, 300));
        abas.addTab("Tabela De Tokens Mal Formados", tokensMFPane);

        painelPrincipal.add(abas, BorderLayout.CENTER);

        // Botão para analisar
        JButton botaoAnalisar = new JButton("Analisar");
        botaoAnalisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TesteAnalisador();
            }
        });

        // Botão para limpar tudo
        JButton botaoLimpar = new JButton("Limpar Tudo");
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigoArea.setText("");
                ((DefaultTableModel) tabelaSimbolosTable.getModel()).setRowCount(0);
                ((DefaultTableModel)  tokensMalFormadosTable .getModel()).setRowCount(0);
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
        new interface_teste();
    }
}