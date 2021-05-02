package modele;

/**
 * CellFinish class, game end cell
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellFinish extends CellPlayable{

	//Constructor
	public CellFinish(Position p) {
		super(p,'*');
	}
	
	//Methods	
	public String process(Rider r) {
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est sur la case d'arrivée **";
		return str;
	}
}
