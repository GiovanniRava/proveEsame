package a05.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    public GUI(int width) {
        this.logic = new LogicImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        
        JPanel panel = new JPanel(new GridLayout(width,width));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
        	if(this.logic.hit(this.cells.get(jb))){
                redraw();
                if(this.logic.isOver()){
                    this.cells.forEach((k, d)->k.setEnabled(false));
                }else {
                    this.logic.moveEnemy();
                    redraw();
                }
            };
        };
                
        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
            	var pos = new Position(j,i);
                final JButton jb = new JButton(pos.toString());
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        redraw();
        this.setVisible(true);
    }
    private void redraw(){
        for (var entry  : this.cells.entrySet()) {
            entry.getKey().setText(
                this.logic.getPlayer().equals(entry.getValue())? "P" : 
                this.logic.getEnemy().equals(entry.getValue())? "E" : " "
            );
            
        }
    }
    
}
