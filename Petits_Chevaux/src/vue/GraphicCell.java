package vue;

import javax.swing.*;

import modele.*;
import java.awt.*;

public class GraphicCell{
	
	//chnager getCell c unnom de merde
	//mettre un get jlabel get text ui
	//voila :) je t'aime <2
	
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
	
	public void initialize(Cell c) {
		if(c instanceof CellSide) {
			cell.setBackground(Color.GREEN);
		}
		if(c instanceof CellWhite) {
			cell.setBackground(Color.WHITE);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellFinish) {
			cell.setBackground(Color.CYAN);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellStable) {
			cell.setBackground(Color.ORANGE);
		}
		if(c instanceof CellStart) {
			cell.setBackground(Color.RED);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellHedge) {
			cell.setBackground(Color.DARK_GRAY);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellHole) {
			cell.setBackground(Color.YELLOW);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellRiver) {
			cell.setBackground(Color.BLUE);
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
