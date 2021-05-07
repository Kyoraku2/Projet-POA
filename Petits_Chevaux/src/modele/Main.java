package modele;

import java.util.Scanner;

/** 
 * Game of "Petits Chevaux"
 * 
 * @author Tayeb
 * @author Marie-Almina
 
 */

/* Bonus :
 * - Selection de la taille du plateau
 * - Règle spéciale : Comme l'énoncé le suggère, lorsqu'un joueur est "mangé" par un autre,
 * il est renvoyé à sa case de départ. Cependant, dans le cas ou le joueur mangé se trouve déjà sur
 * sa case de départ, il sera renvoyé à l'écurie.
 * Par exemple dans les cas suivants :
 *
 * * *Cas classique de collision : retour au départ pour le bleu * * *
 *  +  =  +  +  +  +  +  +  +  +  +  +  + 
 *	+  #  .  .  @  .  .  .  .  .  .  R  + 
 *	+                                   + 
 *	+  .  .  .  *           *  .  .  B  + 
 *	+                                   + 
 *	+  .  .  .  .  |  .  .  .  .  ~  #  + 
 *	+  +  +  +  +  +  +  +  +  +  +  =  +
 *
 * TOUR SUIVANT : (Rouge, dé = 1)
 *
 *  +  =  +  +  +  +  +  +  +  +  +  +  + 
 *	+  #  .  .  @  .  .  .  .  .  .  .  + 
 *	+                                   + 
 *	+  .  .  .  *           *  .  .  R  + 
 *	+                                   + 
 *	+  .  .  .  .  |  .  .  .  .  ~  B  + 
 *	+  +  +  +  +  +  +  +  +  +  +  =  +
 * 
 * * *Cas où le Bleu se fait manger sur sa case de départ : direction l'écurie * * *
 * 
 *  +  =  +  +  +  +  +  +  +  +  +  +  + 
 *	+  #  .  .  @  .  .  .  .  .  .  .  + 
 *	+                                   + 
 *	+  .  .  .  *           *  .  .  R  + 
 *	+                                   + 
 *	+  .  .  .  .  |  .  .  .  .  ~  B  + 
 *	+  +  +  +  +  +  +  +  +  +  +  =  +
 *
 * TOUR SUIVANT : (Rouge, dé = 1)
 * 
 *  +  =  +  +  +  +  +  +  +  +  +  +  + 
 *	+  #  .  .  @  .  .  .  .  .  .  .  + 
 *	+                                   + 
 *	+  .  .  .  *           *  .  .  .  + 
 *	+                                   + 
 *	+  .  .  .  .  |  .  .  .  .  ~  R  + 
 *	+  +  +  +  +  +  +  +  +  +  +  B  +
 *
 *
 *  Le joueur Bleu se trouve sur sa case de départ. Admettons que le joueur Rouge face 1 au dé.
 *  Il mange donce le joueur Bleu, puis en accord avec l'implématation choisit, il sera renvoyé cette fois
 *  à l'écurie.
 */
public class Main {
	
	/**
	   * Main program
	   *
	   * @param args Array of parameters
	   */
	public static void main(String[] args) {
		//Select board a valid size
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

		//Create a new game with a board of the size given in size in [11,31]
		Game game= new Game(size);
		
		//Plays the game with the red going first in 1 in parameter and the blue otherwise
		game.play(1);
	}
}
