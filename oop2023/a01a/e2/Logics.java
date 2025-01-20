package a01a.e2;
import java.util.*;
public interface Logics {
    Optional <Integer> hit (Position position);
    Optional <Integer> getMark(Position position);
    boolean isOver();
}
