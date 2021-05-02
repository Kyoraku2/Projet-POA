package modele;

/**
 * CellWhite, non-playable cells
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellWhite extends Cell{

	//Constructor 
	public CellWhite(Position p) {
		super(p,' ');
	}	
	
	//Methods
	public boolean isPlayable() {
		return false;
	}
	
}
