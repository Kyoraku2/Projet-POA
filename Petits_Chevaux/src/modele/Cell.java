package modele;

/**
 * Cell class, general representation of cells
 * @author Marie-Almina
 * @author Tayeb
 */

public abstract class Cell {
	
	//////Attributes //////
	
	/**
	 * Symbol of the cell,
	 * Used to know it's type and to print it on the board
	 */	
	private char symbol;
	
	
	//////Constructor //////
	
	/**
	 * Constructor of Cell
	 * 
	 * @param s The character used for it's symbol
	 */
	public Cell(char s) {
		symbol=s;
	}
	
	//////Methods //////

	/**
	 * Getter of the symbol of the cell
	 * 
	 * @return The symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	
	/**
	 * Abstract function used to know if a cell is a playable one or not
	 * 
	 * @return True if it's playable false otherwise
	 */
	abstract public boolean isPlayable();
}
