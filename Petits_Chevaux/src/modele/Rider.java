package modele;

/**
 * Rider class
 * @author Marie-Almina
 * @author Tayeb
 */

public class Rider {
	
	////// Attributes //////
	
	/**
	 * The position of the rider (on the board)
	 */
	private Position pos;
	
	/**
	 * Counter of turns spend in a hole.
	 */
	private int inHole;
	
	/**
	 * Boolean allowing to know if the rider is stuck in a river, 
	 * True if is false otherwise
	 */
	private boolean river;
	
	/**
	 * Boolean allowing to know if the rider is stuck behind a hedge, 
	 * True if is false otherwise
	 */
	private boolean hedge;
	
	/**
	 * The color of the rider (to differentiate them)
	 */
	private Couleur color;
	
	/**
	 * The position where the rider starts
	 * 	(After leaving the stable)
	 */
	private Position start;
	
	/**
	 * The position where the rider ends
	 * 	(wins the game)
	 */
	private Position end;
	

	////// Constructor //////
	
	/**
	 * Constructor of Rider
	 * 
	 * @param p The position of the rider (At the beginning it's stable)
	 * @param s The position of the rider's starting point
	 * @param e The position of the rider's ending point
	 * @param c Color of the rider
	 */
	public Rider(Position p, Position s, Position e, Couleur c) {
		pos=p;
		color=c;
		inHole=-1;
		river=false;
		hedge=false;
		start=s;
		end=e;
	}
	

	////// Methods //////

	/**
	 * Getter of the position of the rider
	 * 
	 * @return The current position of the rider
	 */	
	public Position getPos() {
		return pos;
	}
	
	/**
	 * Getter of the color of the rider
	 * 
	 * @return The color of the rider
	 */	
	public Couleur getColor() {
		return color;
	}
	
	/**
	 * Getter of the start position of the rider
	 * 
	 * @return The start position
	 */	
	public Position getStart() {
		return start;
	}
	
	/**
	 * Getter of the end position of the rider
	 * 
	 * @return The end position
	 */	
	public Position getEnd() {
		return end;
	}
	
	/**
	 * Allows to know if the rider is in a river 
	 * 
	 * @return True if he is false otherwise
	 */	
	public boolean inRiver() {
		return river;
	}
	
	/**
	 * Allows to know if the rider is behind a hedge
	 * 
	 * @return True if he is false otherwise
	 */	
	public boolean inHedge() {
		return hedge;
	}
	
	/**
	 * Allow to know the number of turns spend in a hole
	 * 
	 * @return -1 if not in hole, the number spend otherwise
	 */
	public int inHole() {
		return inHole;
	}
	
	/**
	 * Increment the number of turns spent in a hole by one
	 */
	public void incrementHole() {
		++inHole;
	}
	
	/**
	 * Reset the number of turns spent in a hole to -1
	 */
	public void resetHole() {
		inHole=-1;
	}
	
	/**
	 * Sets the hedge to the right value
	 * 
	 * @param h The new value of hedge 
	 */
	public void setHedge(boolean h) {
		hedge=h;
	}
	
	/**
	 * Sets the river to the right value
	 * 
	 * @param r The new value of river 
	 */
	public void setRiver(boolean r) {
		river=r;
	}
	
	/**
	 * Moves the rider to a new position
	 * 
	 * @param to The destination of the rider
	 */
	public void move(Position to) {
		pos=to;
	}
	
	

}
