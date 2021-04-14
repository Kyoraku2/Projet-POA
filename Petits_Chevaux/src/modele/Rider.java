package modele;

public class Rider {
	
	//Attributs
	private Position pos;
	private Couleur color;
	
	//Constructeur 
	
	public Rider(Position p,Couleur c) {
		pos=p;
		color =c;
	}
	
	//Methods
	
	public void setPosition(Position p) {
		this.pos=p;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public Couleur getColor() {
		return color;
	}
	
	//A faire
	public void move(Position to) {
		
	}
	
	

}
