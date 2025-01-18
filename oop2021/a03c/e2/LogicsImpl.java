package a03c.e2;

import java.awt.image.DirectColorModel;
import java.util.*;

public class LogicsImpl implements Logics{
    private final int size;
    private final Set<Pair<Integer, Integer>> obstacles = new HashSet<>();
    private Set<Pair<Integer, Integer>> position = new HashSet<>();
    private Direction direction = Direction.UP;
    private Map<Pair<Integer, Integer>, Direction> directions = new HashMap<>();

    public LogicsImpl(int size) {
        this.size = size;
        final var random = new Random();
        for (int i=0 ; i<size ; i++){
            this.obstacles.add(new Pair<>(i, random.nextInt(size-2)));
            var p = new Pair<> (i, size);
            this.position.add(p);
            this.directions.put(p, Direction.UP);
        }
    }

    private static enum Direction{
        UP(0,-1), RIGHT(1,0), DOWN(0,1), LEFT(-1,0);

        int x;
        int y;
        private Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
        Direction next(){
            return Direction.values()[(this.ordinal()+1) % Direction.values().length];
        }
    }
    
    private Pair<Integer, Integer> nextPosition (Pair<Integer, Integer> p){
        return  new Pair<>(p.getX()+this.directions.get(p).x, p.getY()+this.directions.get(p).y);
    }

    @Override
    public Set<Pair<Integer, Integer>> next() {
        for (var p : this.position) {
            var next = nextPosition(p);
            if(next.getY()==size || this.obstacles.contains(next)){
                this.direction.put(p, this.directions.get(p).next().next());
                this.obstacles.remove(next);
                next = nextPosition(p);
            }
            this.directions.put(next, this.directions.remove(p));
        }
        this.position.clear();
        this.position.addAll(this.directions.keySet());
        return Collections.unmodifiableSet(this.position);
    }
    @Override
    public boolean obstacleAt(int x, int y) {
        return obstacles.contains(new Pair <> (x,y));
    }

    @Override
    public boolean isOver() {
        return this.position.stream().anyMatch(p->p.getY() == -1);
    }

}
