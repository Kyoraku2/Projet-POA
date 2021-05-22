package vue;


import javax.swing.*;
import javax.swing.border.*;

import controleurs.*;
import modele.*;
import java.awt.*;

/*
 * A faire avant de rendre :
 * Menu de fin
 * Fonctions dans actionPerformed dans le listener
 * Renommer un max de variables qui sont nommées comme de la merde
 * Ranger l'ordre des fonctions
 * Ranger l'intérieur des fonctions
 * Division en plusieurs fonctions si c'est vraiment dégueux
 * JavaDoc + commentaires
 * Rapport
 * Mettre des JLabels dans Personnalisée pour expliquer
 * Revoir mise en forme premier menu + menu partie simple
 * 
 * Bonus : boutons de retour
 */


public class Window extends JFrame {
	
	private Container c;
	
	//dans menu principal
	
	private JPanel gameType;
	private JButton simpleGame;
	private JButton persoGame;
	
	//dans partie simple
	
	private JPanel dif;
	private JButton easy;
	private JButton normal;
	private JButton hard;
	
	//A renommmé
	private JButton play;
	private JButton playPerso;
	
	//dans partie personalisé
	
	private JPanel size;
	private JComboBox<Integer> selectSize;
	private JButton boxValidate;
	
	//Dans fenetre de jeu
	
	private JPanel up = new JPanel();
	private JPanel up_button = new JPanel();
	private JPanel center = new JPanel();
	private JPanel down = new JPanel();
	
	private JButton roll;
	private JTextField step;
	
	private JTextField txtTurn;
	private JTextArea txtProcess;
	

	public Window(){
		
		btnListener list = new btnListener(this);
		setSize(700,400);
		setTitle("Petits Chevaux");
		c=this.getContentPane();		
		c.setLayout(new BorderLayout());
		
		
///// Choix type de partie ////
		
		gameType=new JPanel();
		gameType.setLayout(new FlowLayout());
		gameType.setBackground(Color.LIGHT_GRAY);
		
		JLabel choseGame=new JLabel("Choisir votre type de partie : ");
		
		simpleGame = new JButton("Simple");
		simpleGame.addActionListener(list);
		
		persoGame = new JButton("Personnalisée");
		persoGame.addActionListener(list);
		
		gameType.add(choseGame);
		gameType.add(simpleGame);
		gameType.add(persoGame);
		
		c.add(gameType);

//// Parties simple ////
		
		dif=new JPanel();
		dif.setBackground(Color.LIGHT_GRAY);
		
		JLabel difLab=new JLabel("Choisir la difficultée :");
		difLab.setOpaque(true);
		difLab.setBackground(Color.LIGHT_GRAY);
		
		//Boutons de choix de la difficulté
		easy=new JButton("Facile");
		easy.addActionListener(list);
		normal=new JButton("Normal");
		normal.addActionListener(list);
		hard=new JButton("Difficile");
		hard.addActionListener(list);
		
		dif.add(difLab);
		dif.add(easy);
		dif.add(normal);
		dif.add(hard);
		
		play=new JButton("Jouer");
		play.addActionListener(list);
		play.setEnabled(false);
		
//// Parties personnalisée ////
		
		//Classe Graphic board 	
		size=new JPanel();
		size.setBackground(Color.LIGHT_GRAY);
		JLabel sizeLab = new JLabel("Veuillez choisir une taille pour votre plateau : ");
		selectSize=new JComboBox<Integer>();
		for(int i=10;i<32;++i) {
			selectSize.addItem(i);
		}
		boxValidate=new JButton("Valider");
		boxValidate.addActionListener(list);
		size.add(sizeLab);
		size.add(selectSize);
		size.add(boxValidate);
		
		playPerso=new JButton("Jouer");
		playPerso.addActionListener(list);
		playPerso.setEnabled(false);

		
//// Fenetre de jeu ////
		
	// HAUT 
		up.setBackground(Color.LIGHT_GRAY);
		up.setSize(this.getWidth(),100);
		up.setLayout(new GridLayout(2,1));
		
		//Text indiquant le joueur du tour
		txtTurn=new JTextField();
		txtTurn.setEditable(false);
		txtTurn.setBackground(Color.LIGHT_GRAY);
		txtTurn.setBorder(null);
		txtTurn.setHorizontalAlignment(JTextField.CENTER);
		
		//partie des boutons
		up_button.setBackground(Color.LIGHT_GRAY);	
		
		roll=new JButton("Rouler le dé");
		roll.addActionListener(list);

		//Choix de valeur du dé par utilisateur
		JLabel stepLab =new JLabel("        Saisir valeur Dé :");
		step=new JTextField(3);
		step.addActionListener(list);
		
		up_button.add(roll);
		up_button.add(stepLab);
		up_button.add(step);
		
		up.add(txtTurn);
		up.add(up_button);
		
		
	// CENTRE
		
		// Dans listener
		
	// BAS
		down.setBackground(Color.LIGHT_GRAY);
		down.setSize(this.getWidth(),100);
		
		//texte de process
		txtProcess=new JTextArea(2, 1);
		txtProcess.setEditable(false);
		txtProcess.setBorder(null);
		txtProcess.setBackground(Color.LIGHT_GRAY);

		down.add(txtProcess);
		
	}
	
	public JButton getBoxValidate() {
		return boxValidate;
	}
	
	public JPanel getSizePanel() {
		return size;
	}
	
	public JComboBox<Integer> getSelectBox() {
		return selectSize;
	}
	
	public JPanel getGameType() {
		return gameType;
	}
	
	public JButton getSimple() {
		return simpleGame;
	}
	
	public JButton getPerso() {
		return persoGame;
	}
	
	public JPanel getDif() {
		return dif;
	}
	
	public JButton getPlayPerso() {
		return playPerso;
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

	public JTextArea getTxtProcess() {
		return txtProcess;
	}
	
	
}
