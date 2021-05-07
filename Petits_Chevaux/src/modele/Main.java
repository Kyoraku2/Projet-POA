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
		System.out.print("Write the length of the board in [11,31] : ");
		int size=0;
		while(!s.hasNextInt()) {
			s.next();
			System.out.print("That's not an integer, please enter again in [11,31] : ");
		}
		size=s.nextInt();
		while(size<11 || size>31) {
			System.out.print("That's not in the good intervalle, please enter again in [11,31] : ");
			while(!s.hasNextInt()) {
				s.next();
				System.out.print("That's not an integer, please enter again in [11,31] : ");
			}
			size=s.nextInt();
		}
		/*if(size<11) {
			System.out.println("Size was lower than 11 : board size set to 11 !\n");
			size=11;
		}*/
		//Create a new game with a board of the size given in args 
		// Or of size 16 minimum (default 16)
		Game game= new Game(size);
		
		//Plays the game with the red going first in 1 in parameter and the blue otherwise
		game.play(1);
	}
}
