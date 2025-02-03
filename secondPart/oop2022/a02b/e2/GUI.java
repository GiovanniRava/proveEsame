package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private final List<Position> marked = new ArrayList<>();
    private boolean hasDisabledDiagonal = false;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton checkRestart = new JButton("Check > Restart");
        main.add(BorderLayout.SOUTH, checkRestart);
        checkRestart.addActionListener(e-> checkRestart());
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if(hit(position)){
                    button.setText(" ");
                    marked.remove(position);
                }else {
                    button.setText("*");
                    marked.add(position);
                }
            } 
        };

                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Position(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
    private boolean hit(Position pos){
        return this.marked.contains(pos);
    }
    private void checkRestart() {
        if (hasDisabledDiagonal) {
            resetGame();
        } else {
            List<Position> diagonal = getDiagonalPositions();
            if (diagonal != null) {
                disableButtonsOnDiagonal(diagonal);
                hasDisabledDiagonal = true;
            }
        }
    }

    private void resetGame() {
        for (JButton button : cells.keySet()) {
            button.setText(" ");
            button.setEnabled(true);
        }
        marked.clear();
        hasDisabledDiagonal = false;
    }

    /**
 * Trova una diagonale con almeno 3 posizioni nella lista marked.
 * Se esiste, restituisce la lista delle posizioni su quella diagonale.
 * Se non esiste, restituisce null.
 */
public List<Position> getDiagonalPositions() {
    List<Position> mainDiagonal = new ArrayList<>();
    Map<Integer, List<Position>> secondaryDiagonalGroups = new HashMap<>();

    for (Position p : marked) {
        int x = p.x();
        int y = p.y();

        if (x == y) {
            mainDiagonal.add(p);
        }

        int sum = x + y;
        secondaryDiagonalGroups.putIfAbsent(sum, new ArrayList<>());
        secondaryDiagonalGroups.get(sum).add(p);
    }

    // Se esiste una diagonale con almeno 3 elementi, la restituiamo
    if (mainDiagonal.size() >= 3) {
        return mainDiagonal;
    }

    for (List<Position> diagonal : secondaryDiagonalGroups.values()) {
        if (diagonal.size() >= 3) {
            return diagonal;
        }
    }

    return null; // Nessuna diagonale con almeno 3 elementi
}

/**
 * Disabilita i pulsanti che si trovano sulle posizioni della diagonale fornita.
 */
public void disableButtonsOnDiagonal(List<Position> diagonal) {
    if (diagonal == null) return; // Se non c'è una diagonale valida, esci

    for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
        if (diagonal.contains(entry.getValue())) {
            entry.getKey().setEnabled(false);
        }
    }
}

    
    
}
