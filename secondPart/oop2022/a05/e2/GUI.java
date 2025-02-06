package a05.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Set<Position> activeButton = new HashSet<>();
    private Position umanPosition;
    private Position computerPosition;
    private final int size;
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        Random random = new Random();
        // Popola la mappa dei pulsanti attivi
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Position pos = new Position(i, j);
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    // Pulsante disabilitato, non lo aggiungiamo ad activeButton
                } else {
                    activeButton.add(pos); // Solo pulsanti attivi
                }
            }
        }

        do{
            umanPosition = new Position(size-1, random.nextInt(size));
        }while(!activeButton.contains(umanPosition));
        
        do {
            computerPosition = new Position(random.nextInt(2), random.nextInt(size));
        } while (!activeButton.contains(computerPosition));

        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if(isValidMove(position)){
                    if(checkUmanWin(position)){
                        System.out.println("Vittoria");
                        resetGame();
                    }else{
                        updateUmanPosition(position);
                        moveComputer();
                    }
                }
                
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                Position pos = new Position(i, j);
                if((!activeButton.contains(pos))){
                    jb.setEnabled(false);
                }

                if(i== umanPosition.x() && j == umanPosition.y()){
                    jb.setText("H");
                }

                if(i == computerPosition.x() && j == computerPosition.y()){
                    jb.setText("C");
                }
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    

    private boolean isValidMove(Position pos){
        if(pos.x() == umanPosition.x()-1 && (pos.y() == umanPosition.y()-1 || pos.y() == umanPosition.y()+1)){
            return true;
        }
        return false;
    }

    private boolean checkUmanWin(Position pos){
        if(computerPosition.equals(pos) || pos.x() == 0){
            return true;
        }
        return false;
    }

    private void resetGame(){
        this.dispose();
        new GUI(size);
    }

    private void updateUmanPosition(Position pos){
        for (var entry : cells.entrySet()) {
            if(entry.getValue().equals(pos)){
                entry.getKey().setText("H");
            }
            if(entry.getValue().equals(umanPosition)){
                entry.getKey().setText(" ");
            }
            
        }
        umanPosition = pos;
    }

    private void moveComputer() {
        Random random = new Random();
        Position possiblePosition1 = new Position(computerPosition.x() + 1, computerPosition.y() - 1);
        Position possiblePosition2 = new Position(computerPosition.x() + 1, computerPosition.y() + 1);

        // Se il pedone del computer può catturare il giocatore, termina la partita
        if (umanPosition.equals(possiblePosition2) || umanPosition.equals(possiblePosition1)) {
            System.out.println("Sconfitta :(");
            System.exit(0);
        }

        List<Position> validMoves = new ArrayList<>();
        
        // Controlla che possiblePosition1 sia dentro la griglia e sia un pulsante attivo
        if (isValidComputerMove(possiblePosition1)) {
            validMoves.add(possiblePosition1);
        }

        // Controlla che possiblePosition2 sia dentro la griglia e sia un pulsante attivo
        if (isValidComputerMove(possiblePosition2)) {
            validMoves.add(possiblePosition2);
        }

        // Se ci sono mosse valide, sceglie una posizione casuale tra quelle disponibili
        if (!validMoves.isEmpty()) {
            Position newComputerPosition = validMoves.get(random.nextInt(validMoves.size()));

            // Aggiorna la GUI con la nuova posizione del computer
            for (var entry : cells.entrySet()) {
                if (entry.getValue().equals(computerPosition)) {
                    entry.getKey().setText(" "); // Cancella la vecchia posizione
                }
                if (entry.getValue().equals(newComputerPosition)) {
                    entry.getKey().setText("C"); // Imposta la nuova posizione
                }
            }
            computerPosition = newComputerPosition;
        }
    }
    private boolean isValidComputerMove(Position pos) {
        return pos.x() >= 0 && pos.x() < size && pos.y() >= 0 && pos.y() < size && activeButton.contains(pos);
    }
}

