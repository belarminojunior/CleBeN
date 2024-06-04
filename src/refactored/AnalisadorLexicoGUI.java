package refactored;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;

public class AnalisadorLexicoGUI {

    private JFrame frame;
    private JTextArea codigoTextArea;
    private JTextArea lineNumbers;
    private JTable tabelaSimbolosTable;

    public AnalisadorLexicoGUI() {
        criarInterface();
    }

    private void criarInterface() {
        frame = new JFrame("Analisador Léxico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JTabbedPane abas = new JTabbedPane();

        codigoTextArea = new JTextArea();
        Font codeFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
        codigoTextArea.setFont(codeFont);
        JScrollPane codigoScrollPane = new JScrollPane(codigoTextArea);
        codigoScrollPane.setPreferredSize(new Dimension(400, 300));
        abas.addTab("Escrita De Código", codigoScrollPane);

        // Add line numbers to the text area
        lineNumbers = new JTextArea("1");
        lineNumbers.setFont(codeFont);
        lineNumbers.setBackground(Color.LIGHT_GRAY);
        lineNumbers.setEditable(false);
        codigoTextArea.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = codigoTextArea.getDocument().getLength();
                Element root = codigoTextArea.getDocument().getDefaultRootElement();
                String text = "1" + System.lineSeparator();
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.lineSeparator();
                }
                return text;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lineNumbers.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                lineNumbers.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lineNumbers.setText(getText());
            }
        });
        codigoScrollPane.setRowHeaderView(lineNumbers);

        tabelaSimbolosTable = criarTabela(new String[]{"Token", "Tipo"});
        JScrollPane tabelaSimbolosScrollPane = new JScrollPane(tabelaSimbolosTable);
        tabelaSimbolosScrollPane.setPreferredSize(new Dimension(400, 300));
        abas.addTab("Tabela de Símbolos", tabelaSimbolosScrollPane);

        painelPrincipal.add(abas, BorderLayout.CENTER);

        JPanel botoesPanel = new JPanel();
        botoesPanel.add(criarBotaoAnalisar());
        botoesPanel.add(criarBotaoLimpar());

        painelPrincipal.add(botoesPanel, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
    }

    private JButton criarBotaoAnalisar() {
        JButton botaoAnalisar = new JButton("Analisar");
        botaoAnalisar.addActionListener((ActionEvent e) -> {
            // Obter o texto do código fonte da área de texto
            String codigo = codigoTextArea.getText();

            // Criar um novo analisador léxico CleBeNLexer com o texto do código fonte
            CleBeNLexer lexer = new CleBeNLexer(new StringReader(codigo));

            // Limpar as tabelas antes de preenchê-las com novos dados
            ((DefaultTableModel) tabelaSimbolosTable.getModel()).setRowCount(0);

            // Iterar sobre os tokens produzidos pelo analisador léxico
            TokenType token;
            while (true) {
                try {
                    if ((token = lexer.yylex()) == TokenType.EOF) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (token != TokenType.ERROR) {
                    ((DefaultTableModel) tabelaSimbolosTable.getModel()).addRow(new Object[]{lexer.yytext(), token});
                }
            }
        });
        return botaoAnalisar;
    }

    private JButton criarBotaoLimpar() {
        JButton botaoLimpar = new JButton("Limpar Tudo");
        botaoLimpar.addActionListener((ActionEvent e) -> {
            codigoTextArea.setText("");
            ((DefaultTableModel) tabelaSimbolosTable.getModel()).setRowCount(0);
        });
        return botaoLimpar;
    }

    private JTable criarTabela(String[] colunas) {
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        return new JTable(modeloTabela);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnalisadorLexicoGUI());
    }
}
