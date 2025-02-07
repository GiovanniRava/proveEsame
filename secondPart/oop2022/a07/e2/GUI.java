package a07.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private final List<Position> marked = new ArrayList<>();
    private final int size;
    private static enum Corner{
        UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT;
    }
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if(isOver()){
                    resetGame();
                }else{
                    if(areMoreMarked(position)){
                        setBlank(position);
                    }else{
                        setAsterisks(position);
                    }
                }
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                Position pos = new Position(i, j);
                final JButton jb = new JButton(" ");
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
    private List<Position> near(Position pos){
        List<Position> neighborhood = new ArrayList<>();
        if(this.isCorner(pos) == null){
            Position start = new Position(pos.x()-1, pos.y()-1);
            for(int i = start.x(); i < start.x()+3; i++){
                for(int j = start.y(); j<start.y()+3; j++){
                    Position current = new Position(i, j);
                    if(!this.cells.containsValue(current)){
                        continue;
                    }
                    neighborhood.add(current);
                }
            }
        }else if (this.isCorner(pos)!= null){
            switch (this.isCorner(pos)) {
                case Corner.UPRIGHT:
                    neighborhood.add(new Position(pos.x(), pos.y()+1));
                    neighborhood.add(new Position(pos.x()+1, pos.y()+1));
                    neighborhood.add(new Position(pos.x()+1, pos.y()));
                    break;
            
                case Corner.UPLEFT:
                    neighborhood.add(new Position(pos.x(), pos.y()-1));
                    neighborhood.add(new Position(pos.x()+1, pos.y()-1));
                    neighborhood.add(new Position(pos.x()+1, pos.y()));
                    break;
                case Corner.DOWNLEFT:
                    neighborhood.add(new Position(pos.x()-1, pos.y()));
                    neighborhood.add(new Position(pos.x()-1, pos.y()-1));
                    neighborhood.add(new Position(pos.x(), pos.y()-1));
                    break;
                case Corner.DOWNRIGHT:
                    neighborhood.add(new Position(pos.x(), pos.y()+1));
                    neighborhood.add(new Position(pos.x()-1, pos.y()+1));
                    neighborhood.add(new Position(pos.x()-1, pos.y()));
                    break;
            }
        }
        neighborhood.add(pos);
        return new ArrayList<>(neighborhood);
    }

    private Corner isCorner(Position pos){
        if((pos.x()==0 && pos.y()== 0)){
            return Corner.UPRIGHT;
        }
        if ((pos.x()==0 && pos.y() == size-1)) {
            return Corner.UPLEFT;
        }
        if((pos.x() == size -1  && pos.y() == 0)){
            return Corner.DOWNLEFT;
        }
        if ((pos.x()== size-1 && pos.y() == size-1)) {
            return Corner.DOWNRIGHT;
        }
        return null;
    }
    private boolean areMoreMarked(Position pos){
        List<Position> neighborhood = new ArrayList<>(near(pos));
        int areMarked = 0;
        int notMarked = 0;
        for (Position position : neighborhood) {
            if(this.marked.contains(position)){
                areMarked++;
            }else{
                notMarked++;
            }
        }
        return areMarked> notMarked;
    }
    private void setAsterisks(Position pos){
        List<Position> neighborhood = new ArrayList<>(near(pos));
        for (var entry : cells.entrySet()) {
            if(neighborhood.contains(entry.getValue())){
                entry.getKey().setText("*");
                this.marked.add(entry.getValue());
            }
        }
    }
    private void setBlank(Position pos){
        List<Position> neighborhood = new ArrayList<>(near(pos));
        for (var entry : cells.entrySet()) {
            if(neighborhood.contains(entry.getValue())){
                entry.getKey().setText("");
                if(this.marked.contains(entry.getValue())){
                    this.marked.remove(entry.getValue());
                }
            }
        }
    }
    private boolean isOver(){
        return this.marked.size()>(((size*size)/2)+1);
    }
    private void resetGame(){
        this.dispose();
        new GUI(size);
    }
    
}
