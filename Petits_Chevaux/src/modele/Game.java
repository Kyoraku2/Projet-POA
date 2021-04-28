package modele;

import java.util.LinkedList;
import java.util.List;

public class Game {
	
	//Attributs
	private List<Rider> riders;
	private Board board;
	private De de;
	
	//Constructeurs
	
	//celui de base
	public Game() {
		this(16);
	}
	
	//celui bonus taille
	public Game(int size) {
		de=new De();
		board=new Board(size);
		riders=new LinkedList<Rider>();
		riders.add(new Rider(new Position(1,0),Couleur.RED));
		riders.add(new Rider(new Position(board.getCols()-2,board.getRows()-1),Couleur.BLUE));
	}
	
	//Methods
	
	//A faire
	public void play(int first) {
		//boucle de jeu (while pas arrivé, on alterne le tour)
		/*
		boolean fini=false
		while(!fini) {
			if(first==1){
				//r1 rouge, r2 bleu
				fini=turn(r1,r2)
				Si fini
					end(r1)
					break;
				fini=turn(r2,r1)
				Si fini
					end(r2)
			}else{
				fini=turn(r2,r1)
				Si fini
					end(r1)
					break;
				fini=turn(r1,r2)
				Si fini
					end(r2)
			}
		}*/
	}
	
	//A faire
	public void go(Rider r1, Rider r2, int de) {
		//board.followPath
		//			Si collision à l'arrivée then yeet r2
		//			Sinon r juste occuped
		//			call Process
	}
	
	//A faire
	public void goStart(Rider r) {
		//place sur le pos de départ
	}
	
	//A faire (utiliser getCellType avec pos départ)
	public boolean turn(Rider r1, Rider r2){
		//Roule un dé
		//Check pos départ
		//Si hole : 
		//		process(){
		//		increment 
		//		Si ==3 : =-1
		//		}
		//      
		//Si haie : 
		//		Si de impaire : go(r1,r2,de.getValue())
		//Si River :
		//		Si de paire : go(r1,r2,de.getValue())	
		//Si Free :
		//	Si ecurie : 
		//		Si 6 :
		//			goDepart(r1)
		//	Sinon Si position end 
		//		return true
		//	go(r1,r2,de.getValue())
		return false;
	}
	
	public void end(Rider r) {
		
	}
	
	//A faire
	public String toString() {
		return "";
	}
	
	
	
}
