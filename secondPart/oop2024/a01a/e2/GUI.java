package a01a.e2;

import javax.swing.*;
import java.util.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private final Set<Position> marked = new HashSet<>();
    private final int size;
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
    
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var position = cells.get(jb);
            if(isValidMove(position)){
                if(this.marked.contains(position)){
                    showRectangle();
                    //resetGame();
                }else{
                    jb.setText("*");
                    this.marked.add(position);
                }
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
    private boolean isValidMove(Position pos){
        if(pos.x()== 0 || pos.x()== size-1 || pos.y()==0 || pos.y() == size -1){
            return false;
        }
        return true;
    }
    private void showRectangle(){
        int minX = size;
        int minY = size;
        int maxY = 0;
        int maxX = 0;
        for (Position position : marked) {
            if(minX > position.x()){
                minX = position.x();
            }
            if(minY > position.y()){
                minY = position.y();
            }
            if(maxX<position.x()){
                maxX = position.x();
            }
            if(maxY < position.y()){
                maxY = position.y();
            }
        }
        for (var entry : cells.entrySet()) {
            if(entry.getValue().x() == minX - 1 && entry.getValue().y() <= maxY + 1 && entry.getValue().y() >= minY - 1){
                entry.getKey().setText("o");
            }
            if(entry.getValue().x() == maxX + 1 && entry.getValue().y() <= maxY + 1 && entry.getValue().y() >= minY - 1){
                entry.getKey().setText("o");
            }
            if(entry.getValue().y() == minY - 1 && entry.getValue().x() <= maxX + 1 && entry.getValue().x() >= minX - 1){
                entry.getKey().setText("o");
            }
            if(entry.getValue().y() == maxY + 1 && entry.getValue().x() <= maxX + 1 && entry.getValue().x() >= minX - 1){
                entry.getKey().setText("o");
            }
            if(entry.getValue().x() == minX -1 && entry.getValue().y() == minY - 1){
                entry.getKey().setText("1");
            }
            if(entry.getValue().x() == minX -1 && entry.getValue().y() == maxY + 1){
                entry.getKey().setText("2");
            }
            if(entry.getValue().x() == maxX + 1 && entry.getValue().y() == maxY + 1){
                entry.getKey().setText("3");
            }
            if(entry.getValue().x() == maxX + 1 && entry.getValue().y() == minY - 1){
                entry.getKey().setText("4");
            }
            
        }
    }
    private void showMessage(){
        JOptionPane.showMessageDialog(null, "Rectangle", "Arrosto", JOptionPane.INFORMATION_MESSAGE);
    }
    private void resetGame(){
        this.dispose();
        new GUI(size);
    }
}
