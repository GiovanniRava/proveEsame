package a03b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private final List<Position> computerPositions = new ArrayList<>();
    private final static int COMPUTER_PIECES = 4;
    private final int size;
    
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);
        initComputerPositions();

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var pos = cells.get(button);

                if (!isPlayerPiece(pos)) {
                    return; // Ignora il click se la cella non contiene un "*"
                }

                // Controlla se può mangiare in alto a sinistra
                if (computerPositions.contains(new Position(pos.x() - 1, pos.y() - 1))) {
                    eat(new Position(pos.x() - 1, pos.y() - 1), pos);
                }
                // Controlla se può mangiare in alto a destra
                else if (computerPositions.contains(new Position(pos.x() - 1, pos.y() + 1))) {
                    eat(new Position(pos.x() - 1, pos.y() + 1), pos);
                }
                // Se non può mangiare, prova a muoversi in avanti
                else if (canMove(pos)) {
                    move(pos);
                }
                
                // Controlla se il gioco è finito
                checkGameEnd();
            }
        };
                
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                Position pos = new Position(i, j);

                if (i == (size - 1)) { 
                    jb.setText("*"); // Pedoni del giocatore nell'ultima riga
                }
                if (this.computerPositions.contains(pos)) {
                    jb.setText("o"); // Pedoni del computer
                }

                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void move(Position pos) {
        Position newPos = new Position(pos.x() - 1, pos.y()); // Sposta in alto
        updateBoard(pos, newPos);
    }

    private void eat(Position enemyPos, Position playerPos) {
        computerPositions.remove(enemyPos); // Rimuove il pedone del computer
        updateBoard(playerPos, enemyPos);
    }

    private void updateBoard(Position oldPos, Position newPos) {
        for (var entry : cells.entrySet()) {
            if (entry.getValue().equals(oldPos)) {
                entry.getKey().setText(" "); // Rimuove "*"
            }
            if (entry.getValue().equals(newPos)) {
                entry.getKey().setText("*"); // Aggiunge "*"
            }
        }
    }

    private void initComputerPositions() {
        Random random = new Random();
        while (computerPositions.size() < COMPUTER_PIECES) {
            int x = random.nextInt(2); // Prima o seconda riga
            int y = random.nextInt(size); // Colonna casuale

            Position pos = new Position(x, y);

            if (!this.computerPositions.contains(pos)) {
                this.computerPositions.add(pos);
            }
        }
    }

    private boolean canMove(Position pos) {
        if (pos.x() - 1 < 0) {
            return false; // Non può muoversi fuori dalla griglia
        }
        return !computerPositions.contains(new Position(pos.x() - 1, pos.y())); // Deve avere la cella libera
    }

    private boolean isPlayerPiece(Position pos) {
        for (var entry : cells.entrySet()) {
            if (entry.getValue().equals(pos) && "*".equals(entry.getKey().getText())) {
                return true;
            }
        }
        return false;
    }

    private void checkGameEnd() {
        if (computerPositions.isEmpty()) {
            showWinMessage("Hai vinto!");
            resetGame();
        }
    }

    private void showWinMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetGame() {
        this.dispose(); // Chiude la finestra attuale
        new GUI(size); // Crea una nuova istanza della GUI
    }
}
