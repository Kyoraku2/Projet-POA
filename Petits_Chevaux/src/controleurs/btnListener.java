package controleurs;

import java.awt.BorderLayout;

import java.awt.event.*;

import vue.*;
import modele.*;

/**
 * btnListener class
 * @author Marie-Almina
 * @author Tayeb
 */
public class btnListener implements ActionListener{

	/**
	 * The window of the game
	 */
	private Window window;
	/**
	 * The current game
	 */
	private Game game;
	/**
	 * Allows to witch players plays the turn
	 */
	private int turn; // red :<=0, bleu :>0
	/**
	 * The size of the board
	 */
	private int sizeBoard;
	/**
	 * The graphic board 
	 */
	private GraphicBoard gboard;
	
	/**
	 * Constructor of btnListener 
	 * 
	 * @param w The window containing the elements to listen
	 */
	public btnListener(Window w) {
		window=w;
		turn=1;
		sizeBoard=16;
		gboard=new GraphicBoard();
	}

	/**
	 * Associate an action to a element listened
	 * 
	 * @param e The event that triggered the listener
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Simple game event
		if(e.getSource()==window.getBtnSimple()) {
			hideGameTypeMenu();
			showGameSimpleMenu();
		}
		
		if(e.getSource()==window.getBtnEasy()) {
			easyDifficulty();
		}
		
		if(e.getSource()==window.getBtnNormal()) {
			normalDifficulty();
		}
		
		if(e.getSource()==window.getBtnHard()) {
			hardDifficulty();
		}
		
		if(e.getSource()==window.getBtnBackSimple()) {
			hideGameSimpleMenu();
			window.getBtnPlaySimple().setEnabled(false);
			showGameTypeMenu();
		}
		
		if(e.getSource()==window.getBtnPlaySimple()) {
			hideGameSimpleMenu();
			showGame();
		}
		
		//Personalized game event
		if(e.getSource()==window.getBtnPerso()) {
			persoMenu();
		}
		
		if(e.getSource()==window.getBoxValidate()) {
			validateBoardSize();
		}
		
		if(e.getSource()==window.getBtnBackPerso()) {
			returnMenu2();
		}
		
		if(e.getSource()==window.getBtnPlayPerso()) {
			playPersoGame();
		}
		
		//General event
		if(e.getSource()==window.getBtnRoll()){
			game.getDice().rouler();
			int value=game.getDice().getValue();
			gboard.getCells().get((int)(game.getBoard().getCols()/2)).get(3).getLabel().setText("  "+value+"  ");
			playTurn(value);
		}
		
		if(e.getSource()==window.getTxtStep()) {
			int value=window.getStepValue();
			window.getTxtStep().setText("");
			gboard.getCells().get((int)(game.getBoard().getCols()/2)).get(3).getLabel().setText("  "+value+"  ");
			playTurn(value);
		}
		
		if(e.getSource()==window.getBtnQuit()) {
			System.exit(0);
		}
		
		if(e.getSource()==window.getBtnReplay()) {
			game=null;
			window.getBtnPlaySimple().setEnabled(false);
			window.getBtnPlayPerso().setEnabled(false);
			window.getEndMenu().setVisible(false);
			window.getPnlGameType().setVisible(true);
		}
	}
	
	//Simple game functions
	
	
	/**
	 * Shows the menu allowing to chose the game type
	 * And resize the window 
	 * 
	 */
	private void showGameTypeMenu() {
		window.setSize(500,200);
		window.getPnlGameType().setVisible(true);
	}
	
	/**
	 * Hides the menu allowing to chose the game type
	 * 
	 */
	private void hideGameTypeMenu() {
		window.getPnlGameType().setVisible(false);
	}
	
	/**
	 * Shows the menu allowing to chose the party difficulty in simples  games
	 * 
	 */
	private void showGameSimpleMenu() {
		window.setSize(500,200);
		window.getPnlDif().setVisible(true);
		window.getPnlSimpleBtn().setVisible(true);
		window.getC().add(window.getPnlDif(),BorderLayout.CENTER);
		window.getC().add(window.getPnlSimpleBtn(),BorderLayout.SOUTH);
	}
	
	/**
	 * Hides the menu allowing to chose the party difficulty in simples  games
	 * 
	 */
	private void hideGameSimpleMenu() {
		window.getPnlDif().setVisible(false);
		window.getPnlSimpleBtn().setVisible(false);
	}
	
	/**
	 * Shows the menu allowing to personalize a party 
	 * 
	 */
	private void showGamePersoMenu() {
		window.setSize(500,200);
		sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
		window.getSizePanel().setVisible(true);
		window.getPnlPersoBtn().setVisible(true);
		window.getC().add(window.getSizePanel(),BorderLayout.NORTH);
		window.getC().add(window.getPnlPersoBtn(),BorderLayout.SOUTH);
	}
	
