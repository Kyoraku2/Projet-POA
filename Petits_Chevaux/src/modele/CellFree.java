package modele;

public class CellFree extends CellPlayable{
	//Constructeurs
	public CellFree(Position p) {
		super(p,'.');
	}
	
	//Methodes	
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
