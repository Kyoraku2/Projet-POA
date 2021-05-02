package modele;

/**
 * Cell class, general representation of cells
 * @author Marie-Almina
 * @author Tayeb
 */

public abstract class Cell {
	
	//Attributes
	private Position pos;
	private char symbole;
	
	
	//Constructor
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
	abstract public boolean isPlayable();
}
