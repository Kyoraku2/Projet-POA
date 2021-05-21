package vue;

import javax.swing.*;
import modele.*;
import java.awt.*;

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
	
	public void initialize(Cell c) {
		if(c instanceof CellSide) {
			//cell.setBackground(Color.GREEN);
			cell.setBackground(new Color(23,162,33));
		}
		if(c instanceof CellWhite) {
			cell.setBackground(Color.WHITE);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellFinish) {
			//cell.setBackground(Color.CYAN);
			cell.setBackground(new Color(53,195,255));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellStable) {
			//cell.setBackground(Color.ORANGE);
			cell.setBackground(new Color(204,102,0));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellStart) {
			//cell.setBackground(Color.RED);
			cell.setBackground(new Color(188,0,0));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(c instanceof CellHedge) {
			cell.setBackground(Color.DARK_GRAY);
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellHole) {
			//cell.setBackground(Color.YELLOW);
			cell.setBackground(new Color(255,205,0));
			cell.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		if(c instanceof CellRiver) {
			//cell.setBackground(Color.BLUE);
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
