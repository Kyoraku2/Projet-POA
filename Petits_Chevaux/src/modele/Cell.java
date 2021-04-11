package modele;

public abstract class Cell {
	
	//Attributs
	private Position pos;
	private char symbole;
	
	
	//Constructeurs
	public Cell(Position p, char s) {
		pos=p;
		symbole=s;
	}
	
	//Methods
	public char getSymbole() {
		return symbole;
	}
	public Position getPosition() {
		return pos;
	}
}
