package a04.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private Position umanPosition;
    private Position computerPosition;
    private final int size;
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        Random random = new Random();
        do{
            umanPosition = new Position(random.nextInt(size), random.nextInt(size));
            computerPosition = new Position(random.nextInt(size), random.nextInt(size));
        }while(umanPosition.equals(computerPosition));
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if(inLine(position)){
                    if(checkHumanWin(position)){
                    System.out.println("Vittoria!!");
                    resetGame();
                }else{
                    updateUmanPosition(position);
                    updateComputerPosition();
                }
                }
                
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                if(i == umanPosition.x() && j == umanPosition.y()){
                    jb.setText("R");
                }
                if(i == computerPosition.x() && j == computerPosition.y()){
                    jb.setText("K");
                }
                this.cells.put(jb, new Position(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    private boolean inLine(Position pos){
        return pos.x() == umanPosition.x() || pos.y() == umanPosition.y();
    }
    private boolean checkHumanWin (Position pos){
        if (umanPosition.x() == pos.x()) { // Movimento verticale
            int minY = Math.min(umanPosition.y(), pos.y());
            int maxY = Math.max(umanPosition.y(), pos.y());
            return (computerPosition.x() == umanPosition.x() && computerPosition.y() >= minY && computerPosition.y() <= maxY);
        } else if (umanPosition.y() == pos.y()) { // Movimento orizzontale
            int minX = Math.min(umanPosition.x(), pos.x());
            int maxX = Math.max(umanPosition.x(), pos.x());
            return (computerPosition.y() == umanPosition.y() && computerPosition.x() >= minX && computerPosition.x() <= maxX);
        }
        return false; // Movimento non valido
    }
    private void updateUmanPosition(Position pos){
        for (var entry : cells.entrySet()) {
            if(entry.getValue().equals(pos)){
                entry.getKey().setText("R");
            }
            if(entry.getValue().equals(umanPosition)){
                entry.getKey().setText(" ");
            }
        }
        umanPosition = pos;
    }
    private boolean isNear() {
        int dx = Math.abs(computerPosition.x() - umanPosition.x());
        int dy = Math.abs(computerPosition.y() - umanPosition.y());
    
        return dx <= 1 && dy <= 1; // Controlla se la distanza in entrambe le direzioni è ≤ 1
    }
    private void updateComputerPosition(){
        Position newComputerPosition;
        if(isNear()){
            System.exit(0);
        }
    
        Random random = new Random();
        do{
            newComputerPosition = new Position(computerPosition.x() - (random.nextInt(3)-1), computerPosition.y()-(random.nextInt(3)-1));
        }while(computerPosition.equals(newComputerPosition) || newComputerPosition.x() < 0 || newComputerPosition.y()<0);
            
        for (var entry : cells.entrySet()) {
            if(entry.getValue().equals(newComputerPosition)){
                entry.getKey().setText("K");
            }
            if(entry.getValue().equals(computerPosition)){
                entry.getKey().setText(" ");
            }
            
        }
        computerPosition = newComputerPosition;
    }
    private void resetGame(){
        this.dispose();
        new GUI(size);
    }
}
