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
		
	////// Constructor //////
		
	/**
	 * Constructor of board
	 * 
	 * @param c The length (number of columns) of the board
	 */
	public Board(int c) {
		rows=7;
		this.cols=c+2;
		
		//Cells initialization
		cells=new ArrayList<ArrayList<Cell>>(cols);
		for(int i=0;i<cols;++i) {
			cells.add(new ArrayList<Cell>(rows));
			for(int j=0; j<rows;++j) {
				cells.get(i).add(new CellFree());
			}
		}
	}
		
		
	////// Methods //////
	
	public ArrayList<ArrayList<Cell>> getCells(){
		return cells;
	}

	/**
	 * Getter of the number of columns of the board
	 * 
	 * @return The number of columns
	 */
	public int getCols() {
		return cols;
	}
	
	/**
	 * Getter of the number of rows of the board
	 * 
	 * @return The number of rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Getter of a cell of the board 
	 * 
	 * @param p The position of the cell we want
	 * @return The cell
	 */
	public Cell getCell(Position p) {
		return cells.get(p.getCol()).get(p.getRow());
	}
	
	/**
	 * Getter of the type of a cell of the board 
	 * 	Uses getCell
	 * 
	 * @param p The position of the cell we want
	 * @return The character indicating the type of the cell 
	 */
	public char getCellType(Position p) {
		return getCell(p).getSymbol();
	}
	/**
	 * Initialize the board
	 * Put	CellWhite where the players doesn't go,
	 * 		CellFree where they can go,
	 * 		CellSide around the board,
	 * 		CellStart, CellFinish and CellStable of each players,
	 * 		CellRiver, CellHole and CellHedge according to the board we where given in class
	 * 
	 * @param riders The list of riders
	 */
	public void init(List<Rider> riders,boolean initObstacles) {
		//CellSide initialization
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
		
		//CellWhite initialization
		for(int i=1; i<cols-1;i++) {
			changeCell(' ',new Position(i,2));
			changeCell(' ',new Position(i,4));
		}
		for(int i=riders.get(0).getEnd().getCol();i<riders.get(1).getEnd().getCol();++i) {
			changeCell(' ',new Position(i,(int)(rows/2)));
		}
		
		if(initObstacles) {
			//Special cells initialization
			//Hole
			changeCell('@',new Position(4,1));
			//Hedge
			changeCell('|',new Position(5,5));
			//River
			changeCell('~',new Position(10,5));
		}
		
		//Stable
		changeCell('=',new Position(1,0));
		changeCell('=',new Position(cols-2,rows-1));
		//Start 
		changeCell('#',new Position(1,1));
		changeCell('#',new Position(cols-2,rows-2));
		//End
		changeCell('*',new Position(riders.get(0).getEnd().getCol(),riders.get(0).getEnd().getRow()));
		changeCell('*',new Position(riders.get(1).getEnd().getCol(),riders.get(1).getEnd().getRow()));
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
				cell=new CellHole();
				break;
			case '|':
				cell=new CellHedge();
				break;
			case '~':
				cell=new CellRiver();
				break;
			case '+':
				cell=new CellSide();
				break;
			case ' ':
				cell=new CellWhite();
				break;
			case '#':
				cell=new CellStart();
				break;
			case '=':
				cell=new CellStable();
				break;
			case '*':
				cell=new CellFinish();
				break;
			default:
				cell=new CellFree();
				break;
		}
		cells.get(from.getCol()).set(from.getRow(), cell);
	}
	
	
	/**
	 * Move a rider from a cell to another,
	 * Checks if the new position is valid then move the rider to it
	 * 
	 * @param r		The rider we want to move
	 * @param from	The position of the rider
	 * @param to	The position where the rider will be moved
	 */
	public void move(Rider r,Position from, Position to) {
		if(from.isValid(rows,cols) && to.isValid(rows,cols) && getCell(to).isPlayable()) {
			r.move(to);
		}
	}
	
	/**
	 * Allow to move the rider of several cases according to his path,
	 * First it checks what player has to move since they don't have the same path,
	 * Then while the rider hasn't moved of the number of cell he has to
	 * 		it checks where it is and move it to the next cell 
	 * 
	 * @param r	The rider we want to move
	 * @param n The number of cases 
	 */
	public void followPath(Rider r, int n) {
		Position init_rpos=r.getPos();
		if(r.getColor()==Couleur.RED) {
			//Red Rider
			while(n!=0) {
				int x=r.getPos().getCol();
				int y=r.getPos().getRow();
				//Up
				if(x<cols-2 && y==1) {
					move(r,r.getPos(),new Position(x+1,y));
					n--;
				}
				//Right
				if(x==cols-2 && y<rows-2) {
					move(r,r.getPos(),new Position(x,y+2));
					n--;
				}
				//Down
				if(x>1 && y==rows-2) {
					move(r,r.getPos(),new Position(x-1,y));
					n--;
				}
				//Left
				if(x==1 && y>3) {
					move(r,r.getPos(),new Position(x,y-2));
					n--;
				}
				//End Line
				if(y==3 && x<r.getEnd().getCol()) {
					move(r,r.getPos(),new Position(x+1,y));
					n--;
				}
				//End Position
				if(getCell(r.getPos()).getSymbol()=='*') {
					if(n!=0) {
						move(r,r.getPos(),init_rpos);
					}
					break;
				}
			}
		}else {
			//Blue Rider
			while(n!=0) {
				int x=r.getPos().getCol();
				int y=r.getPos().getRow();
				//Down
				if(x>1 && y==rows-2) {
					move(r,r.getPos(),new Position(x-1,y));
					n--;
				}
				//Left
				if(x==1 && y>1) {
					move(r,r.getPos(),new Position(x,y-2));
					n--;
				}
				//Up
				if(x<cols-2 && y==1) {
					move(r,r.getPos(),new Position(x+1,y));
					n--;
				}
				//Right
				if(x==cols-2 && y<3) {
					move(r,r.getPos(),new Position(x,y+2));
					n--;
				}
				//End line
				if(y==3 && x>r.getEnd().getCol()) {
					move(r,r.getPos(),new Position(x-1,y));
					n--;
				}
				//End Position
				if(getCell(r.getPos()).getSymbol()=='*') {
					if(n!=0) {
						move(r,r.getPos(),init_rpos);
					}
					break;
				}
			}
		}
	}
	
	/**
	 * Allows to represent the board of the game,
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
				//Red on the board
				if(r1.getPos().equals(new Position(j,i))) {
					str+=" R ";
				}else {
					//Blue on the board
					if(r2.getPos().equals(new Position(j,i))) {
						str+=" B ";
					}else {
						//Other cells
						str+=" "+cells.get(j).get(i).getSymbol()+" ";
					}
				}
			}
			str+="\n";
		}
		return str;
	}

}
