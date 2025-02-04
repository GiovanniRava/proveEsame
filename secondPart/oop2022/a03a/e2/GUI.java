package a03a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private Position umanPosition;
    private Position computerPosition;
    private final int size; // Variabile di istanza per la dimensione della griglia
    
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        
        Random random = new Random();

        // Genera posizioni iniziali per umano e computer senza sovrapposizione
        do {
            umanPosition = new Position(random.nextInt(size), random.nextInt(size));
            computerPosition = new Position(random.nextInt(size), random.nextInt(size));
        } while (umanPosition.equals(computerPosition)); // Evita sovrapposizione iniziale
        
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var umanMove = cells.get(button);
                
                if (isValidMove(umanMove)) { 
                    if (checkHumanWin(umanMove)) {
                        showWinMessage("Uman Wins");
                        resetGame();
                    } else {
                        moveUserTower(umanMove);
                        updateComputerPosition();
                    }
                }
            }
        };
                
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                if (i == umanPosition.x() && j == umanPosition.y()) {
                    jb.setText("*");
                } else if (i == computerPosition.x() && j == computerPosition.y()) {
                    jb.setText("o");
                }
                this.cells.put(jb, new Position(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    

    private boolean isValidMove(Position pos) {
        return pos.x() == umanPosition.x() || pos.y() == umanPosition.y();
    }

    private boolean checkHumanWin(Position pos) {
        if (umanPosition.x() == pos.x()) { // Movimento verticale
            int minY = Math.min(umanPosition.y(), pos.y());
            int maxY = Math.max(umanPosition.y(), pos.y());
            return (computerPosition.x() == umanPosition.x() && computerPosition.y() > minY && computerPosition.y() < maxY);
        } else if (umanPosition.y() == pos.y()) { // Movimento orizzontale
            int minX = Math.min(umanPosition.x(), pos.x());
            int maxX = Math.max(umanPosition.x(), pos.x());
            return (computerPosition.y() == umanPosition.y() && computerPosition.x() > minX && computerPosition.x() < maxX);
        }
        return false; // Movimento non valido
    }

    private void showWinMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private void moveUserTower(Position newPosition) {
        for (var entry : cells.entrySet()) {
            if (entry.getValue().equals(umanPosition)) {
                entry.getKey().setText(" "); // Rimuove "*"
            }
        }
        for (var entry : cells.entrySet()) {
            if (entry.getValue().equals(newPosition)) {
                entry.getKey().setText("*"); // Imposta la nuova posizione "*"
            }
        }
        umanPosition = newPosition; // Aggiorna la posizione dell'umano
    }

    private void updateComputerPosition() {
        if (computerPosition.x() == umanPosition.x() || computerPosition.y() == umanPosition.y()) {
            showWinMessage("Computer Wins");
            return;
        }

        Random random = new Random();
        Position newComputerPos;
        
        do {
            newComputerPos = new Position(random.nextInt(size), random.nextInt(size));
        } while (newComputerPos.equals(umanPosition) || newComputerPos.equals(computerPosition)); // Evita sovrapposizioni

        moveComputerTower(newComputerPos);
    }

    private void moveComputerTower(Position newPosition) {
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            if (entry.getValue().equals(computerPosition)) {
                entry.getKey().setText(" "); // Rimuove "o"
            }
        }
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            if (entry.getValue().equals(newPosition)) {
                entry.getKey().setText("o"); // Imposta la nuova posizione "o"
            }
        }
        computerPosition = newPosition; // Aggiorna la posizione del computer
    }

    private void resetGame() {
        this.dispose(); // Chiude la finestra
        new GUI(size);  // Crea una nuova istanza della GUI per riavviare il gioco
    }
}
