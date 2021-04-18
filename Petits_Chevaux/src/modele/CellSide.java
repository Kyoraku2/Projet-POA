package modele;

public class CellSide extends Cell{

	//Constructeur 
	public CellSide(Position p) {
		super(p,'+');
	}	
	
	public boolean isPlayable() {
		return false;
	}
	
}
