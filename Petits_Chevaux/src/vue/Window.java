package vue;


import javax.swing.*;


import controleurs.*;
import modele.*;
import java.awt.*;

public class Window extends JFrame {
	

	
	private JButton roll; 
	
	private JPanel center= new JPanel();
	
	
	

	public Window(){
		
		btnListener list = new btnListener(this);
		
	
		setTitle("Petits Chevaux");
		Container c=this.getContentPane();		
		c.setLayout(new BorderLayout());

/////HAUT 
		JPanel up= new JPanel();
		up.setBackground(Color.LIGHT_GRAY);
		
		roll=new JButton("Rouler le dé");
		up.add(roll);
		
		
		
		
////CENTER

		center.setBackground(Color.white);		

		

		
////DOWN
		JPanel down= new JPanel();
		down.setBackground(Color.LIGHT_GRAY);
		
		
		
		
////reste
		c.add(up,BorderLayout.NORTH);
		c.add(center,BorderLayout.CENTER);
		c.add(down,BorderLayout.SOUTH);

		
		
		
	}
	
	
	public JPanel getCenter() {
		return center;
	}
	

	
	
}
