package modele;

public class CellHedge extends CellPlayable{

	//Constructeur 
	public CellHedge(Position p) {
		super(p,'|');
	}
		
	//Methods
	
	//A faire
	public String process (Rider r) {
		r.setHedge(true);
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		if(!r.inHedge()) {
			str+="est lib�r� d'une haie **";
		}else {
			str+="est tomb� dans une haie **";
		}
		return str;
	}
}