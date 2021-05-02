package modele;

/**
 * Rider class
 * @author Marie-Almina
 * @author Tayeb
 */

public class Rider {
	
	//Attributes
	private Position pos;
	private int inHole;
	private boolean river;
	private boolean hedge;
	private Couleur color;
	private Position start;
	
	//Constructor 
	
	public Rider(Position p, Position s,Couleur c) {
		pos=p;
		color=c;
		inHole=-1;
		river=false;
		hedge=false;
		start=s;
	}
	
	//Methods
	public Position getPos() {
		return pos;
	}
	
	public Couleur getColor() {
		return color;
	}
	
	public Position getStart() {
		return start;
	}
	
	public boolean inRiver() {
		return river;
	}
	
	public boolean inHedge() {
		return hedge;
	}
	
	public int inHole() {
		return inHole;
	}
	
	public void incrementHole() {
		++inHole;
	}
	
	public void resetHole() {
		inHole=-1;
	}
	
	public void setHedge(boolean h) {
		hedge=h;
	}
	
	public void setRiver(boolean r) {
		river=r;
	}
	
	public void move(Position to) {
		pos=to;
	}
	
	

}
