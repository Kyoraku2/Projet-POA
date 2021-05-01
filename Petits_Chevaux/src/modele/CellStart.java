package modele;

public class CellStart extends CellPlayable{
	public CellStart(Position p) {
		super(p,'#');
	}
	
	public String process(Rider r) {
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		if(r.getPos()==r.getStart()) {
            str+="est sur la case de d�part **";
        }else {
            str+="est sur une case neutre **";
        }
		return str;
	}
}