package modele;

public class Position {
	//Attributs
	
	private int col;
	private int row;
	
	//Constructeur 
	
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
