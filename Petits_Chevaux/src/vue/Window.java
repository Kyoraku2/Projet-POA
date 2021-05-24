package vue;


import javax.swing.*;
import javax.swing.border.*;

import controleurs.*;
import modele.*;
import java.awt.*;

/*

 * Ranger l'ordre des fonctions
 * Ranger l'intérieur des fonctions
 * Division en plusieurs fonctions si c'est vraiment dégueux
 * JavaDoc + commentaires
 * Rapport
 * Mettre des JLabels dans Personnalisée pour expliquer
 * Revoir mise en forme premier menu + menu partie simple
 * 
 */


public class Window extends JFrame {
	
//// Attributs ////
	
	/**
	 * The container of the window
	 */
	private Container c;
	
	//// Game type menu
	/**
	 * Panel the menu where to chose the game type
	 */
	private JPanel pnlGameType;
	/**
	 * Button to play a simple game
	 */
	private JButton btnSimpleGame;
	/**
	 * Button to play a personalized game
	 */
	private JButton btnPersoGame;
	
	//// Simple game menu
	/**
	 * Panel containing button to chose difficulty
	 */
	private JPanel pnlDif;
	/**
	 * Panel containing the other buttons of the menu
	 */
	private JPanel pnlBtnSimple;
	/**
	 * Button to go back to the choice of game type
	 */
	private JButton btnBackSimple;
	/**
	 * Button to chose the difficulty easy
	 */
	private JButton btnEasy;
	/**
	 * Button to chose the difficulty normal
	 */
	private JButton btnNormal;
	/**
	 * Button to chose the difficulty hard
	 */
	private JButton btnHard;
	/**
	 * Button to play a simple party
	 */
	private JButton btnPlaySimple;

	//// Personalized party
	/**
	 * Button to go back to the game type menu
	 */
	private JButton btnBackPerso;
	/**
	 * Panel containing the element to chose the size of the board
	 */
	private JPanel pnlSizePerso;
	/**
	 * To chose the size of the board
	 */
	private JComboBox<Integer> selectSize;
	/**
	 * Button to validate the chosen sized
	 */
	private JButton btnBoxValidate;
	/**
	 * Button to play a personalized game
	 */
	private JButton btnPlayPerso;
	/**
	 * Panel containing the button of the perso menu
	 */
	private JPanel pnlBtnPerso;
	
	//// Game window
	/**
	 * The up panel of the game, contains the button to roll the dice and text to know what the player did
	 */
	private JPanel pnlPlayUp = new JPanel();
	/**
	 * Panel containing the button of the up panel of the game window
	 */
	private JPanel pnlPlayUpBtn = new JPanel();
	/**
	 * Panel containing the board
	 */
	private JPanel pnlPlayCenter = new JPanel();
	/**
	 * Panel containing text to indicate the status of the player
	 */
	private JPanel pnlPlayDown = new JPanel();
	/**
	 * Button to roll a dice
	 */
	private JButton btnRoll;
	/**
	 * Text field allowing to chose manually the value of the dice 
	 */
	private JTextField txtStep;
	/**
	 * Text field showing what player is playing
	 */
	private JTextField txtTurn;
	/**
	 * Text field containing the value of the process function, indicates the status of the player
	 */
	private JTextArea txtProcess;
	
	//// End menu
	/**
	 * Panel containing the end menu
	 */
	private JPanel pnlEndMenu;
	/**
	 * Button allowing to replay a game
	 */
	private JButton btnReplay;
	/**
	 * Button to quit the application
	 */
	private JButton btnQuit;
	/**
	 * Text field showing the winner of the game
	 */
	private JTextArea txtWinner;
	

