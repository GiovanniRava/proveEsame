package a02a.e2;

import java.util.*;
import java.util.function.Function;

public class LogicImpl implements Logic{
    
    private List<Position> dynamic = new LinkedList<>();

    private boolean isStatic(Position position){
        return position.x() % 2 == 1 || position.y() %2 == 1;
    }
    
    private boolean isDynamic(Position position){
        return this.dynamic.contains(position);
    }
    @Override
    public boolean hit(Position position) {
      if(this.isStatic(position) && !this.isDynamic(position)){
        this.dynamic.add(position);
        return true;
      }
      return false;
    }
    private boolean overRelationSepareted(List<Position> positions, Function <Position, Integer> projection){
        var list = positions.stream().map(projection).distinct().sorted().toList();
        return list.size() == 2 && list.get(0).equals(list.get(1)-2);
    };
    private boolean overRelation(List<Position> positions){
        return overRelationSepareted(positions, Position::x) && overRelationSepareted(positions, Position::y);
    }

    @Override
    public CellType getMark(Position position) {
        return this.isStatic(position) ? CellType.STATIC : this.isDynamic(position)? CellType.DYNAMIC : CellType.EMPTY;
    }

    @Override
    public boolean isOver() {
        return this.dynamic.size()<=4 &&
        this.overRelation(this.dynamic.subList(this.dynamic.size()-4, this.dynamic.size()));
    }

}
