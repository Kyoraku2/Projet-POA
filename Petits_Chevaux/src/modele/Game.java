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
	
	////// Attributes //////
	
	/**
	 * List of the players of the game
	 */
	private List<Rider> riders;
	/**
	 * Board used for the game
	 */
	private Board board;
	/**
	 * Dice that will be used by the players
	 */
	private De de;
	
	
	////// Constructor //////
	
	/**
	 * Default constructor of game
	 * Call the other constructor with the size 16
	 */
	public Game() {
		this(16);
	}
	
	/**
	 * Constructor of Game
	 * Create a dice, add two riders to the list
	 * Create and initialize a board of size size
	 * 
	 * @param size The size of the board that will be created
	 */
	public Game(int size) {
		de=new De();
		board=new Board(size);
		riders=new LinkedList<Rider>();
		int rows=board.getRows();
		int cols=board.getCols();
		if(cols%2==0) {
			riders.add(new Rider(new Position(1,0),new Position(1,1),new Position((int)(cols/2)-3,(int)(rows/2)),Couleur.RED));
			riders.add(new Rider(new Position(board.getCols()-2,board.getRows()-1),new Position(board.getCols()-2,board.getRows()-2),new Position((int)(cols/2)+2,(int)(rows/2)),Couleur.BLUE));
		}else {
			riders.add(new Rider(new Position(1,0),new Position(1,1),new Position((int)(cols/2)+1-3,(int)(rows/2)),Couleur.RED));
			riders.add(new Rider(new Position(board.getCols()-2,board.getRows()-1),new Position(board.getCols()-2,board.getRows()-2),new Position((int)(cols/2)+1+1,(int)(rows/2)),Couleur.BLUE));
		}
		board.init(riders.get(0),riders.get(1));
	}
	
	
	////// Methods //////

	/**
	 * The game loop
	 * Calls the function turn for each turn of each player
	 * If one player win end the game loop and calls end
	 * 
	 * @param first The first player to play (1 for red)
	 */
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
	
	/**
	 * Allows to move a player of a number of case given by the dice
	 * If it lands on the same case as the other rider the other rider will be sent back to it's start 
	 * 		and the rider is already on it's start it will go back to it's stable
	 * Prints the result of the process of the cell
	 * 
	 * @param r1 The first rider
	 * @param r2 The other rider
	 * @param de The value of the dice rolled by the player
	 */
	private void go(Rider r1, Rider r2, int de) {
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
	
	
	/**
	 * Send a rider back to the start cell
	 * 
	 * @param r The rider
	 */
	private void goStart(Rider r) {
		board.move(r,r.getPos(),r.getStart());
	}
	
	/**
	 * Send a rider back to the stable cell
	 * 
	 * @param r The rider
	 */
	private void goStable(Rider r) {
		if(r.getColor()==Couleur.RED) {
			board.move(r,r.getPos(),new Position(1,0));
		}else{
			board.move(r,r.getPos(),new Position(board.getCols()-2,board.getRows()-1));
		}
	}
	
	/**
	 * Allows to make a turn 
	 * Ask the player to press enter to roll the dice
	 * Then prints information on this turn (who plays and how much)
	 * Checks on witch cell the player is and then if he can move
	 * If he can move it moves it to the right cell using go
	 * 
	 * @param r1 The first rider
	 * @param r2 The other rider
	 * @return True if the player has won (if he is on the finished line) false otherwise
	 */
	private boolean turn(Rider r1, Rider r2){
		////////////////////////////Exam part (not random dice)
		Scanner s=new Scanner(System.in);
		System.out.print("Write value of dice : ");
		while(!s.hasNextInt()) {
			s.next();
			System.out.print("That's not an integer, please enter again : ");
		}
		int value=s.nextInt();
		////////////////////////////End Exam part
		
		//For random dice, put above in comments end remove bellow comments 
		//Scanner s=new Scanner(System.in);
		//System.out.print("Press ENTER to roll the dice : ");
		//s.nextLine();
		//de.rouler();
		//int value=de.getValue();

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
	
	/**
	 * Prints the results of the race
	 * 
	 * @param r The winner
	 */
	private void end(Rider r) {
		if(r.getColor()==Couleur.RED) {
			System.out.println("Le joueur Rouge a gagné !");
		}else {
			System.out.println("Le joueur Bleu a gagné !");
		}
	}
	
	/**
	 * Call to string of board with the riders of the game
	 * 
	 * @return toString of board using the riders
	 */
	public String toString() {
		return board.toString(riders.get(0),riders.get(1));
	}
	
	
	
}
