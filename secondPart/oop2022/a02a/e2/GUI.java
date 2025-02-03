package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private final List<Position> marked = new LinkedList<>();
    public GUI(int size) { 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if(!isOver()){
                    if(hit(position)){
                        button.setText("B");
                        update(button);
                    }
                }else{
                    resetGame();
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
    private boolean isOver(){
        for (JButton button : cells.keySet()) {
            if(button.isEnabled() && button.getText().equals(" ")){
                return false;
            }
        }
        return true;
    }

    private boolean hit(Position pos){
        if(!this.marked.contains(pos)){
            this.marked.add(pos);
            return true;
        }
        return false;
    }
    private void update(JButton clickedButton) {
        Position pos = cells.get(clickedButton);
        if (pos == null) return; // Se il bottone non è nella mappa, esci
    
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            JButton button = entry.getKey();
            Position buttonPos = entry.getValue();
    
            
            if (Math.abs(buttonPos.x() - pos.x()) == Math.abs(buttonPos.y() - pos.y())) {
                button.setEnabled(false); 
            }
        }
    }
    private void resetGame(){
        for (JButton button : cells.keySet()) {
            button.setEnabled(true);
            button.setText(" ");    
            this.marked.clear();        
        }
        this.revalidate();
        this.repaint();
    }
    
}
