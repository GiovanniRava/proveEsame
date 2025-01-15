package a01b.e2;

public interface Logics {
    enum HitType{
        FIRST, SECOND, THIRD, WRONG;
    }

    HitType hit(int x, int y);

    boolean isSelected(int x, int y);

}
