package a03a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private int counter = 0;
    private Logics logics;
    
    public GUI(int size) {
        this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button =(JButton)e.getSource();
            var position = cells.get(button);
            logics.hit(position.getX(), position.getY());
            boolean b = logics.isOver();
            for (var entry  : cells.entrySet()) {
                if(logics.isSelected(entry.getValue().getX(), entry.getValue().getY())){
                    entry.getKey().setText("*");
                }
                if (b){
                    entry.getKey().setEnabled(false);
                }
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
