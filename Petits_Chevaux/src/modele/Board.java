package modele;

import java.util.*;

/**
 * Board class
 * @author Marie-Almina
 * @author Tayeb
 */

public class Board {
	
	////// Attributes //////
	
	/**
	 * x-coordinate
	 */
	private int cols;
	/**
	 * y-coordinate
	 */
	private int rows;
	/**
	 * ArrayList of ArrayList of Cell modeling a board 
	 */
	private ArrayList<ArrayList<Cell>> cells;
	/**
	 * Position where the red player finish and win the race
	 */
	private Position end_red;
	/**
	 * Position where the blue player finish and win the race
	 */
	private Position end_blue;
	
		
	////// Constructor //////
		
	/**
	 * Constructor of board
	 * 
	 * @param c The length (number of columns) of the board
	 */
	public Board(int c) {
		rows=7;
		this.cols=c+2;
		cells=new ArrayList<ArrayList<Cell>>(cols);
		
		for(int i=0;i<cols;++i) {
			cells.add(new ArrayList<Cell>(rows));
			for(int j=0; j<rows;++j) {
				cells.get(i).add(new CellFree(new Position(i,j)));
			}
		}
		
		if(cols%2==0) {
			end_red=new Position((int)(cols/2)-3,(int)(rows/2));
			end_blue=new Position((int)(cols/2)+2,(int)(rows/2));
		}else {
			end_red=new Position((int)(cols/2)+1-3,(int)(rows/2));
			end_blue=new Position((int)(cols/2)+1+1,(int)(rows/2));
		}
	}
		
		
	////// Methods //////

	/**
	 * Getter of the number of columns of the board
	 * 
	 * @return the number of columns
	 */
	public int getCols() {
		return cols;
	}
	
	/**
	 * Getter of the number of rows of the board
	 * 
	 * @return the number of rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Getter of a cell of the board 
	 * 
	 * @param p the position of the cell we want
	 * @return The cell
	 */
	public Cell getCell(Position p) {
		return cells.get(p.getCol()).get(p.getRow());
	}
	
	/**
	 * Getter of the type of a cell of the board 
	 * 	Uses getCell
	 * 
	 * @param p the position of the cell we want
	 * @return The character indicating the type of the cell 
	 */
	public char getCellType(Position p) {
		return getCell(p).getSymbol();
		//return cells.get(p.getCol()).get(p.getRow()).getSymbole();
	}

	/**
	 * Getter of the position where the red rider end the race
	 * 
	 * @return The end position of red
	 */
	public Position getRedEnd() {
		return end_red;
	}
	
	/**
	 * Getter of the position where the blue rider end the race
	 * 
	 * @return The end position of blue
	 */
	public Position getBlueEnd() {
		return end_blue;
	}
	
	/**
	 * Initialize the board
	 * Put	CellWhite where the players doesn't go
	 * 		CellFree where they can go
	 * 		CellSide around the board
	 * 		Cell start and CellStable of each players
	 * 		CellRiver, CellHole and CellHedge according to the board we where given in class
	 * Then calculate where the CellFinish of each player will be
	 * 
	 */
	public void init() {
		for(int i=0;i<rows;++i) {
			if(i==0 || i==rows-1) {
				for(int j=0; j<cols;j++) {
					changeCell('+',new Position(j,i));
				}
			}
		}
		for(int i=0; i<cols;i++) {
			if(i==0 || i==cols-1) {
				for(int j=0; j<rows;j++) {
					changeCell('+',new Position(i,j));
				}
			}
		}
		for(int i=1; i<cols-1;i++) {
			changeCell(' ',new Position(i,2));
			changeCell(' ',new Position(i,4));
		}
		changeCell('@',new Position(4,1));
		changeCell('|',new Position(5,5));
		changeCell('~',new Position(10,5));
		changeCell('=',new Position(1,0));
		changeCell('=',new Position(cols-2,rows-1));
		changeCell('#',new Position(1,1));
		changeCell('#',new Position(cols-2,rows-2));
		for(int i=end_red.getCol();i<end_blue.getCol();++i) {
			changeCell(' ',new Position(i,(int)(rows/2)));
		}
		changeCell('*',new Position(end_red.getCol(),end_red.getRow()));
		changeCell('*',new Position(end_blue.getCol(),end_blue.getRow()));
	}
	
