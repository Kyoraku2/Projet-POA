package vue;


import javax.swing.*;
import javax.swing.border.*;

import controleurs.*;
import modele.*;
import java.awt.*;

/*
 * Ranger l'ordre des fonctions
 * Ranger l'intérieur des fonctions
 * JavaDoc + commentaires
 * Rapport
 * Revoir mise en forme premier menu + menu partie simple
 */

/**
 * Window class
 * @author Marie-Almina
 * @author Tayeb
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
	
//// Constructor /////

	/**
	 * Constructor of window,
	 * Create all the elements of the game window
	 * 
	 */
	public Window(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnListener list = new btnListener(this);
		setSize(500,200);
		setTitle("Petits Chevaux");
		c=this.getContentPane();		
		c.setLayout(new BorderLayout());
		
///// Game type menu ////
		
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

//// Simple games ////
		
		pnlDif=new JPanel();
		pnlDif.setBackground(Color.LIGHT_GRAY);
		
		JLabel txtDifLab=new JLabel("Choisir la difficultée :");
		txtDifLab.setOpaque(true);
		txtDifLab.setBackground(Color.LIGHT_GRAY);
		
		//To chose the difficulty
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
		
		//to go back to the menu
		btnBackSimple=new JButton("Retour au menu");
		btnBackSimple.addActionListener(list);
		//to play a game
		btnPlaySimple=new JButton("Jouer");
		btnPlaySimple.addActionListener(list);
		btnPlaySimple.setEnabled(false);
		
		pnlBtnSimple.setLayout(new FlowLayout());

		pnlBtnSimple.add(btnBackSimple);
		pnlBtnSimple.add(btnPlaySimple);
		
		
		
//// Personalized games ////
		
		
		pnlSizePerso=new JPanel();
		pnlSizePerso.setBackground(Color.LIGHT_GRAY);
		JTextArea TxtExplainPerso = new JTextArea("Veuillez choisir une taille pour votre plateau : \n\nAppuyer sur une case pour changer son type. ");
		TxtExplainPerso.setBackground(Color.LIGHT_GRAY);
		TxtExplainPerso.setEditable(false);
		//to chose the size of the window
		selectSize=new JComboBox<Integer>();
		for(int i=10;i<32;++i) {
			selectSize.addItem(i);
		}
		//to validate this size
		btnBoxValidate=new JButton("Valider");
		btnBoxValidate.addActionListener(list);
		pnlSizePerso.add(TxtExplainPerso);
		pnlSizePerso.add(selectSize);
		pnlSizePerso.add(btnBoxValidate);
		//to go back to the game type menu
		btnBackPerso=new JButton("Retour au menu");
		btnBackPerso.addActionListener(list);
		//to play a game
		btnPlayPerso=new JButton("Jouer");
		btnPlayPerso.addActionListener(list);
		btnPlayPerso.setEnabled(false);
		btnPlayPerso.setVisible(true);
		
		pnlBtnPerso=new JPanel();
		pnlBtnPerso.setBackground(Color.LIGHT_GRAY);
		pnlBtnPerso.setLayout(new FlowLayout());
		
		pnlBtnPerso.add(btnBackPerso);
		pnlBtnPerso.add(btnPlayPerso);

		
//// Game  ////
		
	// UP
		pnlPlayUp.setBackground(Color.LIGHT_GRAY);
		pnlPlayUp.setSize(this.getWidth(),100);
		pnlPlayUp.setLayout(new GridLayout(2,1));
		
		//text to know with player played and what he did
		txtTurn=new JTextField();
		txtTurn.setEditable(false);
		txtTurn.setBackground(Color.LIGHT_GRAY);
		txtTurn.setBorder(null);
		txtTurn.setHorizontalAlignment(JTextField.CENTER);
		
		pnlPlayUpBtn.setBackground(Color.LIGHT_GRAY);	
		//to roll a dice
		btnRoll=new JButton("Rouler le dé");
		btnRoll.addActionListener(list);
		//To chose the value instead
		JLabel txtStepAsk =new JLabel("        Saisir valeur Dé :");
		txtStep=new JTextField(3);
		txtStep.addActionListener(list);	
		
		pnlPlayUpBtn.add(btnRoll);
		pnlPlayUpBtn.add(txtStepAsk);
		pnlPlayUpBtn.add(txtStep);
		
		pnlPlayUp.add(txtTurn);
		pnlPlayUp.add(pnlPlayUpBtn);
		
		
	// CENTER
		
		// All the element of the center pannel are created and updated in the listener, GraphicCell or GraphicBoard
		
	// DOWN
		pnlPlayDown.setBackground(Color.LIGHT_GRAY);
		pnlPlayUp.setSize(this.getWidth(),100);
		
		//text of the process functions
		txtProcess=new JTextArea(2, 1);
		txtProcess.setEditable(false);
		txtProcess.setBorder(null);
		txtProcess.setBackground(Color.LIGHT_GRAY);

		pnlPlayDown.add(txtProcess);
		
///// END MENU ////
		
		pnlEndMenu=new JPanel();
		pnlEndMenu.setLayout(new FlowLayout());
		pnlEndMenu.setBackground(Color.LIGHT_GRAY);
		pnlEndMenu.setLayout(new BorderLayout());
		// To replay
		btnReplay = new JButton("Rejouer");
		btnReplay.addActionListener(list);
		// To quit
		btnQuit = new JButton("Quitter");
		btnQuit.addActionListener(list);
		// Text to know who is the winner
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
	
///// Methods /////
	
	/**
	 * Getter of the button allowing to quit when the party is finished
	 * 
	 * @return The quit button
	 */
	public JButton getBtnQuit() {
		return btnQuit;
	}
	/**
	 * Getter of the text showing the winner
	 * 
	 * @return The text of the end menu
	 */
	public JTextArea getTxtWinner() {
		return txtWinner;
	}
	/**
	 * Getter of the panel where all the button from the simple game menu are
	 * 
	 * @return The panel containing the simple menu's buttons
	 */
	public JPanel getPnlSimpleBtn() {
		return pnlBtnSimple;
	}
	/**
	 * Getter of the panel where all the button from the perso game menu are
	 * 
	 * @return The panel containing the perso menu's buttons
	 */
	public JPanel getPnlPersoBtn() {
		return pnlBtnPerso;
	}
	/**
	 * Getter of the panel of the end menu
	 * 
	 * @return The panel of the end menu
	 */
	public JPanel getEndMenu() {
		return pnlEndMenu;
	}
	/**
	 * Getter of the button allowing to go back from the simple menu to the game type one
	 * 
	 * @return The return button of simple
	 */
	public JButton getBtnBackSimple() {
		return btnBackSimple;
	}
	/**
	 * Getter of the button allowing to go back from the perso menu to the game type one
	 * 
	 * @return The return button of perso
	 */
	public JButton getBtnBackPerso() {
		return btnBackPerso;
	}
	/**
	 * Getter of the button to validate the length of the board in personalized games
	 * 
	 * @return The validate button 
	 */
	public JButton getBoxValidate() {
		return btnBoxValidate;
	}
	/**
	 * Getter of the button to validate the length of the board in personalized games
	 * 
	 * @return The validate button 
	 */
	public JPanel getSizePanel() {
		return pnlSizePerso;
	}
	/**
	 * Getter of the select box allowing to chose the size of the board
	 * 
	 * @return The select box
	 */
	public JComboBox<Integer> getSelectBox() {
		return selectSize;
	}
	/**
	 * Getter of the panel containing the menu to chose the type of the game
	 * 
	 * @return The panel of the game type menu
	 */
	public JPanel getPnlGameType() {
		return pnlGameType;
	}
	/**
	 * Getter of the button to chose the simple game
	 * 
	 * @return The simple game button
	 */
	public JButton getBtnSimple() {
		return btnSimpleGame;
	}
	/**
	 * Getter of the button to chose the personalized game
	 * 
	 * @return The personalized game button
	 */
	public JButton getBtnPerso() {
		return btnPersoGame;
	}
	/**
	 * Getter of the panel containing the difficulty buttons
	 * 
	 * @return The difficulty panel
	 */
	public JPanel getPnlDif() {
		return pnlDif;
	}
	/**
	 * Getter of the button to play a personalized game
	 * 
	 * @return The play button
	 */
	public JButton getBtnPlayPerso() {
		return btnPlayPerso;
	}
	/**
	 * Getter of the button to play a simple game
	 * 
	 * @return The play button
	 */
	public JButton getBtnPlaySimple() {
		return btnPlaySimple;
	}
	/**
	 * Getter of the button to set the easy difficulty
	 * 
	 * @return The difficulty button
	 */
	public JButton getBtnEasy() {
		return btnEasy;
	}
	/**
	 * Getter of the button to set the normal difficulty
	 * 
	 * @return The difficulty button
	 */
	public JButton getBtnNormal() {
		return btnNormal;
	}
	/**
	 * Getter of the button to set the hard difficulty
	 * 
	 * @return The difficulty button
	 */
	public JButton getBtnHard() {
		return btnHard;
	}
	/**
	 * Getter of the container of the window
	 * 
	 * @return The container of the window
	 */
	public Container getC() {
		return c;
	}
	/**
	 * Getter of the upper panel of the game window
	 * 
	 * @return The panel
	 */
	public JPanel getPnlPlayUp() {
		return pnlPlayUp;
	}
	/**
	 * Getter of the center panel of the game window
	 * 
	 * @return The panel
	 */
	public JPanel getPnlPlayCenter() {
		return pnlPlayCenter;
	}
	/**
	 * Getter of the down panel of the game window
	 * 
	 * @return The panel
	 */
	public JPanel getPnlPlayDown() {
		return pnlPlayDown;
	}
	/**
	 * Getter of the upper panel of the game window
	 * 
	 * @return The panel
	 */
	public JButton getBtnRoll() {
		return btnRoll;
	}
	/**
	 * Getter of the text field to chose the value of the dice
	 * 
	 * @return The text field
	 */
	public JTextField getTxtStep() {
		return txtStep;
	}
	/**
	 * Getter of the chosen value of the dice
	 * 
	 * @return The chosen value
	 */
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
	/**
	 * Getter of the text field that shows which player played
	 * 
	 * @return The text field
	 */
	public JTextField getTxtTurn() {
		return txtTurn;
	}
	/**
	 * Getter of the text field used to show the return of the process functions
	 * 
	 * @return The text field
	 */
	public JTextArea getTxtProcess() {
		return txtProcess;
	}
	/**
	 * Getter of the button to replay when a game is finished
	 * 
	 * @return The replay button
	 */
	public JButton getBtnReplay() {
		return btnReplay;
	}
}
