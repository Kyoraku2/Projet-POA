package modele;

/**
 * CellWhite, non-playable cells
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellWhite extends Cell{

	//////Constructor //////

	/**
	 * Constructor of CellFinish
	 * The symbol is the same for all cells of same type
	 * 
	 */
	public CellWhite() {
		super(' ');
	}	
	
	//////Methods //////
	
	/**
	 * Allows to know is a cell is a playable one or not
	 * 
	 * @return false because the riders can't go on this cell
	 */
	public boolean isPlayable() {
		return false;
	}
	
}
