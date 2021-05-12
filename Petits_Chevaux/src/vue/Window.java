package vue;


import javax.swing.*;


import controleurs.*;
import modele.*;
import java.awt.*;

public class Window extends JFrame {
	

	
	private JButton roll; 
	private JTextField txtTurn;
	private JTextField txtProcess;
	
	private JPanel up = new JPanel();
	private JPanel center = new JPanel();
	private JPanel down = new JPanel();
	
	

	public Window(){
		
		btnListener list = new btnListener(this);
		
	
		setTitle("Petits Chevaux");
		Container c=this.getContentPane();		
		c.setLayout(new BorderLayout());

/////HAUT 
		
		up.setBackground(Color.LIGHT_GRAY);
		up.setSize(this.getWidth(),100);
		up.setLayout(new BoxLayout(up,BoxLayout.Y_AXIS));
		
		roll=new JButton("Rouler le dé");
		roll.setAlignmentX(CENTER_ALIGNMENT);
		
		txtTurn=new JTextField();
		txtTurn.setEditable(false);
		txtTurn.setBackground(Color.LIGHT_GRAY);
		txtTurn.setBorder(null);

		
		up.add(txtTurn);
		up.add(roll);
		
		
////CENTER

		
////DOWN
		down.setBackground(Color.LIGHT_GRAY);
		down.setSize(this.getWidth(),100);
		
		txtProcess=new JTextField();
		txtProcess.setEditable(false);
		txtProcess.setBorder(null);
		txtProcess.setBackground(Color.LIGHT_GRAY);

		
		down.add(txtProcess);
		
////reste
		c.add(up,BorderLayout.NORTH);
		c.add(center,BorderLayout.CENTER);
		c.add(down,BorderLayout.SOUTH);

		
		
		
	}
	
	
	public JPanel getCenter() {
		return center;
	}
	

	
	
}
