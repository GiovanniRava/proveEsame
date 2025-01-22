package a05.e2;

public interface Logic {

    boolean hit(Position position);

    void moveEnemy();

    Position getEnemy();

    Position getPlayer();

    boolean isOver();

}
