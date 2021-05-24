package vue;

import java.awt.GridLayout;

import java.util.ArrayList;

import modele.*;

public class GraphicBoard {
	
	/**
	 * The length of the board
	 */
	private int sizeBoard;
	/**
	 * The number of holes in the board
	 */
	private int nbHole;
	/**
	 * The number of rivers in the board
	 */
	private int nbRiver;
	/**
	 * The number of hedges in the board
	 */
	private int nbHedge;
	/**
	 * ArrayList of ArrayList of GraphicCell modeling a board 
	 */
	private ArrayList<ArrayList<GraphicCell>> cells;
	
	/**
	 * Constructor of GraphicBoard,
	 * Sets the number of traps to 0 and the size of the board to 16
	 * 
	 */
	public GraphicBoard() {
		nbHole=0;
		nbRiver=0;
		nbHedge=0;
		sizeBoard=16;
	}
	
	/**
	 * Initialize the GraphicBoard and the board from game
	 * according to the numbers of traps
	 * 
	 * 
	 * @param game The game in with to initialize the board
	 * @param window The window of the game
	 * @param size The size of the board
	 */
	public void init(Game game,Window window,int size) {
		sizeBoard=size;
		window.getPnlPlayCenter().removeAll();
		
		int tmpHole=nbHole;
		int tmpHedge=nbHedge;
		int tmpRiver=nbRiver;
		
		//we place all the traps at randoms places on the board (of game)
		while(tmpHole>0 || tmpRiver>0 || tmpHedge>0) {
			int row =(int)(Math.random() * 2);
			if(row==0) {
				row=game.getBoard().getRows()-2;
			}
			int col =(int)(Math.random()*(sizeBoard-1)+1);
			Position pos=new Position(col,row);
			if(game.getBoard().getCellType(pos)=='.'){
				if(tmpHole>0) {
					game.getBoard().changeCell('@',pos);
					--tmpHole;
					continue;
				}
				if(tmpRiver>0) {
					game.getBoard().changeCell('~',pos);
					--tmpRiver;
					continue;
				}
				if(tmpHedge>0) {
					game.getBoard().changeCell('|',pos);
					--tmpHedge;
					continue;
				}
			}
		}
		
		window.setSize(game.getBoard().getCols()*50, game.getBoard().getRows()*50+200);
		window.getPnlPlayCenter().setLayout(new GridLayout(game.getBoard().getRows(),game.getBoard().getCols()));
		
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
				window.getPnlPlayCenter().add(c.getPanel());
				cells.get(j).set(i,c);
			}	
		}
		
		Position tmp=game.getRider(0).getPos();
		cells.get(tmp.getCol()).get(tmp.getRow()).getLabel().setText("  R  ");
		tmp=game.getRider(1).getPos();
		cells.get(tmp.getCol()).get(tmp.getRow()).getLabel().setText("  B  ");
		cells.get((int)(game.getBoard().getCols()/2)-1).get(3).getLabel().setText("  Dé  ");
		
	}
	
	/**
	 * Add the listener to each cell of the board 
	 * 
	 * @param game The current game
	 */
	public void addListener(Game game) {
		for(int i=0;i<game.getBoard().getRows();++i) {
			for(int j=0;j<game.getBoard().getCols();++j) {
				cells.get(j).get(i).listen(game, new Position(j,i));
			}
		}
	}
	
	/**
	 * Remove the listener of each cell of the board
	 * 
	 */
	public void removeListener() {
		for(ArrayList<GraphicCell> array : cells) {
			for(GraphicCell c : array) {
				if(c.getPanel().getMouseListeners().length != 0) {
					c.removeListener();
				}
			}
		}
	}
	
	/**
	 * Getter of the ArrayList of ArrayList of GraphicCell
	 * 
	 * @return The board
	 */
	public ArrayList<ArrayList<GraphicCell>> getCells(){
		return cells;
	}
	
	/**
	 * Setter of the number of holes in the board
	 * 
	 * @param n The new number of holes
	 */
	public void setNbHole(int n) {
		nbHole=n;
	}
	/**
	 * Setter of the number of rivers in the board
	 * 
	 * @param n The new number of rivers
	 */
	public void setNbRiver(int n) {
		nbRiver=n;
	}
	/**
	 * Setter of the number of hedges in the board
	 * 
	 * @param n The new number of hedges
	 */
	public void setNbHedge(int n) {
		nbHedge=n;
	}
}
