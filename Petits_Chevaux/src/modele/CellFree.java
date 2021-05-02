package modele;

/**
 * CellFree class, normal CellPlayable
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellFree extends CellPlayable{
	
	//Constructor
	public CellFree(Position p) {
		super(p,'.');
	}
	
	//Methods	
	public String process(Rider r) {
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est sur une case neutre **";
		return str;
	}
}
