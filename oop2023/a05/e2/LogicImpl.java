package a05.e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LogicImpl implements Logic {
    private final int size;
    private Position enemy;
    private Position player;

    public LogicImpl(int size) {
        this.size = size;
        Random random = new Random();
        this.enemy = new Position(random.nextInt(size), random.nextInt(size));
        this.player = new Position(random.nextInt(size), random.nextInt(size));
        if(neighbours(player, enemy)){
            throw new IllegalStateException();
        }
    }
    private boolean neighbours(Position p1, Position p2){
        return Math.abs(p1.x()-p2.x())<=1 && Math.abs(p1.y() - p2.y())<=1;
    }
    @Override
    public boolean hit(Position position) {
       if(neighbours(this.player, position)){
        this.player=position;
        return true;
       }
       return false;
    }

    @Override
    public void moveEnemy() {
        if (neighbours(player, enemy)) {
              var moves = new ArrayList<>(
                neighbours(this.enemy)
                    .filter(p -> !p.equals(this.player))
                    .filter(p -> !validNeighbours(this.player).anyMatch(p2 -> p2.equals(p)))
                    .toList());
            if (moves.size() > 0){
                Collections.shuffle(moves);
                this.enemy = moves.get(0);
            }
            
        }
    }

    @Override
    public Position getEnemy() {
        return this.enemy;
    }

    @Override
    public Position getPlayer() {
        return this.player;
    }

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

}
