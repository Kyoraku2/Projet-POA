package modele;

/**
 * CellSide, non-playable cells
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellSide extends Cell{

	//Constructor 
	public CellSide(Position p) {
		super(p,'+');
	}	
	
	//Methods
	public boolean isPlayable() {
		return false;
	}
	
}
