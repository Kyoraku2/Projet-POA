package modele;

/**
 * Position class
 * @author Marie-Almina
 * @author Tayeb
 */

public class Position {
	
	//Attributes
	private int col;
	private int row;
	
	//Constructor 
	public Position(int c,int r) {
		col=c;
		row=r;
	}
	
	//Methods
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public boolean isValid(int height,int width) {
		return col>=0 && col<width && row>=0 && row<width;
	}
	
	public boolean equals(Position pos) {
		return this.col==pos.col && this.row==pos.row;
	}
	
	public String toString() {
		return "("+col+","+row+")";
	}
	
}
