package vue;


import javax.swing.*;
import javax.swing.border.*;

import controleurs.*;
import modele.*;
import java.awt.*;

public class Window extends JFrame {
	
	private Container c;
	
	private JButton play;
	
	private JPanel dif;
	private JButton easy;
	private JButton normal;
	private JButton hard;
	
	private JPanel up = new JPanel();
	private JPanel up_button = new JPanel();
	private JPanel center = new JPanel();
	private JPanel down = new JPanel();
	
	private JButton roll;
	private JTextField step;
	
	private JTextField txtTurn;
	private JTextField txtProcess;
	
	
	

	public Window(){
		
		btnListener list = new btnListener(this);
		
		setSize(700,400);
		setTitle("Petits Chevaux");
		c=this.getContentPane();		
		c.setLayout(new BorderLayout());
		
////// MENU ////
		
		play=new JButton("Jouer");
		play.addActionListener(list);
		play.setEnabled(false);
		
		
		dif=new JPanel();
		/*
		dif.setBackground(Color.LIGHT_GRAY);
		dif.setSize(this.getWidth(),100);
		dif.setLayout(new FlowLayout());
		*/
		
		easy=new JButton("Facile");
		easy.addActionListener(list);
		normal=new JButton("Normal");
		normal.addActionListener(list);
		hard=new JButton("Difficile");
		hard.addActionListener(list);
		
		dif.add(easy);
		dif.add(normal);
		dif.add(hard);
		
		
		c.add(play,BorderLayout.NORTH);
		c.add(dif,BorderLayout.SOUTH);
		
		
///// JEU////
///// HAUT 
		
		up.setBackground(Color.LIGHT_GRAY);
		up.setSize(this.getWidth(),100);
		up.setLayout(new GridLayout(2,1));
		
		up_button.setBackground(Color.LIGHT_GRAY);	
		
		roll=new JButton("Rouler le dé");
		roll.addActionListener(list);

		JLabel stepLab =new JLabel("        Saisir valeur Dé :");
		
		step=new JTextField(3);
		//step.setMinimumSize(new Dimension(30,10));
		step.addActionListener(list);
		
		txtTurn=new JTextField();
		txtTurn.setEditable(false);
		txtTurn.setBackground(Color.LIGHT_GRAY);
		txtTurn.setBorder(null);
		txtTurn.setHorizontalAlignment(JTextField.CENTER);
		
		up.add(txtTurn);
		up.add(up_button);
		up_button.add(roll);
		up_button.add(stepLab);
		up_button.add(step);
		
//// CENTER

		
//// DOWN
		down.setBackground(Color.LIGHT_GRAY);
		down.setSize(this.getWidth(),100);
		
		txtProcess=new JTextField("                                                         ");
		txtProcess.setEditable(false);
		txtProcess.setBorder(null);
		txtProcess.setBackground(Color.LIGHT_GRAY);

		
		down.add(txtProcess);
		

		
		
	}
	
	public JPanel getDif() {
		return dif;
	}
	
	public JButton getPlay() {
		return play;
	}
	
	public JButton getEasy() {
		return easy;
	}
	
	public JButton getNormal() {
		return normal;
	}
	
	public JButton getHard() {
		return hard;
	}
	
	public Container getC() {
		return c;
	}
	
	public JPanel getUp() {
		return up;
	}
	
	public JPanel getCenter() {
		return center;
	}
	
	public JPanel getDown() {
		return down;
	}
	
	public JButton getRoll() {
		return roll;
	}
	
	public JTextField getStep() {
		return step;
	}
	
	public int getStepValue() {
		int res;
		try {
			res = Integer.parseInt(step.getText());
		}
		
		catch (NumberFormatException e) {
			res=0;
		}
		return res;
	}
	
	public JTextField getTxtTurn() {
		return txtTurn;
	}

	public JTextField getTxtProcess() {
		return txtProcess;
	}
	
	
}
