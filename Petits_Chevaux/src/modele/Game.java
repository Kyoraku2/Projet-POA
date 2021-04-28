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
		board.init();
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
		boolean ended=false;
		Rider r1= riders.get(0); //red
		Rider r2= riders.get(1); //blue
		while(ended==false){
			if(first==1){
				//r1 rouge, r2 bleu
				
				ended=turn(r1,r2);
				if(ended) {
					end(r1);
					break;
				}
				ended=turn(r2,r1);
				if(ended) {
					end(r2);
				}
			}else{
				ended=turn(r2,r1);
				if(ended) {
					end(r1);
					break;
				}
				ended=turn(r1,r2);
				if(ended) {
					end(r2);
				}
			}
		}
	}
	
	//A faire
	public void go(Rider r1, Rider r2, int de) {
		//	board.followPath
		board.followPath(r1,de);
		//	Si collision à l'arrivée then yeet r2
		//	Sinon r juste occuped
		if(r1.getPos().equals(r2.getPos())) {
			goStart(r2);
		}
		//	call process
		System.out.println(board.getCell(r1.getPos()));
	}
	
	//A faire
	public void goStart(Rider r) {
		if(r.getColor()==Couleur.RED) {
			board.move(r,r.getPos(),board.getRedStart());
		}else{
			board.move(r,r.getPos(),board.getBlueStart());
		}
	}
	
	//A faire (utiliser getCellType avec pos départ)
	public boolean turn(Rider r1, Rider r2){
		//Roule un dé
		de.rouler();
		int value=de.getValue();
		//Check pos départ
		char c=board.getCellType(r1.getPos());
		switch(c) {
			case '@'://Si hole : 
				go(r1,r2,value);
			break;
			case '|'://Si haie : 
				if(value%2!=0) {
					go(r1,r2,value);
				}
			break;
			case '~'://Si River :
				if(value%2==0) {
					go(r1,r2,value);
				}
			break;
			default://Si Free :
				if(value==6 && !board.getCell(r1.getPos()).isPlayable()) {
					goStart(r1);
				}else {
					if(( r1.getColor()==Couleur.RED && r1.getPos().equals(board.getRedEnd()) )|| ( r1.getColor()==Couleur.BLUE && r1.getPos().equals(board.getBlueEnd()) )) {
						return true;
					}
				}
				go(r1,r2,value);
			break;
		}
		System.out.println("wsh"+this.toString());
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
		if(r.getColor()==Couleur.RED) {
			System.out.println("Le joueur Rouge a gagné !");
		}else {
			System.out.println("Le joueur Bleu a gagné !");
		}
	}
	
	//A faire
	public String toString() {
		return board.toString(riders.get(0),riders.get(1));
	}
	
	
	
}
