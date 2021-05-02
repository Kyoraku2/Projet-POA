package modele;

/**
 * CellHedge class, special event 
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellHedge extends CellPlayable{

	//Constructor 
	public CellHedge(Position p) {
		super(p,'|');
	}
		
	//Methods
	public String process (Rider r) {
		r.setHedge(true);
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est tombé dans une haie **";
		return str;
	}
}