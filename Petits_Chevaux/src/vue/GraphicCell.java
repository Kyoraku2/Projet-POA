package vue;

import javax.swing.*;
import modele.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicCell{
	
	/**
	 * The panel representing a cell on the window
	 */
	private JPanel panel=new JPanel();
	/**
	 * The text containing the player or the dice value
	 */
	private JLabel texte;
	
	/**
	 * Constructor of GraphicCell, according to the cell she must portrays
	 * 
	 * @param c The cell to portray  
	 */
	public GraphicCell(Cell c){
		
		panel.setSize(50,50);
		panel.setLayout(new GridBagLayout());
		
		texte=new JLabel(" ");
		texte.setBorder(null);
		
		panel.add(texte);
		
		initialize(c);
	}
	
	/**
	 * Create a listener that allows us to change the type of the cell 
	 * 	when we click on it 
	 * 
	 * @param game The game currently playing
	 * @param pos The position of the cell to change
	 */
	public void listen(Game game, Position pos) {
		panel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//CellFree became CellHole
				if(game.getBoard().getCell(pos) instanceof CellFree) {
					game.getBoard().changeCell('@', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
				//CellHole became CellRiver
				if(game.getBoard().getCell(pos) instanceof CellHole) {
					game.getBoard().changeCell('~', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
				//CellRiver became CellHedge
				if(game.getBoard().getCell(pos) instanceof CellRiver) {
					game.getBoard().changeCell('|', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
				//CellHedge became CellFree
				//So the user can remove his change
				if(game.getBoard().getCell(pos) instanceof CellHedge) {
					game.getBoard().changeCell('.', pos);
					initialize(game.getBoard().getCell(pos));
					return;
				}
			}
		});
	}
	
	
	/**
	 * Remove the previously added listener
	 */
	public void removeListener(){
		panel.removeMouseListener(panel.getMouseListeners()[0]);
	}
	
	/**
	 * Initialize the GraphicCell according to a Cell
	 * Depending on the type of the cell to portray the color and the border
	 * of the GraphicCell will change
	 * 
	 * @param c The cell used to initialize
	 */
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
	/**
	 * Getter of the panel of the cell
	 * 
	 * @return The panel
	 */
	public JPanel getPanel() {
		return panel;
	}
	/**
	 * Getter of the text field of the cell
	 * 
	 * @return The text field
	 */
	public JLabel getLabel() {
		return texte;
	}
	
}
