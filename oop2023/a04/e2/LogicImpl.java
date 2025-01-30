package a04.e2;

import java.util.*;

public class LogicImpl implements Logic{
    private final int size;
    private final Set<Position> marked = new HashSet<>();
    @Override
    public boolean hit(Position position) {
        if(this.marked.contains(position)){
            return false;
        }else{
            this.marked.add(position);
            return true;
        }
    
    }

    @Override
    public boolean isOver(Position first, Position last) {
        
    }

}
