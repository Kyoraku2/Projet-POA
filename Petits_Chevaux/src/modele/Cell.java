package modele;

/**
 * Cell class, general representation of cells
 * @author Marie-Almina
 * @author Tayeb
 */

public abstract class Cell {
	
	//////Attributes //////
	
	/**
	 * symbol of the cell
	 * used to know it's type and to print it on the board
	 */	
	private char symbol;
	
	
	//////Constructor //////
	
	/**
	 * Constructor of Cell
	 * 
	 * @param c The character used for it's symbol
	 */
	public Cell(char s) {
		symbol=s;
	}
	
	//////Methods //////

	/**
	 * Getter of the symbol of the cell
	 * 
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	
	/**
	 * Abstract function used to know is a cell is a playable one or not
	 * 
	 * @return true is it's playable false otherwise
	 */
	abstract public boolean isPlayable();
}
