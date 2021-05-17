package controleurs;

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
	
	public btnListener(Window w) {
		window=w;
		turn=1;
		game=new Game();
	
		window.setSize(game.getBoard().getCols()*50, game.getBoard().getRows()*50+200);
		//set un size pour toute ces merde
		// init board (a metre dans btn commencer partie aptes)
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

	public void actionPerformed(ActionEvent e) {
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
			if(turn<=0) {
				if(game.getRider(0).getStable().equals(game.getRider(0).getPos())) {
					ended=turn(game.getRider(0),game.getRider(1),6);
				}else {
					ended=turn(game.getRider(0),game.getRider(1),1);
				}
				if(ended) {
					end(game.getRider(0));
					return;
				}
			}else {
				if(game.getRider(1).getStable().equals(game.getRider(1).getPos())) {
					ended=turn(game.getRider(1),game.getRider(0),6);
				}else {
					ended=turn(game.getRider(1),game.getRider(0),1);
				}
				if(ended) {
					end(game.getRider(1));
					return;
				}
			}
		}
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
			
			//Pour animer avec rouler de, juste while(de){follow(1)+timer+move}
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
	
	
	
}
