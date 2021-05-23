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
		if(e.getSource()==window.getSimple()) {
			simpleMenu();
		}
		
		if(e.getSource()==window.getEasy()) {
			easyDifficulty();
		}
		
		if(e.getSource()==window.getNormal()) {
			normalDifficulty();
		}
		
		if(e.getSource()==window.getHard()) {
			hardDifficulty();
		}
		
		if(e.getSource()==window.getRetourMenu()) {
			returnMenu();
		}
		
		if(e.getSource()==window.getPlay()) {
			playGame();
		}
		
		//Personalized game event
		if(e.getSource()==window.getPerso()) {
			persoMenu();
		}
		
		if(e.getSource()==window.getBoxValidate()) {
			validateBoardSize();
		}
		
		if(e.getSource()==window.getRetourMenu2()) {
			returnMenu2();
		}
		
		if(e.getSource()==window.getPlayPerso()) {
			playPersoGame();
		}
		
		//General event
		if(e.getSource()==window.getRoll()){
			game.getDice().rouler();
			int value=game.getDice().getValue();
			gboard.getCells().get((int)(game.getBoard().getCols()/2)).get(3).setLabel("  "+value+"  ");
			playTurn(value);
		}
		
		if(e.getSource()==window.getStep()) {
			int value=window.getStepValue();
			window.getStep().setText("");
			gboard.getCells().get((int)(game.getBoard().getCols()/2)).get(3).setLabel("  "+value+"  ");
			playTurn(value);
		}
	}
	
	//Simple game functions
	private void simpleMenu() {
		window.getGameType().setVisible(false);
		window.getDif().setVisible(true);
		window.getButtonSimple().setVisible(true);
		window.getC().add(window.getDif(),BorderLayout.CENTER);
		window.getC().add(window.getButtonSimple(),BorderLayout.SOUTH);
	}
	
	private void hardDifficulty() {
		window.getPlay().setEnabled(true);
		sizeBoard=24;
		gboard.setNbHole(3);
		gboard.setNbRiver(2);
		gboard.setNbHedge(2);
	}
	
	private void normalDifficulty() {
		window.getPlay().setEnabled(true);
		sizeBoard=16;
		gboard.setNbHole(1);
		gboard.setNbRiver(2);
		gboard.setNbHedge(2);
	}
	
	private void easyDifficulty() {
		window.getPlay().setEnabled(true);
		sizeBoard=10;
		gboard.setNbHole(1);
		gboard.setNbRiver(1);
		gboard.setNbHedge(1);
	}
	
	private void playGame(){
		window.getButtonSimple().setVisible(false);
		
		window.getDif().setVisible(false);
		initGame();
		
		window.getC().add(window.getUp(),BorderLayout.NORTH);
		window.getC().add(window.getCenter(),BorderLayout.CENTER);
		window.getC().add(window.getDown(),BorderLayout.SOUTH);
		window.getUp().setVisible(true);
		window.getCenter().setVisible(true);
		window.getDown().setVisible(true);
	}
	
	private void returnMenu() {
		window.getButtonSimple().setVisible(false);
		window.getDif().setVisible(false);
		window.getPlay().setEnabled(false);
		window.getGameType().setVisible(true);
	}
	
	//Personalized game functions
	private void persoMenu() {
		game=null;
		gboard.setNbHole(0);
		gboard.setNbRiver(0);
		gboard.setNbHedge(0);
		
		window.getGameType().setVisible(false);
		window.getC().add(window.getSizePanel(),BorderLayout.NORTH);
		sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
		window.getSizePanel().setVisible(true);
		window.getC().add(window.getButtonPerso(),BorderLayout.SOUTH);
		window.getButtonPerso().setVisible(true);	
	}
	
	private void validateBoardSize() {
		sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
		initGame();
		window.getC().add(window.getCenter(),BorderLayout.CENTER);
		window.getCenter().setVisible(true);
		window.getPlayPerso().setEnabled(true);
		gboard.addListener(game);
	}
	
	private void playPersoGame() {
		window.getButtonPerso().setVisible(false);
		
		window.getSizePanel().setVisible(false);
		
		window.getC().add(window.getUp(),BorderLayout.NORTH);
		window.getC().add(window.getCenter(),BorderLayout.CENTER);
		window.getC().add(window.getDown(),BorderLayout.SOUTH);
		
		window.getUp().setVisible(true);
		window.getCenter().setVisible(true);
		window.getDown().setVisible(true);
	
		gboard.removeListener();
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
	
	private void returnMenu2() {
		window.getButtonPerso().setVisible(false);
		window.getSizePanel().setVisible(false);
		window.getPlayPerso().setEnabled(false);
		window.getUp().setVisible(false);
		window.getCenter().setVisible(false);
		window.getDown().setVisible(false);
		
		if(gboard.getCells()!=null) {
			gboard.removeListener();
		}
		
		window.setSize(700,400);
		window.getGameType().setVisible(true);
	}
	
	
	//General functions
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
		window.getRoll().setEnabled(false);
		window.getStep().setEnabled(false);
		if(r.getColor()==Couleur.RED) {
			window.getTxtTurn().setText("** Le joueur Rouge a gagné ! **");
		}else {
			window.getTxtTurn().setText("** Le joueur Bleu a gagné ! **");
		}
	}
	
	private void move(Rider r,Position from, Position to) {
		gboard.getCells().get(from.getCol()).get(from.getRow()).setLabel("    ");
		gboard.getCells().get(to.getCol()).get(to.getRow()).setLabel("  "+((r.getColor()==Couleur.RED)?"R":"B")+"  ");
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
