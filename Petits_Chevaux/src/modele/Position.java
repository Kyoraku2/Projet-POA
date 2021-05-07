package modele;

/**
 * Position class
 * @author Marie-Almina
 * @author Tayeb
 */

public class Position {
	
	//////Attributes //////
	
	/**
	 * The number of column of the position
	 */	
	private int col;
	
	/**
	 * The number of row of the position
	 */
	private int row;
	

	////// Constructor //////
	
	/**
	 * Constructor of position
	 * 
	 * @param c The x-axis or column
	 * @param r The y-axis or row
	 */	
	public Position(int c,int r) {
		col=c;
		row=r;
	}
	
	////// Methods //////

	/**
	 * Getter of the column
	 * @return The number of the column
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Getter of the row
	 * @return The number of the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 *	Checks is the position is valid, 
	 *		if it's inside the board of height height and width width
	 * @param height The height of the board
	 * @param width	 The width of the board
	 * @return True if the position is valid false otherwise
	 */
	public boolean isValid(int height,int width) {
		return col>=0 && col<width && row>=0 && row<width;
	}
	
	/**
	 * Checks if a position is equal to another
	 * 
	 * @param pos The position to check with
	 * @return	True if the positions are the same, false otherwise
	 */
	public boolean equals(Position pos) {
		return this.col==pos.col && this.row==pos.row;
	}
	
	/**
	 * Allows to show the position,
	 * 	Used for development
	 * 
	 * @return A String made from the column and the row
	 */
	public String toString() {
		return "("+col+","+row+")";
	}
	
}
