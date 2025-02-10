package a01c.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private Position first = null;
    private Position second = null;
    private Position third = null;
    private Position fourth = null;
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
    
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var position = cells.get(jb);
            if(areVerticesSet()){
                showRectangle();
            }else{
                setPosition(position);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                this.cells.put(jb, new Position(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    private boolean areVerticesSet(){
        return first!= null && second != null && third!= null && fourth != null;
    }
    private void setPosition(Position pos){
        if(first == null){
            first = pos;
            setNumber(pos, "1");
        }else if(second == null && first.x() == pos.x()){
            second = pos;
            setNumber(pos, "2");
        }else if(third == null && second.y() == pos.y()){
            third = pos;
            setNumber(pos, "3");
        }else if(fourth == null && third.x() == pos.x()){
            fourth = pos;
            setNumber(pos, "4");
        }    
    }
    private void setNumber(Position pos, String number){
        for (var entry : cells.entrySet()) {
            if(entry.getValue().equals(pos)){
                entry.getKey().setText(number);
            }
            
        }
    }
    private void showRectangle(){
        for (int i = first.x() + 1 ; i <= fourth.x()-1 ; i++){
            for(int j = first.y()+1; j<=second.y()-1 ; j++){
                Position pos = new Position(i, j);
                setNumber(pos, "*");
            }
        }
    }
}