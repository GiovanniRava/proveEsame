package a01b.e2;

public interface Logic {
    enum CS {
        MARKED, EMPTY;
    };
    boolean hit(Position position);
    boolean isOver();
}
