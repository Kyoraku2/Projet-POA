package modele;

import java.util.*;

public class Board {
	
		//Attributs
		private int cols;
		private int rows;
		private ArrayList<ArrayList<Cell>> cells;
		
		private Position start_red;
		private Position start_blue;
		private Position end_red;
		private Position end_blue;
		
		public Board(int c) {
			rows=7;
			this.cols=c+2;
			cells=new ArrayList<ArrayList<Cell>>(cols);
			
			for(int i=0;i<cols;++i) {
				cells.add(new ArrayList<Cell>(rows));
				for(int j=0; j<rows;++j) {
					if(i==1 && j==1 || i==cols-2 && j==cols-2) {
						cells.get(i).add(new CellFree(new Position(i,j),true));
					}else{
						cells.get(i).add(new CellFree(new Position(i,j)));
					}
				}
			}
			start_red=new Position(1,1);
			start_blue=new Position(cols-2,rows-2);
			
			if(cols%2==0) {
				end_red=new Position((int)(cols/2)-3,(int)(rows/2));
				end_blue=new Position((int)(cols/2)+2,(int)(rows/2));
			}else {
				//end_red=new Position((int)(cols/2)+1-2,(int)(rows/2)+1);
				//end_blue=new Position((int)(cols/2)+1+2,(int)(rows/2)+1);
			}
		}

		//Methods
		public int getCols() {
			return cols;
		}
		
		public int getRows() {
			return rows;
		}
		
		
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
			for(int i=end_red.getCol();i<end_blue.getCol();++i) {
				changeCell(' ',new Position(i,(int)(rows/2)));
			}
			for(int i=1; i<cols-1;i++) {
				changeCell(' ',new Position(i,2));
				changeCell(' ',new Position(i,4));
			}
			changeCell('@',new Position(4,1));
			changeCell('|',new Position(5,5));
			changeCell('~',new Position(10,5));
		}
		
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
				default:
					cell=new CellFree(from);
					break;
			}
			cells.get(from.getCol()).set(from.getRow(), cell);
		}
		

		public boolean removeRider(Position from) {
			((CellPlayable)cells.get(from.getCol()).get(from.getRow())).unOccuped();
			return true;
		}

		public boolean addRider(Position to) {
			((CellPlayable)cells.get(to.getCol()).get(to.getRow())).becomeOccuped();
			return true;
		}
		
		// A finir peut être
		public void move(Rider r,Position from, Position to) {
			if(from.isValid(rows,cols) && to.isValid(rows,cols) && getCell(to).isPlayable()) {
				r.move(to);
				removeRider(from);
				addRider(to);
			}
		}
		
		//A faire
		public void followPath(Rider r, int n) {
			//dissocier rouge and blux
			//chemin
		}
		
		public Cell getCell(Position p) {
			return cells.get(p.getCol()).get(p.getRow());
		}
		
		public char getCellType(Position p) {
			return cells.get(p.getCol()).get(p.getRow()).getSymbole();
		}
		
		public Position getRedStart() {
			return start_red;
		}
		public Position getBlueStart() {
			return start_blue;
		}
		public Position getRedEnd() {
			return end_red;
		}
		public Position getBlueEnd() {
			return end_blue;
		}
		
		public String toString(Rider r1,Rider r2) {
			String str="";
			for(int i=0;i<rows;++i) {
				for(int j=0;j<cols;++j) {
					//str+=" "+cells.get(j).get(i).getSymbole()+" "+cells.get(j).get(i).getPosition().getCol()+" "+cells.get(j).get(i).getPosition().getRow()+" ";
					if(start_red.equals(new Position(j,i)) || start_blue.equals(new Position(j,i))) {
						str+=" # ";
					}else{
						if(end_red.equals(new Position(j,i)) || end_blue.equals(new Position(j,i))) {
							str+=" * ";
						}else {
							if(r1.getPos().equals(new Position(j,i))) {
								str+=" R ";
							}else {
								if(r2.getPos().equals(new Position(j,i))) {
									str+=" B ";
								}else {
									str+=" "+cells.get(j).get(i).getSymbole()+" ";
								}
							}
						}
					}
				}
				str+="\n";
			}
			return str;
		}

}
