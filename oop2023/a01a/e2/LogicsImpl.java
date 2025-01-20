package a01a.e2;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics{
    private final int size;
    private List<Position> mark = new LinkedList<>();
    private boolean moving = false;

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public Optional<Integer> hit(Position position) {
        if(this.isOver()){
            System.exit(0);
        }
        if(this.moving || startMoving(position)){
            this.moving = false;
            this.moveMarks();
            return Optional.empty();
        }
        this.mark.add(position);
        return Optional.of(this.mark.size());
    }
    private boolean neighbours(Position p1, Position p2){
        return Math.abs(p1.x()-p2.x())<=1 && Math.abs(p1.y()-p2.y())<=1;
    }
    private boolean startMoving(Position position){
        return this.mark.stream().anyMatch(p->neighbours(p, position));
    }
    private void moveMarks(){
        this.mark = this.mark
        .stream()
        .map(p-> new Position(p.x()+1, p.y()-1))
        .collect(Collectors.toCollection(LinkedList:: new));
    }

    @Override
    public Optional<Integer> getMark(Position position) {
       return Optional.of(this.mark.indexOf(position)).filter(i-> i>=0).map(i->i+1);
    }

    @Override
    public boolean isOver() {
       return this.mark.stream().anyMatch(p->p.x() == this.size || p.y() == -1);
    }

}
