package modele;

public class CellRiver extends CellPlayable{

	//Constructeur 
	public CellRiver(Position p) {
		super(p,'~');
	}	
	
	//Methods
	public String process (Rider r) {
		r.setRiver(true);
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est tombé dans une rivière **";
		return str;
	}
}