	public Window(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnListener list = new btnListener(this);
		setSize(700,400);
		setTitle("Petits Chevaux");
		c=this.getContentPane();		
		c.setLayout(new BorderLayout());
		
///// Choix type de partie ////
		
		pnlGameType=new JPanel();
		pnlGameType.setLayout(new FlowLayout());
		pnlGameType.setBackground(Color.LIGHT_GRAY);
		
		JLabel txtChoseGame=new JLabel("Choisir votre type de partie : ");
		
		btnSimpleGame = new JButton("Simple");
		btnSimpleGame.addActionListener(list);
		
		btnPersoGame = new JButton("Personnalisée");
		btnPersoGame.addActionListener(list);
		
		pnlGameType.add(txtChoseGame);
		pnlGameType.add(btnSimpleGame);
		pnlGameType.add(btnPersoGame);
		
		c.add(pnlGameType);

//// Parties simple ////
		
		pnlDif=new JPanel();
		pnlDif.setBackground(Color.LIGHT_GRAY);
		
		JLabel txtDifLab=new JLabel("Choisir la difficultée :");
		txtDifLab.setOpaque(true);
		txtDifLab.setBackground(Color.LIGHT_GRAY);
		
		//Boutons de choix de la difficulté
		btnEasy=new JButton("Facile");
		btnEasy.addActionListener(list);
		btnNormal=new JButton("Normal");
		btnNormal.addActionListener(list);
		btnHard=new JButton("Difficile");
		btnHard.addActionListener(list);
		
		pnlDif.add(txtDifLab);
		pnlDif.add(btnEasy);
		pnlDif.add(btnNormal);
		pnlDif.add(btnHard);
		
		pnlBtnSimple=new JPanel();
		pnlBtnSimple.setBackground(Color.LIGHT_GRAY);
		
		btnBackSimple=new JButton("Retour au menu");
		btnBackSimple.addActionListener(list);
		btnPlaySimple=new JButton("Jouer");
		btnPlaySimple.addActionListener(list);
		btnPlaySimple.setEnabled(false);
		
		pnlBtnSimple.setLayout(new FlowLayout());

		pnlBtnSimple.add(btnBackSimple);
		pnlBtnSimple.add(btnPlaySimple);
		
		
		
//// Parties personnalisée ////
		
		//Classe Graphic board 	
		pnlSizePerso=new JPanel();
		pnlSizePerso.setBackground(Color.LIGHT_GRAY);
		JTextArea TxtExplainPerso = new JTextArea("Veuillez choisir une taille pour votre plateau : \n\nAppuyer sur une case pour changer son type. ");
		TxtExplainPerso.setBackground(Color.LIGHT_GRAY);
		TxtExplainPerso.setEditable(false);
		selectSize=new JComboBox<Integer>();
		for(int i=10;i<32;++i) {
			selectSize.addItem(i);
		}
		btnBoxValidate=new JButton("Valider");
		btnBoxValidate.addActionListener(list);
		pnlSizePerso.add(TxtExplainPerso);
		pnlSizePerso.add(selectSize);
		pnlSizePerso.add(btnBoxValidate);
		
		
		btnBackPerso=new JButton("Retour au menu");
		btnBackPerso.addActionListener(list);
		
		btnPlayPerso=new JButton("Jouer");
		btnPlayPerso.addActionListener(list);
		btnPlayPerso.setEnabled(false);
		btnPlayPerso.setVisible(true);
		
		pnlBtnPerso=new JPanel();
		pnlBtnPerso.setBackground(Color.LIGHT_GRAY);
	
		pnlBtnPerso.setLayout(new FlowLayout());
		
		pnlBtnPerso.add(btnBackPerso);
		pnlBtnPerso.add(btnPlayPerso);

		
//// Fenetre de jeu ////
		
	// HAUT 
		pnlPlayUp.setBackground(Color.LIGHT_GRAY);
		pnlPlayUp.setSize(this.getWidth(),100);
		pnlPlayUp.setLayout(new GridLayout(2,1));
		
		//Text indiquant le joueur du tour
		txtTurn=new JTextField();
		txtTurn.setEditable(false);
		txtTurn.setBackground(Color.LIGHT_GRAY);
		txtTurn.setBorder(null);
		txtTurn.setHorizontalAlignment(JTextField.CENTER);
		
		//partie des boutons
		pnlPlayUpBtn.setBackground(Color.LIGHT_GRAY);	
		
		btnRoll=new JButton("Rouler le dé");
		btnRoll.addActionListener(list);

		//Choix de valeur du dé par utilisateur
		JLabel txtStepAsk =new JLabel("        Saisir valeur Dé :");
		txtStep=new JTextField(3);
		txtStep.addActionListener(list);	
		
		pnlPlayUpBtn.add(btnRoll);
		pnlPlayUpBtn.add(txtStepAsk);
		pnlPlayUpBtn.add(txtStep);
		
		pnlPlayUp.add(txtTurn);
		pnlPlayUp.add(pnlPlayUpBtn);
		
		
	// CENTRE
		
		// Dans listener
		
	// BAS
		pnlPlayDown.setBackground(Color.LIGHT_GRAY);
		pnlPlayUp.setSize(this.getWidth(),100);
		
		//texte de process
		txtProcess=new JTextArea(2, 1);
		txtProcess.setEditable(false);
		txtProcess.setBorder(null);
		txtProcess.setBackground(Color.LIGHT_GRAY);

		pnlPlayDown.add(txtProcess);
		
///// MENU FIN ////
		
		pnlEndMenu=new JPanel();
		pnlEndMenu.setLayout(new FlowLayout());
		pnlEndMenu.setBackground(Color.LIGHT_GRAY);
		
		pnlEndMenu.setLayout(new BorderLayout());
		
		btnReplay = new JButton("Rejouer");
		btnReplay.addActionListener(list);
		btnQuit = new JButton("Quitter");
		btnQuit.addActionListener(list);
		
		txtWinner = new JTextArea(2,1);
		txtWinner.setEditable(false);
		txtWinner.setBorder(null);
		txtWinner.setBackground(Color.LIGHT_GRAY);
		
		JPanel pnlEndMenuBtn = new JPanel();
		pnlEndMenuBtn.setLayout(new FlowLayout());
		pnlEndMenuBtn.setBackground(Color.LIGHT_GRAY);;
		
		pnlEndMenuBtn.add(btnReplay);
		pnlEndMenuBtn.add(btnQuit);
		
		JPanel pnlWinner = new JPanel();
		pnlWinner.setBackground(Color.LIGHT_GRAY);
		pnlWinner.setSize(this.getWidth(),100);
		
		pnlWinner.add(txtWinner);
		
		pnlEndMenu.add(pnlWinner,BorderLayout.NORTH);
		pnlEndMenu.add(pnlEndMenuBtn,BorderLayout.CENTER);
		
	}
	
	
	public JButton getBtnQuit() {
		return btnQuit;
	}
	
