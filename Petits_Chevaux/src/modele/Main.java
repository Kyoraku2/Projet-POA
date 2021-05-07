package modele;

import java.util.Scanner;

/** 
 * Game of "Petits Chevaux"
 * 
 * @author Tayeb
 * @author Marie-Almina
 
 */

/*Bonus made:
 * - Select size of board
 * - 
 */
public class Main {
	
	/**
	   * Main program
	   *
	   * @param args Array of parameters
	   */
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.print("Write the length of the board (minimum 16) : ");
		while(!s.hasNextInt()) {
			s.next();
			System.out.print("That's not an integer, please enter again : ");
		}
		int size=s.nextInt();
		if(size<16) {
			System.out.println("Size was lower than 16 : board size set to 16 !\n");
			size=16;
		}
		//Create a new game with a board of the size given in args 
		// Or of size 16 minimum (default 16)
		Game game= new Game(size);
		
		//Plays the game with the red going first in 1 in parameter and the blue otherwise
		game.play(1);
	}
}
