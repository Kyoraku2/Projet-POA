package modele;

/**
 * CellStable, rider's stable
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellStable extends CellPlayable{
	
	//////Constructor //////

	/**
	 * Constructor of CellFinish
	 * The symbol is the same for all cells of same type
	 * 
	 */
	public CellStable() {
		super('=');
	}
	
	//////Methods //////

	/**
	 * 	Function used to process the effect of the cell on the player
	 * 	and explains this effect
	 * 
	 * @param r The rider entering the cell
	 * @return A String explaining what the cell does
	 */
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
