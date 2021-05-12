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
	
	
	public btnListener(Window w) {
		window=w;
		
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
		
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
