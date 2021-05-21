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
		
		if(e.getSource()==window.getSimple()) {
			window.getGameType().setVisible(false);
			window.getC().add(window.getDif(),BorderLayout.CENTER);
			window.getC().add(window.getPlay(),BorderLayout.SOUTH);
		}
		
		if(e.getSource()==window.getPerso()) {
			window.getGameType().setVisible(false);
			window.getC().add(window.getSizePanel(),BorderLayout.NORTH);
			sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
			window.getC().add(window.getPlayPerso(),BorderLayout.SOUTH);
		}
		
		if(e.getSource()==window.getBoxValidate()) {
			sizeBoard=Integer.parseInt(window.getSelectBox().getSelectedItem().toString());
			initGame();
			window.getC().add(window.getCenter(),BorderLayout.CENTER);
			window.getPlayPerso().setEnabled(true);
		}
		
		if(e.getSource()==window.getPlayPerso()) {
			window.getPlayPerso().setEnabled(false);
			window.getPlayPerso().setVisible(false);
			
			window.getSizePanel().setVisible(false);
			
			window.getC().add(window.getUp(),BorderLayout.NORTH);
			window.getC().add(window.getCenter(),BorderLayout.CENTER);
			window.getC().add(window.getDown(),BorderLayout.SOUTH);
		
			gboard.removeListener();
		}
		
		boolean ended=false;
		if(e.getSource()==window.getRoll()){
			game.getDice().rouler();
			int value=game.getDice().getValue();
			gboard.getCells().get((int)(game.getBoard().getCols()/2)).get(3).setLabel("  "+value+"  ");
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
		
		if(e.getSource()==window.getStep()) {
			int value=window.getStepValue();
			window.getStep().setText("");
			gboard.getCells().get((int)(game.getBoard().getCols()/2)).get(3).setLabel("  "+value+"  ");
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
		
		if(e.getSource()==window.getPlay()) {
			// Voir si on change
			window.getPlay().setEnabled(false);
			window.getPlay().setVisible(false);
			
			window.getDif().setVisible(false);
			initGame();
			
			window.getC().add(window.getUp(),BorderLayout.NORTH);
			window.getC().add(window.getCenter(),BorderLayout.CENTER);
			window.getC().add(window.getDown(),BorderLayout.SOUTH);
		
		}
		
		if(e.getSource()==window.getEasy()) {
			window.getPlay().setEnabled(true);
			sizeBoard=10;
			gboard.setNbHole(1);
			gboard.setNbRiver(1);
			gboard.setNbHedge(1);
		}
		
		if(e.getSource()==window.getNormal()) {
			window.getPlay().setEnabled(true);
			sizeBoard=16;
			gboard.setNbHole(1);
			gboard.setNbRiver(2);
			gboard.setNbHedge(2);
		}
		
		if(e.getSource()==window.getHard()) {
			window.getPlay().setEnabled(true);
			sizeBoard=24;
			gboard.setNbHole(3);
			gboard.setNbRiver(2);
			gboard.setNbHedge(2);
		}
		
		
	}
	
	private void initGame() {
		game=new Game(sizeBoard,false);
		
		gboard.init(game,window,sizeBoard);
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

	private void go(Rider r1, Rider r2, int de) {
		Position tmp=r1.getPos();
		if(game.getBoard().getCellType(r1.getPos())=='=') {
			goStart(r1);
		}else {
			// Pas à pas c chiant on verra a p r e s
			
			//Pour animer avec rouler de, juste while(de){follow(1)+timer+move} Et followpath tableau positions
			game.getBoard().followPath(r1,de);
			
		}
		if(r1.getPos().equals(r2.getPos())) {
			if(r2.getPos().equals(r2.getStart())) {
				goStable(r2);
			}else {
				goStart(r2);
			}
		}
		move(r1,tmp,r1.getPos()); 
		window.getTxtProcess().setText(((CellPlayable)game.getBoard().getCell(r1.getPos())).process(r1));
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
	
	
	/*Pour les bonus :
	 * - Créer une zone de saisie pour le nombre d'obstacles
	 * - Créer des boutons pour choisir la difficuté (un entier ? ou juste si source on initialise a telle valeur
	 * - Créer un entier pour le nombre d'obstacle
	 * */
}
