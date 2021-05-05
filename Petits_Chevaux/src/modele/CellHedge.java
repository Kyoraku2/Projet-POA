package modele;

/**
 * CellHedge class, special event 
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellHedge extends CellPlayable{

	//////Constructor //////

	/**
	 * Constructor of CellFinish
	 * The symbol is the same for all cells of same type
	 */
	public CellHedge() {
		super('|');
	}
		
	//////Methods //////

	/**
	 * 	Function used to process the effect of the cell on the player
	 * 	and explains this effect
	 * 	Sets the boolean hedge of the player to true
	 * 
	 * @param r The rider entering the cell
	 * @return A String explaining what the cell does
	 */
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