package modele;

public class CellHole extends CellPlayable{

	//Constructeur 
	public CellHole(Position p) {
		super(p,'@');
	}
	
	//Methods
	//A faire
	public String process (Rider r) {
		r.incrementHole();
		if(r.inHole()==3) {
			r.resetHole();
		}
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		if(r.inHole()<0) {
			str+="est libéré d'un trou **";
		}else {
			str+="est dans un trou **";
		}
		return str;
	}
}