package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private List<Position> obstacles = new ArrayList();
    private Position umaPosition = new Position(0, 0);
    private final int size;
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
    
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);
        
    
        List<Position> borderCells = new ArrayList<>();
        Random rand = new Random();

        // Aggiungi le celle della cornice
        for (int x = 0; x < size; x++) {
            borderCells.add(new Position(x, 0));         // Prima riga
            borderCells.add(new Position(x, size - 1));  // Ultima riga
        }
        for (int y = 1; y < size - 1; y++) {
            borderCells.add(new Position(0, y));         // Prima colonna (escludi angoli)
            borderCells.add(new Position(size - 1, y));  // Ultima colonna (escludi angoli)
        }

        // Mescola le posizioni e ne sceglie 3 casualmente
        Collections.shuffle(borderCells);
        obstacles = borderCells.subList(0, 3);

        
        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var positon = cells.get(jb);
            if(!isOver()){
                if(obstacles.contains(hit())){
                    resetPosition();
                }else{
                    updateUmanPosition;
                }
            }else{
                System.exit(0);
            }
            
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                Position pos = new Position(i, j);
                if(this.obstacles.contains(pos)){
                    jb.setText("o");
                }
                if(this.umaPosition.equals(pos)){
                    jb.setText("*");
                }
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        setEnabled();
        this.setVisible(true);
    }
    private boolean isOver(){
        return this.obstacles.size()>0;
    }
    private Position hit (){
        boolean three = true;
        if(three){
            if(umaPosition.x()== 0 && umaPosition.y()>0 && umaPosition)
        }
    }
    private void setEnabled(){
        for (int i = 1; i < size - 1 ; i++){
            for (int j = 1; j< size - 1; j++){
                Position pos = new Position(i, j);
                for (var entry : cells.entrySet()) {
                         if(entry.getValue().equals(pos)){
                            entry.getKey().setEnabled(false);
                         }               
                }
            }
        }
    }
}

