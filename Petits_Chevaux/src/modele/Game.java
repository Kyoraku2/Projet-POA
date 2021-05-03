package modele;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Game class
 * @author Marie-Almina
 * @author Tayeb
 */

public class Game {
	
	//Attributes
	private List<Rider> riders;
	private Board board;
	private De de;
	
	//Constructors
	public Game() {
		this(16);
	}
	
	public Game(int size) {
		de=new De();
		board=new Board(size);
		riders=new LinkedList<Rider>();
		riders.add(new Rider(new Position(1,0),new Position(1,1),Couleur.RED));
		riders.add(new Rider(new Position(board.getCols()-2,board.getRows()-1),new Position(board.getCols()-2,board.getRows()-2),Couleur.BLUE));
		board.init();
	}
	
	//Methods
	public void play(int first) {
		boolean ended=false;
		Rider r1= riders.get(0); //red
		Rider r2= riders.get(1); //blue
		System.out.println(this.toString());
		while(ended==false){
			if(first==1){
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
					end(r2);
					break;
				}
				ended=turn(r1,r2);
				if(ended) {
					end(r1);
				}
			}
		}
	}
	
	public void go(Rider r1, Rider r2, int de) {
		if(board.getCellType(r1.getPos())=='=') {
			goStart(r1);
		}else {
			board.followPath(r1,de);
		}
		if(r1.getPos().equals(r2.getPos())) {
			if(r2.getPos().equals(r2.getStart())) {
				goStable(r2);
			}else {
				goStart(r2);
			}
		}
		System.out.println(((CellPlayable)board.getCell(r1.getPos())).process(r1)+"\n");
	}
	
	public void goStart(Rider r) {
		board.move(r,r.getPos(),r.getStart());
	}
	
	public void goStable(Rider r) {
		if(r.getColor()==Couleur.RED) {
			board.move(r,r.getPos(),new Position(1,0));
		}else{
			board.move(r,r.getPos(),new Position(board.getCols()-2,board.getRows()-1));
		}
	}
	
	public boolean turn(Rider r1, Rider r2){
		Scanner s=new Scanner(System.in);
		System.out.print("Press ENTER to roll the dice : ");
		String str=s.nextLine();
		de.rouler();
		int value=de.getValue();

		System.out.print("Le cavalier de couleur ");
		System.out.print(r1.getColor()==Couleur.RED?"ROUGE ":"BLEU ");
		System.out.print("joue\n");
		System.out.println("Valeur du dé : "+value+"\n");
		char c=board.getCellType(r1.getPos());
		switch(c) {
			case '@'://if hole : 
				if(r1.inHole()==-1) {
					go(r1,r2,value);
				}else {
					System.out.println(((CellPlayable)board.getCell(r1.getPos())).process(r1)+"\n");
				}
				break;
			case '|'://if hedge : 
				if(value%2!=0) {
					r1.setHedge(false);
					go(r1,r2,value);
				}else {
					System.out.println(((CellPlayable)board.getCell(r1.getPos())).process(r1)+"\n");
				}
				break;
			case '~'://if river :
				if(value%2==0) {
					r1.setRiver(false);
					go(r1,r2,value);
				}else {
					System.out.println(((CellPlayable)board.getCell(r1.getPos())).process(r1)+"\n");
				}
				break;
			case '='://if stable
				if(value==6 && board.getCellType(r1.getPos())=='=') {
					go(r1,r2,1);
				}else {
					System.out.println(((CellPlayable)board.getCell(r1.getPos())).process(r1)+"\n");
				}
				break;
			case '*'://if end
				return true;
			default://Si free :
				go(r1,r2,value);
				break;
		}
		System.out.println(this.toString());
		c=board.getCellType(r1.getPos());
		if(c=='*') {
			return true;
		}
		return false;
	}
	
	public void end(Rider r) {
		if(r.getColor()==Couleur.RED) {
			System.out.println("Le joueur Rouge a gagné !");
		}else {
			System.out.println("Le joueur Bleu a gagné !");
		}
	}
	
	public String toString() {
		return board.toString(riders.get(0),riders.get(1));
	}
	
	
	
}
