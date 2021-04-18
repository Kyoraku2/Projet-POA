package modele;

public class CellWhite extends Cell{

	//Constructeur 
	public CellWhite(Position p) {
		super(p,' ');
	}	
	
	public boolean isPlayable() {
		return false;
	}
	
}
