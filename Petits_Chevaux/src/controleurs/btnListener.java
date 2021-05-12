package controleurs;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import vue.*;
import modele.*;

public class btnListener implements ActionListener{

	Window window;
	Game game;
	
	public btnListener(Window w) {
		window=w;
		
		game=new Game();
	
		window.setSize(game.getBoard().getCols()*50, game.getBoard().getRows()*50+150);
		//set un size pour toute ces merde
		// init board (a metre dans btn commencer partie aptes)
		window.getCenter().setLayout(new GridLayout(game.getBoard().getRows(),game.getBoard().getCols()));
		
		for(int i=0;i<game.getBoard().getRows();++i) {
			for(int j=0;j<game.getBoard().getCols();++j) {
				window.getCenter().add(new GraphicCell(game.getBoard().getCell(new Position(j,i))).getCell());
			}	
		}
		
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