	/**
	 * Change the type of the cell of a known position in the ArrayList cells
	 * 
	 * @param symbole	Allows to know the new type of the cell
	 * @param from		The position of the cell to change
	 */
	public void changeCell(char symbole,Position from) {
		Cell cell;
		switch(symbole) {
			case '@':
				cell=new CellHole(from);
				break;
			case '|':
				cell=new CellHedge(from);
				break;
			case '~':
				cell=new CellRiver(from);
				break;
			case '+':
				cell=new CellSide(from);
				break;
			case ' ':
				cell=new CellWhite(from);
				break;
			case '#':
				cell=new CellStart(from);
				break;
			case '=':
				cell=new CellStable(from);
				break;
			case '*':
				cell=new CellFinish(from);
				break;
			default:
				cell=new CellFree(from);
				break;
		}
		cells.get(from.getCol()).set(from.getRow(), cell);
	}
	
	/**
	 * Remove a rider from a CellPlayable
	 * 
	 * @param from	The position of the rider to remove
	 * @return true when the rider is removed
	 */
	public boolean removeRider(Position from) {
		((CellPlayable)cells.get(from.getCol()).get(from.getRow())).unOccuped();
		return true;
	}
	
	/**
	 * Add a rider to a CellPlayable
	 * 
	 * @param to	The position where the rider will be added
	 * @return true when the rider is added
	 */
	public boolean addRider(Position to) {
		((CellPlayable)cells.get(to.getCol()).get(to.getRow())).becomeOccuped();
		return true;
	}
	
	/**
	 * Move a rider from a cell to another
	 * Checks if the new position is valid then move the rider to it
	 * 
	 * @param r		The rider we want to move
	 * @param from	The position of the rider
	 * @param to	The position where the rider will be moved
	 */
	public void move(Rider r,Position from, Position to) {
		if(from.isValid(rows,cols) && to.isValid(rows,cols) && getCell(to).isPlayable()) {
			r.move(to);
			removeRider(from);
			addRider(to);
		}
	}
	
	/**
	 * Allow to move the rider of several cases according to his path
	 * First it checks what player has to move since they don't have the same path
	 * Then while the rider hasn't moved of the number of cell he has 
	 * 		it checks where it is and move it to the next cell 
	 * 
	 * @param r		The rider we want to move
	 * @param from	The position of the rider
	 * @param to	The position where the rider will be moved
	 */
	public void followPath(Rider r, int n) {
		if(r.getColor()==Couleur.RED) {
			while(n!=0) {
				int x=r.getPos().getCol();
				int y=r.getPos().getRow();
				if(x<cols-2 && y==1) {
					move(r,r.getPos(),new Position(x+1,y));
					n--;
				}
				if(x==cols-2 && y<rows-2) {
					move(r,r.getPos(),new Position(x,y+2));
					n--;
				}
				if(x>1 && y==rows-2) {
					move(r,r.getPos(),new Position(x-1,y));
					n--;
				}
				if(x==1 && y>3) {
					move(r,r.getPos(),new Position(x,y-2));
					n--;
				}
				if(y==3 && x<end_red.getCol()) {
					move(r,r.getPos(),new Position(x+1,y));
					n--;
				}
				if(getCell(r.getPos()).getSymbol()=='*') {
					break;
				}
			}
		}else {
			while(n!=0) {
				int x=r.getPos().getCol();
				int y=r.getPos().getRow();
				if(x>1 && y==rows-2) {
					move(r,r.getPos(),new Position(x-1,y));
					n--;
					continue;
				}
				if(x==1 && y>1) {
					move(r,r.getPos(),new Position(x,y-2));
					n--;
					continue;
				}
				if(x<cols-2 && y==1) {
					move(r,r.getPos(),new Position(x+1,y));
					n--;
					continue;
				}
				if(x==cols-2 && y<3) {
					move(r,r.getPos(),new Position(x,y+2));
					n--;
					continue;
				}
				if(y==3 && x>end_blue.getCol()) {
					move(r,r.getPos(),new Position(x-1,y));
					n--;
					continue;
				}
				if(getCell(r.getPos()).getSymbol()=='*') {
					break;
				}
			}
		}
	}
	
	/**
	 * Allows to represent the board of the game 
	 * 	shows the players and all the specials cells
	 * 
	 * @param r1 The first player
	 * @param r2 The second player
	 * @return A String modeling the board
	 */
	public String toString(Rider r1,Rider r2) {
		String str="";
		for(int i=0;i<rows;++i) {
			for(int j=0;j<cols;++j) {
				if(r1.getPos().equals(new Position(j,i))) {
					str+=" R ";
				}else {
					if(r2.getPos().equals(new Position(j,i))) {
						str+=" B ";
					}else {
						str+=" "+cells.get(j).get(i).getSymbol()+" ";
					}
				}
			}
			str+="\n";
		}
		return str;
	}

}
