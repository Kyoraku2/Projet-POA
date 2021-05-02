package modele;

/**
 * CellPlayable for cells where riders can go on
 * @author Marie-Almina
 * @author Tayeb
 */

public abstract class CellPlayable extends Cell implements Questionnable{
	
	//Attributes
	private boolean occuped;
	
	//Constructor
	public CellPlayable(Position p,char s) {
		super(p,s);
		occuped=false;
	}

	//Methods
	public boolean isPlayable() {
		return true;
	}
	
	public void becomeOccuped() {
		occuped=true;
	}
	
	public void unOccuped() {
		occuped=true;
	}
	
}
