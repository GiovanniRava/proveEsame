package a06.e2;

import javax.swing.*;

import a01a.sol2.LogicImpl;

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
        	this.logic.hit(this.cells.get(jb));
            redraw();
            if(this.logic.isOver()){
                cells.keySet().forEach(b->b.setEnabled(false));
            }
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
        this.redraw();
        this.setVisible(true);
    }
    
    private void redraw(){
        for (var entry : this.cells.entrySet()) {
            entry.getKey().setText(
                this.logic.temporary(entry.getValue()).map(String::valueOf).orElse("")
            );
            this.logic.found(entry.getValue()).ifPresent(n->{
                entry.getKey().setText(String.valueOf(n));
                entry.getKey().setEnabled(false);
            });
        }
    }
}
