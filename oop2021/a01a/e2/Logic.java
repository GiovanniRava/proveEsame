package a01a.e2;

public interface Logic {
    enum HitType{
        FIRST, SECOND
    }
    HitType hit(int x, int y);

    boolean isSelected(int x, int y);

    boolean isOver();
}