	/**
	 * Hides the menu allowing to personalize a party 
	 * 
	 */
	private void hideGamePersoMenu() {
		window.getSizePanel().setVisible(false);
		window.getPnlPersoBtn().setVisible(false);
		window.getPnlPlayCenter().setVisible(false);
	}
	
	/**
	 * Shows the panels of a party
	 * 
	 */
	private void showGame() {
		initGame();
		window.getC().add(window.getPnlPlayUp(),BorderLayout.NORTH);
		window.getC().add(window.getPnlPlayCenter(),BorderLayout.CENTER);
		window.getC().add(window.getPnlPlayDown(),BorderLayout.SOUTH);
		window.getPnlPlayUp().setVisible(true);
		window.getPnlPlayCenter().setVisible(true);
		window.getPnlPlayDown().setVisible(true);
	}
	
	/**
	 * Hides the panels of a party
	 * 
	 */
	private void hideGame() {
		window.getPnlPlayUp().setVisible(false);
		window.getPnlPlayCenter().setVisible(false);
		window.getPnlPlayDown().setVisible(false);
	}
	
	/**
	 * Sets the difficulty to easy 
	 * 
	 */
	private void easyDifficulty() {
		window.getBtnPlaySimple().setEnabled(true);
		sizeBoard=10;
		gboard.setNbHole(1);
		gboard.setNbRiver(1);
		gboard.setNbHedge(1);
	}
	
	/**
	 * Sets the difficulty to normal 
	 * 
	 */
	private void normalDifficulty() {
		window.getBtnPlaySimple().setEnabled(true);
		sizeBoard=16;
		gboard.setNbHole(1);
		gboard.setNbRiver(2);
		gboard.setNbHedge(2);
	}
	
	/**
	 * Sets the difficulty to hard 
	 * 
	 */
	private void hardDifficulty() {
		window.getBtnPlaySimple().setEnabled(true);
		sizeBoard=24;
		gboard.setNbHole(3);
		gboard.setNbRiver(2);
		gboard.setNbHedge(2);
	}
	
	/**
	 * Reset the difficulty and goes to the personalize menu
	 * 
	 */
	private void persoMenu() {
		gboard.setNbHole(0);
		gboard.setNbRiver(0);
		gboard.setNbHedge(0);
		
		hideGameTypeMenu();
		showGamePersoMenu();	
	}
	
	/**
	 * Sets board size to the size chosen by the user
	 * Adds the board in the middle for the user to chose where to put the traps
	 * Adds a listener to the cells for that
	 * Makes it possible to use the play button to create a game
	 * 
	 */
	private void validateBoardSize() {
		sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
		initGame();
		window.getC().add(window.getPnlPlayCenter(),BorderLayout.CENTER);
		window.getPnlPlayCenter().setVisible(true);
		window.getBtnPlayPerso().setEnabled(true);
		gboard.addListener(game);
	}
	
	/**
	 * Allows to go from the perso menu to the game
	 * Remove the listener
	 * 
	 */
	private void playPersoGame() {
		hideGamePersoMenu();
		window.getPnlPlayUp().setVisible(true);
		window.getPnlPlayCenter().setVisible(true);
		window.getC().add(window.getPnlPlayUp(),BorderLayout.NORTH);
		window.getC().add(window.getPnlPlayCenter(),BorderLayout.CENTER);
		window.getC().add(window.getPnlPlayDown(),BorderLayout.SOUTH);
		window.getPnlPlayDown().setVisible(true);
		gboard.removeListener();
	}
	
	/**
	 * Allows to go from the perso menu back to the game type menu
	 * Remove the listener if needed
	 * 
	 */
	private void returnMenu2() {
		game=null;
		hideGamePersoMenu();
		if(gboard.getCells()!=null) {
			gboard.removeListener();
		}
		showGameTypeMenu();
	}
	
	/**
	 * Play a turn, if a rider has finish calls the end function
	 * 
	 * @param value The number of cell the rider has to go 
	 */
	private void playTurn(int value) {
		boolean ended=false;
		if(turn<=0) {
			ended=turn(game.getRider(0),game.getRider(1),value);
			if(ended) {
				end(game.getRider(0));
				return;
			}
		}else {
			ended=turn(game.getRider(1),game.getRider(0),value);
			if(ended) {
				end(game.getRider(1));
				return;
			}
		}
	}
	
	/**
	 * Initialize the game and the graphic board
	 * 
	 */
	private void initGame() {
		game=new Game(sizeBoard,false);
		gboard.init(game,window,sizeBoard);
	}
	
