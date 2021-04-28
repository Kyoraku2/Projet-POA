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
	
	public void move(Position to) {
		pos=to;
	}
	
	

}
