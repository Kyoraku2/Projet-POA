package controleurs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import vue.*;
import modele.*;

public class btnListener implements ActionListener{

	Window window;
	Game game;
	private ArrayList<ArrayList<GraphicCell>> cells;
	private int turn; // red :<=0, bleu :>0
	private int sizeBoard;
	private int nbHole;
	private int nbRiver;
	private int nbHedge;
	
	public btnListener(Window w) {
		window=w;
		turn=1;
		sizeBoard=16;
		nbHole=1;
		nbRiver=1;
		nbHedge=1;
	}
	
	

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==window.getSimple()) {
			window.getGameType().setVisible(false);
			window.getC().add(window.getDif(),BorderLayout.CENTER);
			window.getC().add(window.getPlay(),BorderLayout.SOUTH);
		}
		
		if(e.getSource()==window.getPerso()) {
			window.getGameType().setVisible(false);
			window.getC().add(window.getSizePanel());
		}
		
		boolean ended=false;
		if(e.getSource()==window.getRoll()){
			game.getDice().rouler();
			int value=game.getDice().getValue();
			cells.get((int)(game.getBoard().getCols()/2)).get(3).setLabel("  "+value+"  ");
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
			cells.get((int)(game.getBoard().getCols()/2)).get(3).setLabel("  "+value+"  ");
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
			
			initGame();
			
			window.getC().add(window.getUp(),BorderLayout.NORTH);
			window.getC().add(window.getCenter(),BorderLayout.CENTER);
			window.getC().add(window.getDown(),BorderLayout.SOUTH);
		
		}
		
		if(e.getSource()==window.getEasy()) {
			window.getPlay().setEnabled(true);
			sizeBoard=10;
			nbHole=1;
			nbRiver=1;
			nbHedge=1;
		}
		
		if(e.getSource()==window.getNormal()) {
			window.getPlay().setEnabled(true);
			sizeBoard=16;
			nbHole=1;
			nbRiver=2;
			nbHedge=2;
		}
		
		if(e.getSource()==window.getHard()) {
			window.getPlay().setEnabled(true);
			sizeBoard=24;
			nbHole=3;
			nbRiver=2;
			nbHedge=2;
		}
		
		
	}
	
	private void initGame() {
		window.getPlay().setEnabled(false);
		window.getPlay().setVisible(false);
		
		window.getDif().setVisible(false);
		
		
		window.getEasy().setEnabled(false);
		window.getEasy().setVisible(false);
		
		window.getNormal().setEnabled(false);
		window.getNormal().setVisible(false);
		
		window.getHard().setEnabled(false);
		window.getHard().setVisible(false);
		
		
		game=new Game(sizeBoard,false);
		
		
		initGraphicBoard();
		
		
	}
	
	private void initGraphicBoard() {
		
		while(nbHole>0 || nbRiver>0 || nbHedge>0) {
			int row =(int)(Math.random() * 2);
			if(row==0) {
				row=game.getBoard().getRows()-2;
			}
			int col =(int)(Math.random()*(sizeBoard-1)+1);
			
			Position pos=new Position(col,row);
			if(game.getBoard().getCellType(pos)=='.'){
				if(nbHole>0) {
					game.getBoard().changeCell('@',pos);
					--nbHole;
					continue;
				}
				if(nbRiver>0) {
					game.getBoard().changeCell('~',pos);
					--nbRiver;
					continue;
				}
				if(nbHedge>0) {
					game.getBoard().changeCell('|',pos);
					--nbHedge;
					continue;
				}
			}
			
		}
		
		window.setSize(game.getBoard().getCols()*50, game.getBoard().getRows()*50+200);
		window.getCenter().setLayout(new GridLayout(game.getBoard().getRows(),game.getBoard().getCols()));
	
		cells=new ArrayList<ArrayList<GraphicCell>>(game.getBoard().getCols());
		for(int i=0;i<game.getBoard().getCols();++i) {
			cells.add(new ArrayList<GraphicCell>(game.getBoard().getRows()));
			for(int j=0; j<game.getBoard().getRows();++j) {
				cells.get(i).add(new GraphicCell(new CellFree()));
			}
		}
		
		for(int i=0;i<game.getBoard().getRows();++i) {
			for(int j=0;j<game.getBoard().getCols();++j) {
				GraphicCell c=new GraphicCell(game.getBoard().getCell(new Position(j,i)));
				window.getCenter().add(c.getCell());
				cells.get(j).set(i, c);
			}	
		}
		
		
		Position tmp=game.getRider(0).getPos();
		cells.get(tmp.getCol()).get(tmp.getRow()).setLabel("  R  ");
		tmp=game.getRider(1).getPos();
		cells.get(tmp.getCol()).get(tmp.getRow()).setLabel("  B  ");
		
		cells.get((int)(game.getBoard().getCols()/2)-1).get(3).setLabel("  Dé  ");
		
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
		cells.get(from.getCol()).get(from.getRow()).setLabel("    ");
		cells.get(to.getCol()).get(to.getRow()).setLabel("  "+((r.getColor()==Couleur.RED)?"R":"B")+"  ");
	}
	
	
	/*Pour les bonus :
	 * - Créer une zone de saisie pour le nombre d'obstacles
	 * - Créer des boutons pour choisir la difficuté (un entier ? ou juste si source on initialise a telle valeur
	 * - Créer un entier pour le nombre d'obstacle
	 * */
}
