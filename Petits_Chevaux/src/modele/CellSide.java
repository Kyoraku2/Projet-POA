package modele;

/**
 * CellSide, non-playable cells
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellSide extends Cell{

	//////Constructor //////

	/**
	 * Constructor of CellFinish
	 * The symbol is the same for all cells of same type
	 * 
	 * @param p The position of the cell
	 */
	public CellSide(Position p) {
		super(p,'+');
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
