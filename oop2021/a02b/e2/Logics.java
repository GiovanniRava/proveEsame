package a02b.e2;
import java.util.Optional;
public interface Logics {
    enum Direction{
        LEFT, RIGHT, UP;
    }
    
    void toNext();

    Pair<Integer, Integer> getNext();

    Optional<Boolean> changeDirectionToLeftAt(int x, int y);

    boolean isOver();
}
