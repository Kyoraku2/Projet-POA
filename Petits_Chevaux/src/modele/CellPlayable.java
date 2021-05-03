package modele;

/**
 * CellPlayable for cells where riders can go on
 * @author Marie-Almina
 * @author Tayeb
 */

public abstract class CellPlayable extends Cell implements Questionnable{
	
	//////Attributes //////
	
	/**
	 * Boolean allowing to know if a player is on this cell
	 */	
	private boolean occuped;
	
	
	//////Constructor //////
	
	/**
	 * Constructor of Cell
	 * Sets occuped to false
	 * 
	 * @param p The position of the cell
	 * @param c The character used for it's symbol
	 */
	public CellPlayable(Position p,char s) {
		super(p,s);
		occuped=false;
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
	
	/**
	 * Set occuped to true to show that a player is on this cell
	 * 
	 */
	public void becomeOccuped() {
		occuped=true;
	}
	
	/**
	 * Set occuped to false to show that there is no player on that cell
	 * 
	 */
	public void unOccuped() {
		occuped=false;
	}
	
}
