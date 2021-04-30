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
		riders.add(new Rider(new Position(1,0),new Position(1,1),Couleur.RED));
		riders.add(new Rider(new Position(board.getCols()-2,board.getRows()-1),new Position(board.getCols()-2,board.getRows()-2),Couleur.BLUE));
	}
	
	//Methods
	
	//A faire
	public void play(int first) {
		//boucle de jeu (while pas arriv�, on alterne le tour)
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
	
	public void go(Rider r1, Rider r2, int de) {
		board.followPath(r1,de);
		if(r1.getPos().equals(r2.getPos())) {
			goStart(r2);
		}
		//	call process
		System.out.println(((CellPlayable)board.getCell(r1.getPos())).process(r1)+"\n");
	}
	
	public void goStart(Rider r) {
		if(r.getColor()==Couleur.RED) {
			board.move(r,r.getPos(),r.getStart());
		}else{
			board.move(r,r.getPos(),r.getStart());
		}
		System.out.println(((CellPlayable)board.getCell(r.getPos())).process(r)); //cast
	}
	
	public boolean turn(Rider r1, Rider r2){
		//Roule un d�
		de.rouler();
		int value=de.getValue();
		System.out.print("Le cavalier de couleur ");
		System.out.print(r1.getColor()==Couleur.RED?"ROUGE ":"BLEU ");
		System.out.print("joue\n");
		System.out.println("Valeur du d� : "+value+"\n");
		//Check pos d�part
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
				if(value==6 && board.getCellType(r1.getPos())=='=') {
					goStart(r1);
					//return true;
				}else {
					if(r1.getPos().equals(board.getBlueEnd()) || r1.getPos().equals(board.getRedEnd())) {
						return true;
					}
				}
				go(r1,r2,value);
			break;
		}
		System.out.println(this.toString());
		return false;
	}
	
	public void end(Rider r) {
		System.out.println(this.toString());
		/*if(r.getColor()==Couleur.RED) {
			System.out.println("Le joueur Rouge a gagn� !");
		}else {
			System.out.println("Le joueur Bleu a gagn� !");
		}*/
	}
	
	//A faire
	public String toString() {
		return board.toString(riders.get(0),riders.get(1));
	}
	
	
	
}
