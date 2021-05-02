package modele;

/**
 * CellRiver class, special event
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellRiver extends CellPlayable{

	//Constructor 
	public CellRiver(Position p) {
		super(p,'~');
	}	
	
	//Methods
	public String process (Rider r) {
		r.setRiver(true);
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est tombé dans une rivière **";
		return str;
	}
}