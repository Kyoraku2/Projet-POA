package modele;

/**
 * CellPlayable for cells where riders can go on
 * @author Marie-Almina
 * @author Tayeb
 */

public abstract class CellPlayable extends Cell implements Questionnable{
	
	//////Constructor //////
	
	/**
	 * Constructor of Cell
	 * 
	 * @param c The character used for it's symbol
	 */
	public CellPlayable(char s) {
		super(s);
	}
	
	
	///////Methods //////

	/**
	 * Allows to know is a cell is a playable one or not
	 * 
	 * @return true because all cell extending CellPlayables are playable
	 */
	public boolean isPlayable() {
		return true;
	}

	
}
