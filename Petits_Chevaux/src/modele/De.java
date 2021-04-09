package modele;

public class De {

	private int value;
	
	public De () {
		value=0;
	}
	
	public void rouler() {
		value=(int)(Math.random()*6)+1;
	}
	
	int getValue() {
		return value;
	}
	
}
