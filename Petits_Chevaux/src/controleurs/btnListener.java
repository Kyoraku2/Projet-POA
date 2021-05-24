package controleurs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import vue.*;
import modele.*;

public class btnListener implements ActionListener{

	private Window window;
	private Game game;
	private int turn; // red :<=0, bleu :>0
	private int sizeBoard;
	private GraphicBoard gboard;
	
	public btnListener(Window w) {
		window=w;
		turn=1;
		sizeBoard=16;
		gboard=new GraphicBoard();
	}
	
	

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
			window.getEndMenu().setVisible(false);
			window.getPnlGameType().setVisible(true);
		}
	}
	
	//Simple game functions
	
	
	
	private void showGameTypeMenu() {
		window.setSize(700,400);
		window.getPnlGameType().setVisible(true);
	}
	
	private void hideGameTypeMenu() {
		window.getPnlGameType().setVisible(false);
	}
	
	private void showGameSimpleMenu() {
		window.setSize(700,400);
		window.getPnlDif().setVisible(true);
		window.getPnlSimpleBtn().setVisible(true);
		window.getC().add(window.getPnlDif(),BorderLayout.CENTER);
		window.getC().add(window.getPnlSimpleBtn(),BorderLayout.SOUTH);
	}
	
	private void hideGameSimpleMenu() {
		window.getPnlDif().setVisible(false);
		window.getPnlSimpleBtn().setVisible(false);
	}
	
	private void showGamePersoMenu() {
		window.setSize(700,400);
		sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
		window.getSizePanel().setVisible(true);
		window.getPnlPersoBtn().setVisible(true);
		window.getC().add(window.getSizePanel(),BorderLayout.NORTH);
		window.getC().add(window.getPnlPersoBtn(),BorderLayout.SOUTH);
	}
	
	private void hideGamePersoMenu() {
		window.getSizePanel().setVisible(false);
		window.getPnlPersoBtn().setVisible(false);
		window.getPnlPlayCenter().setVisible(false);
	}
	
	private void showGame() {
		initGame();
		window.getC().add(window.getPnlPlayUp(),BorderLayout.NORTH);
		window.getC().add(window.getPnlPlayCenter(),BorderLayout.CENTER);
		window.getC().add(window.getPnlPlayDown(),BorderLayout.SOUTH);
		window.getPnlPlayUp().setVisible(true);
		window.getPnlPlayCenter().setVisible(true);
		window.getPnlPlayDown().setVisible(true);
	}
	
	private void hideGame() {
		window.getPnlPlayUp().setVisible(false);
		window.getPnlPlayCenter().setVisible(false);
		window.getPnlPlayDown().setVisible(false);
	}
	
	private void easyDifficulty() {
		window.getBtnPlaySimple().setEnabled(true);
		sizeBoard=10;
		gboard.setNbHole(1);
		gboard.setNbRiver(1);
		gboard.setNbHedge(1);
	}
	
	private void normalDifficulty() {
		window.getBtnPlaySimple().setEnabled(true);
		sizeBoard=16;
		gboard.setNbHole(1);
		gboard.setNbRiver(2);
		gboard.setNbHedge(2);
	}
	
	private void hardDifficulty() {
		window.getBtnPlaySimple().setEnabled(true);
		sizeBoard=24;
		gboard.setNbHole(3);
		gboard.setNbRiver(2);
		gboard.setNbHedge(2);
	}
	
	
	private void persoMenu() {
		gboard.setNbHole(0);
		gboard.setNbRiver(0);
		gboard.setNbHedge(0);
		
		hideGameTypeMenu();
		showGamePersoMenu();	
	}
	
	private void validateBoardSize() {
		sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
		initGame();
		window.getC().add(window.getPnlPlayCenter(),BorderLayout.CENTER);
		window.getPnlPlayCenter().setVisible(true);
		window.getBtnPlayPerso().setEnabled(true);
		gboard.addListener(game);
	}
	
	private void playPersoGame() {
		hideGamePersoMenu();
		showGame();
		gboard.removeListener();
	}
	
	private void returnMenu2() {
		game=null;
		hideGamePersoMenu();
		if(gboard.getCells()!=null) {
			gboard.removeListener();
		}
		showGameTypeMenu();
	}
	
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
	
	private void initGame() {
		game=new Game(sizeBoard,false);
		gboard.init(game,window,sizeBoard);
	}
	
	private void go(Rider r1, Rider r2, int de) {
		Position tmp=r1.getPos();
		String process="";
		if(game.getBoard().getCellType(r1.getPos())=='=') {
			goStart(r1);
		}else {
			//Pour animer avec rouler de, juste while(de){follow(1)+timer+move} Et followpath tableau positions
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
	
	private void end(Rider r) {
		
		hideGame();
	
		if(r.getColor()==Couleur.RED) {
			window.getTxtWinner().setText("** Le joueur Rouge a gagné ! ** \n   Voulez vous rejouer ?");
		}else {
			window.getTxtWinner().setText("** Le joueur Bleu a gagné ! ** \n   Voulez vous rejouer ?");
		}
		
		window.getEndMenu().setVisible(true);
		window.getC().add(window.getEndMenu());
		window.setSize(700,400);
		
	}
	
	private void move(Rider r,Position from, Position to) {
		gboard.getCells().get(from.getCol()).get(from.getRow()).getLabel().setText("    ");
		gboard.getCells().get(to.getCol()).get(to.getRow()).getLabel().setText("  "+((r.getColor()==Couleur.RED)?"R":"B")+"  ");
	}
	
	private boolean turn(Rider r1, Rider r2,int value){
		String txtTurn="Le cavalier de couleur ";
		txtTurn+=r1.getColor()==Couleur.RED?"ROUGE ":"BLEU ";
		txtTurn+="joue\n";
		window.getTxtTurn().setText(txtTurn);
		char c=game.getBoard().getCellType(r1.getPos());
		switch(c) {
			case '@'://if hole : 
				if(r1.inHole()==-1) {
					go(r1,r2,value);
				}else {
					window.getTxtProcess().setText(((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1));
				}
				break;
			case '|'://if hedge : 
				if(value%2!=0) {
					r1.setHedge(false);
					go(r1,r2,value);
				}else {
					window.getTxtProcess().setText(((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1));
				}
				break;
			case '~'://if river :
				if(value%2==0) {
					r1.setRiver(false);
					go(r1,r2,value);
				}else {
					window.getTxtProcess().setText(((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1));
				}
				break;
			case '='://if stable
				if(value==6 && game.getBoard().getCellType(r1.getPos())=='=') {
					go(r1,r2,1);
				}else {
					window.getTxtProcess().setText(((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1));
				}
				break;
			case '*'://if end
				return true;
			default://if free :
				go(r1,r2,value);
				break;
		}
		c=game.getBoard().getCellType(r1.getPos());
		if(c=='*') {
			return true;
		}
		turn*=-1;
		return false;
	}
}
