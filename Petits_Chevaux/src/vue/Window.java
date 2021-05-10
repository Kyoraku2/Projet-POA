package vue;


import javax.swing.*;
import modele.*;
import java.awt.*;

public class Window extends JFrame {
	
	private int width; 
	private int height; 
	
	private JButton roll; 
	
	

	public Window(){
		
		width=500;
		height=300;
		setSize(width,height);
		setTitle("Petits Chevaux");
		Container c=this.getContentPane();		
		c.setLayout(new BorderLayout());

/////HAUT 
		JPanel up= new JPanel();
		up.setBackground(Color.LIGHT_GRAY);
		
		roll=new JButton("Rouler le dé");
		up.add(roll);
		
		
		
		
////CENTER
		JPanel center= new JPanel();
		center.setBackground(Color.white);
		
		//board.getCols()
		
		center.setLayout(new GridLayout(7,5));

		
////DOWN
		JPanel down= new JPanel();
		down.setBackground(Color.white);
		
		//board.getCols()
		
		
		
////reste
		c.add(up,BorderLayout.NORTH);
		c.add(center,BorderLayout.CENTER);
		c.add(down,BorderLayout.SOUTH);

		
		
		
	}
	
}
