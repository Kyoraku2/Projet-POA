package modele;

/**
 * CellSide, non-playable cells
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellSide extends Cell{

	//////Constructor //////

	/**
	 * Constructor of CellSide
	 * The symbol is the same for all cells of same type
	 * 
	 */
	public CellSide() {
		super('+');
	}	
	
	//////Methods //////
	
	/**
	 * Allows to know is a cell is a playable one or not
	 * 
	 * @return False because the riders can't go on this cell
	 */
	public boolean isPlayable() {
		return false;
	}
	
}
