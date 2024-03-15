package models;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha {

    private JFrame frame;
    private JButton[][] botoes = new JButton[3][3];
    private boolean vezDoX = true;
    private int jogadasFeitas = 0;
    private int vitoriasX = 0;
    private int vitoriasO = 0;
    private JLabel lblVitoriasX;
    private JLabel lblVitoriasO;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JogoDaVelha window = new JogoDaVelha();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public JogoDaVelha() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("| Jogo da Velha |");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(136, 11, 172, 30);
        frame.getContentPane().add(lblTitulo);

        lblVitoriasX = new JLabel("Vitórias do X: 0");
        lblVitoriasX.setFont(new Font("Arial", Font.PLAIN, 14));
        lblVitoriasX.setBounds(23, 18, 103, 20);
        frame.getContentPane().add(lblVitoriasX);

        lblVitoriasO = new JLabel("Vitórias do O: 0");
        lblVitoriasO.setFont(new Font("Arial", Font.PLAIN, 14));
        lblVitoriasO.setBounds(314, 18, 120, 20);
        frame.getContentPane().add(lblVitoriasO);

        // Adicionando botões para o jogo da velha
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int x = i;
                final int y = j;
                JButton botao = new JButton("");
                botao.setFont(new Font("Arial", Font.BOLD, 48));
                botao.setBounds(50 + j * 100, 50 + i * 100, 100, 100);
                botao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!botoes[x][y].getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Esta célula já está ocupada!", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if (vezDoX) {
                            botoes[x][y].setText("X");
                        } else {
                            botoes[x][y].setText("O");
                        }

                        vezDoX = !vezDoX;
                        jogadasFeitas++;

                        if (verificarVitoria("X")) {
                            JOptionPane.showMessageDialog(frame, "O jogador X venceu!", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
                            vitoriasX++;
                            lblVitoriasX.setText("Vitórias do X: " + vitoriasX);
                            reiniciarJogo();
                        } else if (verificarVitoria("O")) {
                            JOptionPane.showMessageDialog(frame, "O jogador O venceu!", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
                            vitoriasO++;
                            lblVitoriasO.setText("Vitórias do O: " + vitoriasO);
                            reiniciarJogo();
                        } else if (jogadasFeitas == 9) {
                            JOptionPane.showMessageDialog(frame, "Empate!", "Empate", JOptionPane.INFORMATION_MESSAGE);
                            reiniciarJogo();
                        }
                    }
                });
                botoes[i][j] = botao;
                frame.getContentPane().add(botao);
            }
        }
    }

    // Método para verificar se algum jogador venceu
    private boolean verificarVitoria(String jogador) {
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(jogador) && botoes[i][1].getText().equals(jogador)
                    && botoes[i][2].getText().equals(jogador)) {
                return true; // Verifica linhas
            }
            if (botoes[0][i].getText().equals(jogador) && botoes[1][i].getText().equals(jogador)
                    && botoes[2][i].getText().equals(jogador)) {
                return true; // Verifica colunas
            }
        }
        if (botoes[0][0].getText().equals(jogador) && botoes[1][1].getText().equals(jogador)
                && botoes[2][2].getText().equals(jogador)) {
            return true; // Verifica diagonal principal
        }
        if (botoes[0][2].getText().equals(jogador) && botoes[1][1].getText().equals(jogador)
                && botoes[2][0].getText().equals(jogador)) {
            return true; // Verifica diagonal secundária
        }
        return false;
    }

    // Método para reiniciar o jogo
    private void reiniciarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText(""); // Limpa os botões
            }
        }
        vezDoX = true;
        jogadasFeitas = 0;
    }
}
