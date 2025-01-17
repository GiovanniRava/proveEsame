package a01a.sol2;


public interface Logics{
	
	enum HitType {
		FIRST, SECOND
	}
	
	HitType hit(int x, int y);
	
	boolean isSelected(int x, int y);
	
	boolean isOver();
    
}
