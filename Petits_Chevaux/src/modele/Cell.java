package modele;

/**
 * Cell class, general representation of cells
 * @author Marie-Almina
 * @author Tayeb
 */

public abstract class Cell {
	
	//////Attributes //////
	
	/**
	 * position of the cell
	 */	
	private Position pos;
	/**
	 * symbol of the cell
	 * used to know it's type and to print it on the board
	 */	
	private char symbol;
	
	
	//////Constructor //////
	
	/**
	 * Constructor of Cell
	 * 
	 * @param p The position of the cell
	 * @param c The character used for it's symbol
	 */
	public Cell(Position p, char s) {
		pos=p;
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
	 * Getter of the position of the cell
	 * 
	 * @return the position
	 */
	public Position getPosition() {
		return pos;
	}
	
	/**
	 * Abstract function used to know is a cell is a playable one or not
	 * 
	 * @return true is it's playable false otherwise
	 */
	abstract public boolean isPlayable();
}
