package a02b.e2;

import java.util.function.Function;
import java.util.function.Predicate;

public record Position(int x, int y) {
    public Position delta(Position p){
        return new Position(x - p.x(), y - p.y());
    }

    public boolean holdInBoth(Predicate<Integer> pred){
        return pred.test(x) && pred.test(y);
    }

    public Position applyToBoth(Function<Integer, Integer> fun){
        return new Position(fun.apply(x), fun.apply(y));
    }

}
