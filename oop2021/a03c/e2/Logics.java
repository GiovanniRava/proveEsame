package a03c.e2;

import java.util.Set;

public interface Logics {

    Set<Pair<Integer, Integer>> next();
    
    boolean obstacleAt(int x, int y);

    boolean isOver();

}
