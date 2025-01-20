package a01c.e2;

import java.util.*;

public class LogicImpl implements Logic {
    private final int size;
    private List<Position> edges  = new LinkedList<>();
    public LogicImpl(int size) {
        this.size = size;
    }

    private Set<Position> marks = new HashSet<>();

    private boolean moving(){
        return this.marks.size() == 2;
    }

    private void moveMarks(){
        if(this.marks.size()==0){
            populateMarks();
        }else {
            extendMarks();
        }
    }
    private boolean neighbours(Position p1, Position p2){
        return Math.abs(p1.x()-p2.x())<=1 && Math.abs(p1.y()-p2.y())<=1;
    }
    private void extendMarks(){
        Set<Position> added = new HashSet<>();
        for(int i = 0; i<this.size; i++){
            for (int j=0; j<this.size; j++){
                var q = new Position(i, j);
                if(this.marks.stream().anyMatch(p->neighbours(p, q))){
                    added.add(q);
                }
            }
        }
    }
    private void populateMarks(){
        for (int i = Math.min(this.edges.get(0).x(), this.edges.get(1).x()); i <= Math.max(this.edges.get(0).x(), this.edges.get(1).x()); i++){
            for (int j = Math.min(this.edges.get(0).y(), this.edges.get(1).y()); j <= Math.max(this.edges.get(0).y(), this.edges.get(1).y()); j++){
                this.marks.add(new Position(i,j));
            }
        }
    }
    @Override
    public Optional<Integer> hit(Position position) {
        if(this.isOver()){
            return Optional.empty();
        }
        if(moving()){
            this.moveMarks();
            return Optional.empty();
        }
        if(this.edges.contains(position)){
            return Optional.of(this.edges.indexOf(position)+1);
        }
        this.edges.add(position);
        return Optional.of(this.edges.size());
    }

    @Override
    public Optional<Integer> getMark(Position position) {
        if(this.edges.contains(position)){
            return Optional.of(this.edges.indexOf(position)+1);
        }
        return Optional.of(0).filter(i-> this.marks.contains(position));
    }

    @Override
    public boolean isOver() {
        
        return this.marks.size() == this.size* this.size;
    }

}
