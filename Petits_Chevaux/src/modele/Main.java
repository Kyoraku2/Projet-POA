package modele;

/** 
 * Game of "Petits Chevaux"
 * 
 * @author Tayeb
 * @author Marie-Almina
 
 */
public class Main {
	
	/**
	   * Main program
	   *
	   * @param args Array of parameters 
	   */
	public static void main(String[] args) {
		
		//Create a new game with a board of the size given in args 
		// Or of size 16 if no arguments
		Game game= new Game(17);
		
		//Plays the game with the red going first in 1 in parameter and the blue otherwise
		game.play(1);
	}
}
