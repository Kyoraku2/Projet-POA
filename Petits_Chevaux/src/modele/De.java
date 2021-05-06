package modele;

/**
 * De, represent a dice
 * @author Marie-Almina
 * @author Tayeb
 */

public class De {
	
	//////Attributes //////
	
	/**
	 * Value of the dice
	 */
	private int value;
	
	
	//////Constructor //////
	
	/**
	 * Constructor of the dice
	 * Sets the value to 0 
	 */
	public De () {
		value=0;
	}
	
	
	//////Methods //////

	/**
	 * Roll the dice and sets the value to the number obtained 
	 * 
	 */
	public void rouler() {
		value=(int)(Math.random()*6)+1;
	}
	
	/**
	 * Getter of the value of the dice
	 * 
	 * @return the value
	 */
	int getValue() {
		return value;
	}
	
}