	/**
	 * Moves the rider of the right number of cells
	 * Uses the functions from the model
	 * 
	 * @param r1 The first rider
	 * @param r2 The other rider
	 * @param de The number of cells 
	 */
	private void go(Rider r1, Rider r2, int de) {
		Position tmp=r1.getPos();
		String process="";
		if(game.getBoard().getCellType(r1.getPos())=='=') {
			goStart(r1);
		}else {
			game.getBoard().followPath(r1,de);
			if(r1.getPos().equals(tmp)) {
				process="Le cavalier "+(r1.getColor()==Couleur.BLUE?"BLEU":"ROUGE")+" a dépassé la case départ !\n";
			}
			
		}
		if(r1.getPos().equals(r2.getPos())) {
			if(r2.getPos().equals(r2.getStart())) {
				goStable(r2);
			}else {
				goStart(r2);
			}
		}
		move(r1,tmp,r1.getPos()); 
		process+=((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1);
		window.getTxtProcess().setText(process);
		System.out.println(process);
	}
	
	/**
	 * Sends a rider back to the start cell
	 * 
	 * @param r The rider
	 */
	private void goStart(Rider r) {
		move(r,r.getPos(),r.getStart());
		game.getBoard().move(r,r.getPos(),r.getStart());
	}
	
	/**
	 * Sends a rider back to the stable cell
	 * 
	 * @param r The rider
	 */
	private void goStable(Rider r) {
		move(r,r.getPos(),r.getStable());
		game.getBoard().move(r,r.getPos(),r.getStable());
	}
	
	/**
	 * Go from the game to the end menu
	 * Set a text to shows who won
	 * 
	 * @param r The winner
	 */
	private void end(Rider r) {
		
		hideGame();
		if(r.getColor()==Couleur.RED) {
			window.getTxtWinner().setText("** Le joueur Rouge a gagné ! ** \n   Voulez vous rejouer ?");
			System.out.println("Le joueur Rouge a gagné !");
		}else {
			window.getTxtWinner().setText("** Le joueur Bleu a gagné ! ** \n   Voulez vous rejouer ?");
			System.out.println("Le joueur Bleu a gagné !");
		}
		window.getEndMenu().setVisible(true);
		window.getC().add(window.getEndMenu());
		window.setSize(500,200);
	}
	
	/**
	 * Moves a rider from a position to another on the graphic board
	 * 
	 * 
	 * @param r The rider
	 * @param from The initial position
	 * @param to The new position
	 */
	private void move(Rider r,Position from, Position to) {
		gboard.getCells().get(from.getCol()).get(from.getRow()).getLabel().setText("    ");
		gboard.getCells().get(to.getCol()).get(to.getRow()).getLabel().setText("  "+((r.getColor()==Couleur.RED)?"R":"B")+"  ");
	}
	
	/**
	 * Allows to make a turn, 
	 * Prints information on the turn (who plays and how much),
	 * Checks on witch cell the player is and then if he can move,
	 * If he can move it moves it to the right cell using go
	 * 
	 * @param r1 The first rider
	 * @param r2 The other rider
	 * @param value The number of cells the rider has to move
	 * @return True if the player has won (if he is on the finished cell) false otherwise
	 */
	private boolean turn(Rider r1, Rider r2,int value){
		String txtTurn="Le cavalier de couleur ";
		txtTurn+=r1.getColor()==Couleur.RED?"ROUGE ":"BLEU ";
		txtTurn+="a joué.  Au tour du ";
		txtTurn+=r2.getColor()==Couleur.RED?"ROUGE ":"BLEU ";
		txtTurn+="!";
		window.getTxtTurn().setText(txtTurn);
		System.out.println(txtTurn);
		char c=game.getBoard().getCellType(r1.getPos());
		switch(c) {
			case '@'://if hole : 
				if(r1.inHole()==-1) {
					go(r1,r2,value);
				}else {
					String tmp=((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1);
					window.getTxtProcess().setText(tmp);
					System.out.println(tmp);
				}
				break;
			case '|'://if hedge : 
				if(value%2!=0) {
					r1.setHedge(false);
					go(r1,r2,value);
				}else {
					String tmp=((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1);
					window.getTxtProcess().setText(tmp);
					System.out.println(tmp);
				}
				break;
			case '~'://if river :
				if(value%2==0) {
					r1.setRiver(false);
					go(r1,r2,value);
				}else {
					String tmp=((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1);
					window.getTxtProcess().setText(tmp);
					System.out.println(tmp);
				}
				break;
			case '='://if stable
				if(value==6 && game.getBoard().getCellType(r1.getPos())=='=') {
					go(r1,r2,1);
				}else {
					String tmp=((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1);
					window.getTxtProcess().setText(tmp);
					System.out.println(tmp);
				}
				break;
			case '*'://if end
				return true;
			default://if free :
				go(r1,r2,value);
				break;
		}
		System.out.println(game.toString());
		c=game.getBoard().getCellType(r1.getPos());
		if(c=='*') {
			return true;
		}
		turn*=-1;
		return false;
	}
}
