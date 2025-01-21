package a06.e2;

import java.util.*;

public interface Logic {

    boolean hit(Position position);

    Optional<Integer> found (Position p);

    Optional<Integer> temporary (Position p);

    boolean isOver();
}