	public JTextArea getTxtWinner() {
		return txtWinner;
	}
	
	public JPanel getPnlSimpleBtn() {
		return pnlBtnSimple;
	}
	public JPanel getPnlPersoBtn() {
		return pnlBtnPerso;
	}
	
	public JPanel getEndMenu() {
		return pnlEndMenu;
	}
	
	public JButton getBtnBackSimple() {
		return btnBackSimple;
	}
	
	public JButton getBtnBackPerso() {
		return btnBackPerso;
	}
	
	public JButton getBoxValidate() {
		return btnBoxValidate;
	}
	
	public JPanel getSizePanel() {
		return pnlSizePerso;
	}
	
	public JComboBox<Integer> getSelectBox() {
		return selectSize;
	}
	
	public JPanel getPnlGameType() {
		return pnlGameType;
	}
	
	public JButton getBtnSimple() {
		return btnSimpleGame;
	}
	
	public JButton getBtnPerso() {
		return btnPersoGame;
	}
	
	public JPanel getPnlDif() {
		return pnlDif;
	}
	
	public JButton getBtnPlayPerso() {
		return btnPlayPerso;
	}
	
	public JButton getBtnPlaySimple() {
		return btnPlaySimple;
	}
	
	public JButton getBtnEasy() {
		return btnEasy;
	}
	
	public JButton getBtnNormal() {
		return btnNormal;
	}
	
	public JButton getBtnHard() {
		return btnHard;
	}
	
	public Container getC() {
		return c;
	}
	
	public JPanel getPnlPlayUp() {
		return pnlPlayUp;
	}
	
	public JPanel getPnlPlayCenter() {
		return pnlPlayCenter;
	}
	
	public JPanel getPnlPlayDown() {
		return pnlPlayDown;
	}
	
	public JButton getBtnRoll() {
		return btnRoll;
	}
	
	public JTextField getTxtStep() {
		return txtStep;
	}
	
	public int getStepValue() {
		int res;
		try {
			res = Integer.parseInt(txtStep.getText());
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
	
	public JButton getBtnReplay() {
		return btnReplay;
	}
}
