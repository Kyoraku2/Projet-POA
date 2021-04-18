package modele;

public abstract class CellPlayable extends Cell implements Questionnable{
	
	//Attributs
	private boolean occuped;
	
	//Constructeur
	public CellPlayable(Position p,char s) {
		super(p,s);
		occuped=false;
	}
	
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
