package modele;

import java.util.LinkedList;
import java.util.List;

public class Game {
	
	//Attributs
	private List<Rider> riders;
	private Board board;
	
	//Constructeurs
	
	//celui de base
	public Game() {
		this(16);
	}
	
	//celui bonus taille
	public Game(int size) {
		board=new Board(size);
		riders= new LinkedList<Rider>();
		riders.add(new Rider(new Position(1,0),Couleur.RED));
		riders.add(new Rider(new Position(board.getCols()-2,board.getRows()-1),Couleur.BLUE));
	}
	
	//Methods
	
	//A faire
	public void play(int first) {
		
	}
	
	//A faire
	public void turn(Rider p1, Rider p2){
		
	}
	
	//A faire
	public String toString() {
		return "";
	}
	
	
	
}
