package a06.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private List<Position> one = new ArrayList<>();
    private List<Position> two = new ArrayList<>();
    private int size;
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton fire = new JButton("FIRE");
        main.add(BorderLayout.SOUTH, fire);
        ActionListener aL = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                fireCollapse(fire);
            }
        };
        fire.addActionListener(aL);
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
            }
        };
        
        
        Random random = new Random();
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(String.valueOf(random.nextInt(2)+1));
                if(jb.getText().equals("1")){
                    one.add(new Position(i, j));
                }else{
                    two.add(new Position(i, j));
                }
                this.cells.put(jb, new Position(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
    private void fireCollapse(JButton fire) {
        for (int col = 0; col < size; col++) {
            List<Integer> columnValues = new ArrayList<>();
            
            // Estrai i valori della colonna dal basso verso l'alto
            for (int row = size - 1; row >= 0; row--) {
                Position pos = new Position(row, col);
                JButton button = getButtonAt(pos);
                if (!button.getText().isEmpty()) {
                    columnValues.add(Integer.parseInt(button.getText()));
                }
            }
    
            // Cerca due celle consecutive con lo stesso valore e collassale
            for (int i = 0; i < columnValues.size() - 1; i++) {
                if (columnValues.get(i).equals(columnValues.get(i + 1))) {
                    int newValue = columnValues.get(i) * 2;
                    columnValues.set(i, newValue);
                    columnValues.remove(i + 1);
                    break; // Effettua un solo collasso per colonna
                }
            }
    
            // Riempie la colonna con i nuovi valori e lascia vuote le celle superiori
            for (int row = size - 1, index = 0; row >= 0; row--) {
                Position pos = new Position(row, col);
                JButton button = getButtonAt(pos);
                if (index < columnValues.size()) {
                    button.setText(String.valueOf(columnValues.get(index++)));
                } else {
                    button.setText("");
                }
            }
            fire.setEnabled(hasPossibleMerges());
        }
    }    
    private JButton getButtonAt(Position pos) {
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            if (entry.getValue().equals(pos)) {
                return entry.getKey();
            }
        }
        return null; // Se non viene trovato alcun pulsante (non dovrebbe succedere)
    }
    private boolean hasPossibleMerges() {
        for (int col = 0; col < size; col++) {
            for (int row = size - 2; row >= 0; row--) { // Dal basso, controllando coppie consecutive
                Position pos1 = new Position(row, col);
                Position pos2 = new Position(row + 1, col);
                
                JButton button1 = getButtonAt(pos1);
                JButton button2 = getButtonAt(pos2);
    
                if (!button1.getText().isEmpty() && !button2.getText().isEmpty()) {
                    int val1 = Integer.parseInt(button1.getText());
                    int val2 = Integer.parseInt(button2.getText());
                    
                    if (val1 == val2) {
                        return true; // Se esiste una coppia sommabile, ritorna true
                    }
                }
            }
        }
        return false; // Nessuna somma possibile
    }
    
}   
