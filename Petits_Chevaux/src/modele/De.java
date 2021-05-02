package modele;

/**
 * De class that represent a dice
 * @author Marie-Almina
 * @author Tayeb
 */

public class De {
	
	//Attributes
	private int value;
	
	//Constructor 
	public De () {
		value=0;
	}
	
	//Methods
	public void rouler() {
		value=(int)(Math.random()*6)+1;
	}
	
	int getValue() {
		return value;
	}
	
}
