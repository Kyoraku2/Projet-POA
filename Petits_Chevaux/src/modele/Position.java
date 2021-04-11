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
	
	//A faire
	public int getCol() {
		return col;
	}
	
	//A faire
	public int getRow() {
		return row;
	}
	
	//A faire
	public boolean isValid() {
		return true;
	}
	
	public boolean equals(Position pos) {
		return this.col==pos.col && this.row==pos.row;
	}
	
	//A faire
	public String toString() {
		return "";
	}
	
}
