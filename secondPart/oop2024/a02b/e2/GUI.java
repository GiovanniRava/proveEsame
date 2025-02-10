package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private final Map<JButton, Position> cells = new HashMap<>();
    private Set<Position> selected = new HashSet<>();
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
            if (selected.size() < 5 && !selected.contains(position)) {
                selected.add(position);
                jb.setText("*");
                if (selected.size() == 5) {
                    showCounts();
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                Position pos = new Position(j, i);
                this.cells.put(jb, pos);
                if (isEnabledPosition(i, j)) {
                    jb.addActionListener(al);
                } else {
                    jb.setEnabled(false);
                }
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private boolean isEnabledPosition(int i, int j) {
        return j != size - 1 && i <= j;
    }

    private void showCounts() {
        Map<Integer, Integer> columnCounts = new HashMap<>();
        Map<Integer, Integer> rowCounts = new HashMap<>();
        
        for (Position pos : selected) {
            columnCounts.put(pos.x(), columnCounts.getOrDefault(pos.x(), 0) + 1);
            rowCounts.put(pos.y(), rowCounts.getOrDefault(pos.y(), 0) + 1);
        }
        
        for (var entry : cells.entrySet()) {
            Position pos = entry.getValue();
            JButton jb = entry.getKey();
            if (pos.x() == pos.y()) {
                jb.setText(String.valueOf(columnCounts.getOrDefault(pos.x(), 0)));
            }
            if (pos.x() == size - 1) {
                jb.setText(String.valueOf(rowCounts.getOrDefault(pos.y(), 0)));
            }
        }
    }
}
