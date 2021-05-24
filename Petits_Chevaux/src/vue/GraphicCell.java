package vue;

import javax.swing.*;
import modele.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicCell{
	
	
	private JPanel panel=new JPanel();
	private JLabel texte;
	
	public GraphicCell(Cell c){
		
		panel.setSize(50,50);
		panel.setLayout(new GridBagLayout());
		
		texte=new JLabel(" ");
		texte.setBorder(null);
		
		panel.add(texte);
		
		initialize(c);
	}
	
	public void listen(Game game, Position pos) {
		panel.addMouseListener(new MouseAdapter(){
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
			panel.setBackground(new Color(23,162,33));
		}
		if(c instanceof CellWhite) {
			panel.setBackground(Color.WHITE);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellFinish) {
			panel.setBackground(new Color(53,195,255));
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellStable) {
			panel.setBackground(new Color(204,102,0));
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellStart) {
			panel.setBackground(new Color(188,0,0));
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellHedge) {
			panel.setBackground(Color.DARK_GRAY);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellHole) {
			panel.setBackground(new Color(255,205,0));
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellRiver) {
			panel.setBackground(new Color(78,78,255));
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}

		if(c instanceof CellFree) {
			panel.setBackground(Color.GRAY);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public JLabel getLabel() {
		return texte;
	}
	
}
