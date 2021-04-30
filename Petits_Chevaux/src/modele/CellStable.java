package modele;

public class CellStable extends CellPlayable{
	public CellStable(Position p) {
		super(p,'=');
	}
	
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
