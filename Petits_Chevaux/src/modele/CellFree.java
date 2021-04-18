package modele;

public class CellFree extends CellPlayable{
	boolean start;
	//Constructeurs
	public CellFree(Position p) {
		super(p,'.');
		start=false;
	}
	
	public CellFree(Position p, boolean s) {
		super(p,'.');
		start=s;
	}
	
	
	//Methodes	
	public String process(Rider r) {
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est sur ";
		if(start) {
			str+="la case de départ **\n";
		}else {
			str+="une case neutre **\n";
		}
		return str;
	}
}
