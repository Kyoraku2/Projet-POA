package vue;

import javax.swing.*;
import modele.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicCell{
	
	
	private JPanel cell=new JPanel();
	private JLabel texte;
	
	public GraphicCell(Cell c){
		
		cell.setSize(50,50);
		cell.setLayout(new GridBagLayout());
		
		texte=new JLabel(" ");
		texte.setBorder(null);
		
		cell.add(texte);
		
		initialize(c);
	}
	
	public void listen(Game game, Position pos) {
		cell.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(game.getBoard().getCell(pos) instanceof CellFree) {
					game.getBoard().changeCell('@', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
				if(game.getBoard().getCell(pos) instanceof CellHole) {
					game.getBoard().changeCell('~', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
				if(game.getBoard().getCell(pos) instanceof CellRiver) {
					game.getBoard().changeCell('|', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
				if(game.getBoard().getCell(pos) instanceof CellHedge) {
					game.getBoard().changeCell('.', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
			}
		});
	}
	
	public void initialize(Cell c) {
		if(c instanceof CellSide) {
			cell.setBackground(new Color(23,162,33));
		}
		if(c instanceof CellWhite) {
			cell.setBackground(Color.WHITE);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellFinish) {
			cell.setBackground(new Color(53,195,255));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellStable) {
			cell.setBackground(new Color(204,102,0));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellStart) {
			cell.setBackground(new Color(188,0,0));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellHedge) {
			cell.setBackground(Color.DARK_GRAY);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellHole) {
			cell.setBackground(new Color(255,205,0));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellRiver) {
			cell.setBackground(new Color(78,78,255));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}

		if(c instanceof CellFree) {
			cell.setBackground(Color.GRAY);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	
	public JPanel getCell() {
		return cell;
	}
	
	public void setLabel(String str) {
		texte.setText(str);
	}
	
}
