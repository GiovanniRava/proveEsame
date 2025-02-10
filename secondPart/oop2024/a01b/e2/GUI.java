package a01b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private Position first;
    private Position second;
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
            if (first == null) {
                first = position;
                jb.setText("*");
            } else {
                if (isValidMove(position)) {
                    second = position;
                    jb.setText("*");
                    drawRhombus();
                    resetSelection();
                } else {
                    resetSelection();
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                this.cells.put(jb, new Position(j, i)); // Usa il record Position
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private boolean isValidMove(Position pos) {
        // Verifica che le due posizioni siano sulla stessa colonna (y) e che la distanza sia multiplo di 2
        return first.y() == pos.y() && Math.abs(first.x() - pos.x()) % 2 == 0 && !pos.equals(first);
    }

    private void drawRhombus() {
        int minX = Math.min(first.x(), second.x());
        int maxX = Math.max(first.x(), second.x());
        int y = first.y();

        for (int x = minX; x <= maxX; x++) {
            int offset = x - minX;
            if (offset <= (maxX - minX) / 2) {
                setCellText(new Position(x, y - offset), "o");
                setCellText(new Position(x, y + offset), "o");
            } else {
                setCellText(new Position(x, y - (maxX - minX - offset)), "o");
                setCellText(new Position(x, y + (maxX - minX - offset)), "o");
            }
        }
    }

    private void setCellText(Position pos, String text) {
        for (var entry : cells.entrySet()) {
            if (entry.getValue().equals(pos)) {
                entry.getKey().setText(text);
                break;
            }
        }
    }

    private void resetSelection() {
        for (var entry : cells.entrySet()) {
            entry.getKey().setText("");
        }
        first = null;
        second = null;
    }
}