package vue;

import javax.swing.*;
import modele.*;
import java.awt.*;

public class GraphicCell{
	
	private JPanel cell=new JPanel();
	private JLabel player;
	
	public GraphicCell(Cell c){
		
		cell.setSize(50,50);
		
		player=new JLabel(" du ");
		player.setBorder(null);
		//player.setEditable(false);
		player.setHorizontalAlignment(JTextField.CENTER);
		player.setAlignmentX(50/2);

		
		//cell.add(player,BorderLayout.CENTER);
		cell.add(player,"Center");
		
		
		
		
		initialize(c);
		
	}
	
	public void initialize(Cell c) {
		if(c instanceof CellSide) {
			cell.setBackground(Color.GREEN);
		}
		if(c instanceof CellWhite) {
			cell.setBackground(Color.WHITE);
		}
		
		if(c instanceof CellFinish) {
			cell.setBackground(Color.CYAN);
		}
		if(c instanceof CellStable) {
			cell.setBackground(Color.ORANGE);
		}
		if(c instanceof CellStart) {
			cell.setBackground(Color.RED);
		}
		
		if(c instanceof CellHedge) {
			cell.setBackground(Color.DARK_GRAY);
		}
		if(c instanceof CellHole) {
			cell.setBackground(Color.YELLOW);
		}
		if(c instanceof CellRiver) {
			cell.setBackground(Color.BLUE);
		}

		if(c instanceof CellFree) {
			cell.setBackground(Color.GRAY);
		}
	}
	
	public JPanel getCell() {
		return cell;
	}
	
}
