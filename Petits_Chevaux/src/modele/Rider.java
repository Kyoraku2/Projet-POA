package modele;

public class Rider {
	
	//Attributs
	private Position pos;
	private int inHole;
	private boolean river;
	private boolean hedge;
	private Couleur color;
	
	//Constructeur 
	
	public Rider(Position p,Couleur c) {
		pos=p;
		color=c;
		inHole=-1;
		river=false;
		hedge=false;
	}
	
	//Methods

	public Position getPos() {
		return pos;
	}
	
	public Couleur getColor() {
		return color;
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
	
	public void setRiver(boolean h) {
		hedge=h;
	}
	
	public void move(Position to) {
		pos=to;
	}
	
	

}
