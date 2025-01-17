package a02c.e2;

public interface Logics {
    boolean next();
    
    Pair<Integer, Integer> getNext();
    
    boolean isObstacle(int x, int y);
}
