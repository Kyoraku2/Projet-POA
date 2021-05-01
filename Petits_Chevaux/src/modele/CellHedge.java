package modele;

public class CellHedge extends CellPlayable{

	//Constructeur 
	public CellHedge(Position p) {
		super(p,'|');
	}
		
	//Methods
	
	public String process (Rider r) {
		r.setHedge(true);
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est tombé dans une haie **";
		return str;
	}
}