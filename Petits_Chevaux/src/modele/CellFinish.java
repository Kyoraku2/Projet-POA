package modele;

public class CellFinish extends CellPlayable{
	
	public CellFinish(Position p) {
		super(p,'*');
	}
	
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
