package modele;

/**
 * CellStart, starting cells for riders
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellStart extends CellPlayable{
	
	//Constructor 
	public CellStart(Position p) {
		super(p,'#');
	}
	
	//Methods
	public String process(Rider r) {
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		if(r.getPos()==r.getStart()) {
            str+="est sur la case de départ **";
        }else {
            str+="est sur une case neutre **";
        }
		return str;
	}
}
