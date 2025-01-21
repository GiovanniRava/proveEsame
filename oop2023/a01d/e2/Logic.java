package a01d.e2;
import java.util.*;
public interface Logic {
    Optional<Integer> hit(Position position);

    Optional<Integer> getMark(Position position);

    boolean isOver();
}
