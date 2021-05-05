package modele;

/**
 * Interface Questionnable for cellPlayable
 * @author Marie-Almina
 * @author Tayeb
 */

public interface Questionnable {
	
	/**
	 * 	Function used to process the effect of the cell on the player
	 * 	and explains this effect
	 * 
	 * @param r The rider entering the cell
	 * @return A String explaining what the cell does
	 */
	public String process (Rider r); 
}
