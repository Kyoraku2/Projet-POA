package modele;

/**
 * CellStable, rider's stable
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellStable extends CellPlayable{
	
	//Constructor 
	public CellStable(Position p) {
		super(p,'=');
	}
	
	//Methods
	public String process(Rider r) {
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est dans l'écurie **";
		return str;
	}
}
